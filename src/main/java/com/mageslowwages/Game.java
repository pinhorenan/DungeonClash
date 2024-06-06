package main.java.com.mageslowwages;

import main.java.com.mageslowwages.character.Character;
import main.java.com.mageslowwages.character.classes.Archer;
import main.java.com.mageslowwages.character.classes.Warrior;
import main.java.com.mageslowwages.character.classes.Mage;
import main.java.com.mageslowwages.character.classes.Monster;
import main.java.com.mageslowwages.groups.Group;
import main.java.com.mageslowwages.skills.Skill;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Game {

  private final Group heros;
  private final Group foes;
  private List<String> textLines;
  private int turnCounter;

  public Game(String filePath) throws IOException {
    this.heros = new Group(false);
    this.foes = new Group(true);
    Path file = Paths.get(filePath);
    this.textLines = Files.readAllLines(file);
    this.turnCounter = 1;

    if (!Files.exists(file)) {
      System.out.println("File not found. Be sure the filepath is correct.");
      System.exit(1);
    }

    try {
      this.textLines = Files.readAllLines(file);
    } catch (IOException exception) {
      System.err.println(STR."Error reading the file: \{exception.getMessage()}");
      System.exit(1);
    }
  }

  public void start() {
    loadHeros();

    String searchFor = "fase";
    boolean verification = false;
    for (String line : textLines){
        if (!(line.toLowerCase().contains(searchFor.toLowerCase()))){
          loadFoes(line);
          verification = true;
        } else {
          if (verification){
            startBattle();
            verification = false;
          }
          System.out.println(STR."\n\{line.substring(5)}");
        }
    }
      // Epílogo
  }

  private void loadHeros() {
      // Utilizada para receber a entrada do usuário na hora de criar heróis. Chamada no inicio do jogo.
    Scanner scanner = new Scanner(System.in);
    boolean characterCreation;

    for (int i = 1; i <= 3; ++i) {
      System.out.println("\nName of your character: ");
      String characterName = scanner.nextLine();
      System.out.println(STR."\nWhich class will \{characterName} be?");
      System.out.println("\n1- Warrior\n2- Archer\n3- Mage");
      int chooseClass;
      do {
        try {
          chooseClass = Integer.parseInt(scanner.nextLine());
          if (chooseClass <= 3 && chooseClass >= 1) {
            break;         // Saia do loop se a entrada for válida
          } else {
            System.out.println("Invalid choice! Try again.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid input! Try again.");
        }
      } while (true);

      Character newCharacter = switch (chooseClass) {
        case 1 -> new Character(characterName, new Warrior());
        case 2 -> new Character(characterName, new Archer());
        case 3 -> new Character(characterName, new Mage());
        default -> throw new IllegalStateException(STR."Invalid value: \{chooseClass}");
      };

      heros.addMember(newCharacter);

        Scanner scan = new Scanner(System.in);

      while (i <= 2) {
        System.out.println("\nDo you wish to create another character?");
        try {
          System.out.println("Type (y)es to create another character, type (n)o to end character creation and start the game:");
          characterCreation = scan.nextBoolean();
          if (!characterCreation) {
            i = 4;
          }
            break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input! Try again.");
          scan.nextLine(); // Clear the input buffer
        }
    }
  }
  System.out.println("\nGroup created!");
}

  private void loadFoes(String line) {
    String[] split = line.split(" ", 3);

    String foeName = split[0];
    String foeClass = split[1];
    int foeLevel = Integer.parseInt(split[2]);

    Character newFoe = null;
    switch (foeClass.toLowerCase()) {
      case "warrior":
        newFoe = new Character(foeName, new Warrior(), foeLevel);
        break;
      case "archer":
        newFoe = new Character(foeName, new Archer(), foeLevel);
        break;
      case "mage":
        newFoe = new Character(foeName, new Mage(), foeLevel);
        break;
      case "monster":
        newFoe = new Character(foeName, new Monster(), foeLevel);
        break;
      default:
        // Lógica para tratamento de classe desconhecida
        break;
    }

    foes.addMember(newFoe);
  }

  private void startBattle() {
    System.out.println("The battle begins!");

    // Sorteia de quem ataca
    Character attacker = randomlyChooseFirstAttacker(heros, foes);

    while (heros.atLeastOneStillAlive() && foes.atLeastOneStillAlive()) {

      // Trocar o atacante para o próximo turno
      attacker = (heros.getMembers().contains(attacker)) ? foes.getNextToAttack() : heros.getNextToAttack();

      showGroupInfo(heros, foes);
      runTurn(heros, foes, attacker);
    }

    // Exibe o resultado da batalha
    if(showBattleResults(foes)) {
      turnCounter = 1;
      for(Character character : heros.getMembers()){
        character.setHP(character.getMaxHP());
        character.setMana(character.getMaxMana());
      }
    } else System.exit(1);
  }

  private void runTurn(Group heroes, Group foes, Character attacker) {
    System.out.println(STR."\n --- Round \{turnCounter} --- ");

    int accumulatedXP;

    // Verifica se o atacante possui tempo de espera
    if(!silentTurn(heroes, foes)) {
        // Imprime
        System.out.println(STR."It's time for \{attacker.getName()} to attack!");

        // Escolhe uma habilidade
        Skill choosenSkill = chooseSkill(attacker);

        // Escolhe um alvo
        Group targetGroup = (heroes.getMembers().contains(attacker)) ? foes : heroes;
        Character target = chooseTarget(targetGroup);

        // Executa a habilidade no alvo
        if (target != null) {
          attacker.useSkill(choosenSkill, target);
          accumulatedXP = attacker.useSkill(choosenSkill, target);
        } else {
          accumulatedXP = 0;
          System.out.println("No valid target found.");
        }

        // Distribui possível PE vindo de possíveis atordoamentos da habilidade usada no turno
        if (target != null) {
          attacker.useSkill(choosenSkill, target);
          if (target.getHP() <= 0) {
            for (Character character : heroes.getMembers()){
              character.calculateXP(target);
              character.levelUp();
            }
            target.kill();
          }
        } else {
            System.out.println("No valid target found.");
        }

        // Atualiza o tempo de espera de maneira correspondente a sua habilidade
        attacker.updateWaitTime(choosenSkill.getWaitTime());

    }

    // Decrementa o tempo de espera de todos os personagens ao final do turno
    heroes.decrementsWaitingTime();
    foes.decrementsWaitingTime();

    // Incrementa o contador de turnos
    turnCounter++;
  }

  private void showSkills(Character character) {
    System.out.println(STR."Available skills for \{character.getName()}:");

    for (Skill skill : character.getCharacterClass().getSkills()) {
      System.out.println(STR."\{skill.getName()} (Mana Cost: \{skill.getManaCost(character.getCharacterClass())}, Damage: \{skill.getDamageDealt(character.getCharacterClass())})");
    }
  }

  private void showInfo(Group group) {
    for (Character member : group.getMembers()) {
      if(member.getIsDead()) {
        System.out.println(STR."ID: \{member.getID()}");
        System.out.println(STR."Name: \{member.getName()}");
        System.out.println(STR."Class: \{member.getClassName()}");
        System.out.println(STR."HP: \{member.getHP()}");
        System.out.println(STR."Mana: \{member.getMana()}");
        System.out.println(STR."Level: \{member.getLevel()}");
        System.out.println(STR."Cooldown: \{member.getWaitTime()}");

        System.out.println("---------------------------------------");
      }
    }
  }

  private void showGroupInfo(Group heros, Group foes) {
    System.out.println("\n --- Teams Info ---");
    System.out.println("\nHeroes: ");
    showInfo(heros);
    System.out.println("\nEnemies: ");
    showInfo(foes);
  }

  private boolean showBattleResults(Group foes) {
    if (!foes.atLeastOneStillAlive()) {
      System.out.println("Congratulations! You won!");
      return true;
    } else {
      System.out.println("Game over! You lose!");
      return false;
    }
  }

  private boolean silentTurn(Group heroes, Group foes) {
    for (Character character : heroes.getMembers())
      if (character.getWaitTime() == 0 || character.getIsDead()){
        return false;
      }
    for (Character character : foes.getMembers()){
      if (character.getWaitTime() == 0 || character.getIsDead()){
        return false;
      }
    }
    return true;
  }

  private Skill chooseSkill(Character character) {
    Scanner scanner = new Scanner(System.in);

    // Mostra as skills disponíveis para o character
    System.out.println(STR."Choose a skill for \{character.getName()}:");
    showSkills(character);

    // Solicita a entrada do usuário até que uma habilidade válida seja escolhida
    while (true) {
      System.out.print("Insert skill name: ");
      String skillName = scanner.nextLine();

      // Verifica se a habilidade escolhida está na lista de skills do character
      for (Skill skill : character.getCharacterClass().getSkills()) {
        if (skill.getName().equalsIgnoreCase(skillName)) {
          return skill; // Retorna a skill escolhida
        }
      }

      // Se o nome da habilidade não foi encontrado, exibe uma mensagem de erro
      System.out.println("Invalid skill! Please, choose a skill from the list.");
    }
  }

  private Character randomlyChooseFirstAttacker(Group heros, Group foes) {
    Random random = new Random();
    return (random.nextBoolean()) ? heros.getNextToAttack() : foes.getNextToAttack();
  }

  private Character chooseTarget(Group group) {
    Scanner scanner = new Scanner(System.in);
    Set<Character> members = group.getMembers();

    // Exibir lista de alvos disponíveis
    System.out.println("Choose a target:");

    int index = 1;
    for (Character member : members) {
      if (member.getIsDead()) {
        System.out.println(STR."\{index}. \{member.getName()}");
        index++;
      }
    }

    // Solicitar ao jogador a choice do alvo
    int choice;
    do {
      System.out.print("Insert target number: ");
      try {
        choice = Integer.parseInt(scanner.nextLine());

        // Checks if it's a valid choice
        if (choice < 1 || choice > members.size()) {
          System.out.println("Invalid choice. Insert a valid number.");
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice. Insert a valid number.");
      }
    } while (true);

    // Retornar o alvo escolhido
    int count = 1;
    for (Character member : members) {
      if(member.getIsDead()) {
        if (count == choice) {
          return member;
        }
        count++;
      }
    }

    // Caso algo inesperado ocorra, retornamos null
    return null;
  }

  //not ok
  //private Set<Character> chooseTarget(Group group) { return null;}
}
