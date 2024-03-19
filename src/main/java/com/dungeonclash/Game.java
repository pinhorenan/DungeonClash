package main.java.com.dungeonclash;

import main.java.com.dungeonclash.character.Character;
import main.java.com.dungeonclash.character.classes.Archer;
import main.java.com.dungeonclash.character.classes.Warrior;
import main.java.com.dungeonclash.character.classes.Mage;
import main.java.com.dungeonclash.character.classes.Monster;
import main.java.com.dungeonclash.groups.Group;
import main.java.com.dungeonclash.skills.Skill;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Game {

  private final Group heros;
  private final Group foes;
  private List<String> textLines;
  private int turnCounter;

  public Game(String caminhoArquivo) throws IOException {
      // Construtor
    this.heros = new Group(false);
    this.foes = new Group(true);
    Path file = Paths.get(caminhoArquivo);
    this.textLines = Files.readAllLines(file);
    this.turnCounter = 1;

    if (!Files.exists(file)) {
      System.out.println("Arquivo não encontrado. Certifique-se de fornecer o caminho correto.");
      System.exit(1);
    }

    try {
      this.textLines = Files.readAllLines(file);
    } catch (IOException exception) {
      System.err.println("Erro ao ler o arquivo: " + exception.getMessage());
      System.exit(1);
    }
  }

  public void start() {
      // Prólogo
    loadHeros();

      // História
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
          System.out.println("\n" + line.substring(5));
        }
    }
      // Epílogo
  }

  private void loadHeros() {
      // Utilizada para receber a entrada do usuário na hora de criar heróis. Chamada no inicio do jogo.
    Scanner scanner = new Scanner(System.in);
    boolean characterCreation;

    for (int i = 1; i <= 3; ++i) {
      System.out.println("\nNome do " + i + "º Herói ou Heroína: ");
      String characterName = scanner.nextLine();
      System.out.println("\nQual será a classe de " + characterName + "?");
      System.out.println("\n1- GUERREIRO\n2- ARQUEIRO\n3- MAGO");
      int chooseClass;
      do {
        try {
          chooseClass = Integer.parseInt(scanner.nextLine());
          if (chooseClass <= 3 && chooseClass >= 1) {
            break;         // Saia do loop se a entrada for válida
          } else {
            System.out.println("Escolha inválida! Tente novamente.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Input inválido! Tente de novo.");
        }
      } while (true);

      Character newCharacter = switch (chooseClass) {
        case 1 -> new Character(characterName, new Warrior());
        case 2 -> new Character(characterName, new Archer());
        case 3 -> new Character(characterName, new Mage());
        default -> throw new IllegalStateException("Valor inválido: " + chooseClass);
      };

      heros.addMember(newCharacter);

        Scanner scan = new Scanner(System.in);

      while (i <= 2) {
        System.out.println("\nDeseja criar mais um personagem?");
        try {
          System.out.println("Digite 'true' se deseja criar mais um personagem ou 'false' para sair:");
          characterCreation = scan.nextBoolean();
          if (!characterCreation) {
            i = 4;
          }
            break;
        } catch (InputMismatchException e) {
          System.out.println("Input inválido! Tente de novo.");
          scan.nextLine(); // Clear the input buffer
        }
    }
  }
  System.out.println("\nMain.java.com.dungeonclash.groups.Group criada!");
}

  private void loadFoes(String line) {
    String[] split = line.split(" ", 3);

    String foeName = split[0];
    String foeClass = split[1];
    int foeLevel = Integer.parseInt(split[2]);

    Character newFoe = null;
    switch (foeClass.toLowerCase()) {
      case "guerreiro":
        newFoe = new Character(foeName, new Warrior(), foeLevel);
        break;
      case "arqueiro":
        newFoe = new Character(foeName, new Archer(), foeLevel);
        break;
      case "mago":
        newFoe = new Character(foeName, new Mage(), foeLevel);
        break;
      case "monstro":
        newFoe = new Character(foeName, new Monster(), foeLevel);
        break;
      default:
        // Lógica para tratamento de classe desconhecida
        break;
    }

    foes.addMember(newFoe);
  }

  private void startBattle() {
    System.out.println("Iniciando batalha...");

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

  private void runTurn(Group heros, Group foes, Character attacker) {
    System.out.println("\n --- Turno " + turnCounter + " --- ");

    int acumuloPE;

    // Verifica se o atacante possui tempo de espera
    if(!silentTurn(heros, foes)) {
        // Imprime
        System.out.println("É a vez de " + attacker.getName() + " atacar!");

        // Escolhe uma habilidade
        Skill choosenSkill = chooseSkill(attacker);

        // Escolhe um alvo
        Group targetGroup = (heros.getMembers().contains(attacker)) ? foes : heros;
        Character target = chooseTarget(targetGroup);

        // Executa a habilidade no alvo
        if (target != null) {
          attacker.useSkill(choosenSkill, target);
          acumuloPE = attacker.useSkill(choosenSkill, target);
        } else {
          acumuloPE = 0;
          System.out.println("Nenhum alvo válido encontrado.");
        }

        // Distribui possível PE vindo de possíveis atordoamentos da habilidade usada no turno
        if (target != null) {
          attacker.useSkill(choosenSkill, target);
          if (target.getHP() <= 0) {
            for (Character character : heros.getMembers()){
              character.calculateXP(target);
              character.levelUp();
            }
            target.kill();
          }
        } else {
            System.out.println("Nenhum alvo válido encontrado.");
        }

        // Atualiza o tempo de espera de maneira correspondente a sua habilidade
        attacker.updateWaitTime(choosenSkill.getWaitTime());

    }

    // Decrementa o tempo de espera de todos os personagens ao final do turno
    heros.decrementsWaitingTime();
    foes.decrementsWaitingTime();

    // Incrementa o contador de turnos
    turnCounter++;
  }

  private void showSkills(Character character) {
    System.out.println("Habilidades disponíveis para " + character.getName() + ":");

    for (Skill skill : character.getCharacterClass().getHabilidades()) {
      System.out.println(skill.getName() + " (PM Necessário: " + skill.getManaCost(character.getCharacterClass())+", Dano: " + skill.getDamageDealt(character.getCharacterClass()) +")");
    }
  }

  private void showInfo(Group group) {
    for (Character member : group.getMembers()) {
      if(member.getIsDead()) {
        System.out.println("ID: " + member.getID());
        System.out.println("Nome: " + member.getName());
        System.out.println("Main.java.com.dungeonclash.character.classes.CharacterClass: " + member.getClassName());
        System.out.println("PV: " + member.getHP());
        System.out.println("PM: " + member.getMana());
        System.out.println("Nível: " + member.getLevel());
        System.out.println("Tempo de Espera: " + member.getWaitTime());

        System.out.println("---------------------------------------");
      }
    }
  }

  private void showGroupInfo(Group heros, Group foes) {
    System.out.println("\n --- Informações das Equipes ---");
    System.out.println("\nHeróis: ");
    showInfo(heros);
    System.out.println("\nInimigos: ");
    showInfo(foes);
  }

  private boolean showBattleResults(Group foes) {
    if (!foes.atLeastOneStillAlive()) {
      System.out.println("Parabéns! Você venceu a batalha!");
      return true;
    } else {
      System.out.println("Game over! Você foi derrotado!");
      return false;
    }
  }

  private boolean silentTurn(Group heros, Group foes) {
    for (Character character : heros.getMembers())
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
    System.out.println("Escolha uma habilidade para " + character.getName() + ":");
    showSkills(character);

    // Solicita a entrada do usuário até que uma habilidade válida seja escolhida
    while (true) {
      System.out.print("Digite o nome da habilidade: ");
      String nomeHabilidade = scanner.nextLine();

      // Verifica se a habilidade escolhida está na lista de skills do character
      for (Skill skill : character.getCharacterClass().getHabilidades()) {
        if (skill.getName().equalsIgnoreCase(nomeHabilidade)) {
          return skill; // Retorna a skill escolhida
        }
      }

      // Se o nome da habilidade não foi encontrado, exibe uma mensagem de erro
      System.out.println("Main.java.com.dungeonclash.skills.Skill inválida! Por favor, escolha uma habilidade da lista.");
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
    System.out.println("Escolha um alvo:");

    int index = 1;
    for (Character member : members) {
      if (member.getIsDead()) {
        System.out.println(index + ". " + member.getName());
        index++;
      }
    }

    // Solicitar ao jogador a choice do alvo
    int choice;
    do {
      System.out.print("Digite o número correspondente ao alvo: ");
      try {
        choice = Integer.parseInt(scanner.nextLine());

        // Checks if it's a valid choice
        if (choice < 1 || choice > members.size()) {
          System.out.println("Escolha inválida. Digite um número válido.");
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Digite um número válido.");
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
  //private Set<Main.java.com.dungeonclash.character.Character> escolherAlvo(Main.java.com.dungeonclash.groups.Group equipe) { return null;}
}
