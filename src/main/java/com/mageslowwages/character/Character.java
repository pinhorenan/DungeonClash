package main.java.com.mageslowwages.character;

import main.java.com.mageslowwages.character.classes.CharacterClass;
import main.java.com.mageslowwages.groups.Group;
import main.java.com.mageslowwages.skills.Skill;

import java.util.Set;

public class Character implements Comparable<Character> {

    private final String name;
    private final CharacterClass characterClass;
    private static int nextID = 1;
    private final int ID;
    private int level, XP, waitTime;
    private float maxHP, HP, maxMana, mana;
    private boolean isDead;

    public Character(String name, CharacterClass characterClass) {
        // Construtor. Deverá passar o nome (String) e uma classe, a classe deverá ser criada no campo de parâmetro
        this.name = name;
        this.characterClass = characterClass;
        ID = getNextID();
        level = 1;
        XP = 0;
        waitTime = 0;
        maxHP = characterClass.getStrength() + ((float) characterClass.getAgillity() / 2);
        maxMana = characterClass.getIntelligence() + ((float) characterClass.getAgillity() / 3);
        HP = maxHP;
        mana = maxMana;
        isDead = false;
    }

    public Character(String name, CharacterClass characterClass, int level) {
        // Construtor. Deverá passar o nome (String) e uma classe, a classe deverá ser criada no campo de parâmetro
        this.name = name;
        this.characterClass = characterClass;
        ID = getNextID();
        this.level = level;
        XP = 0;
        waitTime = 0;
        maxHP = level * characterClass.getStrength() + (level * ((float) characterClass.getAgillity() / 2));
        maxMana = level * characterClass.getIntelligence() + (level * ((float) characterClass.getAgillity() / 3));
        HP = maxHP;
        mana = maxMana;
        isDead = false;
    }

    @Override
    public int compareTo(Character outro) {
        // Define um método de comparação entre instâncias de Personagens para comparar seus tempoEspera. Chamado para definir o próximoAtacante.
        return Integer.compare(this.getWaitTime(), outro.getWaitTime());
    }

    public void levelUp() {
        // Incrementa o nível do Main.java.com.dungeonclash.character.Character que chamar. Também irá "aumentar o nível" da instância de classe desse Main.java.com.dungeonclash.character.Character. Os PE são zerados após a subida de nível e qualquer excesso é desperdiçado.
        if (XP >= (level * 25)) {
            level++;
            characterClass.levelUp();
            maxHP += level * characterClass.getStrength() + (level * ((float) characterClass.getAgillity() / 2));
            maxMana += level * characterClass.getIntelligence() + (level * ((float) characterClass.getAgillity() / 3));
            XP = 0;
        }
    }

    public void updateWaitTime(int time) {
        // Define o time de espera do Main.java.com.dungeonclash.character.Character que chamar para "time". Chamado após usar skills.
        this.setWaitTime(time);
    }

    private static int getNextID() {
        // Gera um novo ID incrementando o último ID registrado. Chamado durante instanciação de Personagens.
        return nextID++;
    }

    public void takeDamage(float damage) {
        // Subtrai o PV do Main.java.com.dungeonclash.character.Character que chamar pelo int Dano. Chamado durante uso de skills.
        if (HP > 0) {
            HP -= damage;
        }
        if (HP <= 0){
            HP = 0;
            System.out.println("\n" + name + " is dead.");
            isDead = true;
        }
    }

    public float manaCost(Skill skill) {
        // Retorna o custo em PM ao usar uma skill dependendo do nível do personagem que chamar.
        return (level * skill.getManaCost(characterClass));
    }

    public float damageDealt(Skill skill) {
        // Retorna o dano em PV ao usar uma skill dependendo do nível do personagem que chamar.
        return level * skill.getDamageDealt(characterClass);
    }

    public int useSkill(Skill skill, Character target) {
        // Método para usar a Main.java.com.dungeonclash.skills.Skill. Primeiro irá verificar se quem chamou cumpre os requisitos e então aplica os efeitos no Alvo.
        if (checkCanUse(skill, target) && checkCanUse(skill)) {
            return(applySkillEffects(skill, target));
        }
        return 0;
    }

    /*
    Do I really need this different method when attacking a group?

    public void useSkill(Skill skill, Group targetGroup) {
        // Método para usar a Main.java.com.dungeonclash.skills.Skill. Primeiro irá verificar se quem chamou cumpre os requisitos e então aplica os efeitos no targetGroup.
        if (checkCanUse(skill)) {
            applySkillEffects(skill, targetGroup);
        }
    }
    ]*/

    private boolean checkCanUse(Skill skill) {
        // Método auxiliar para verificar possibilidade de "usarHabilidade()". Restrições sobre quem chama a Main.java.com.dungeonclash.skills.Skill.
        if (isDead) {
            return false;
        }
        if (waitTime != 0) {
            return false;
        }
        if (skill.getManaCost(characterClass) > mana) {
            return false;
        }
        return characterClass.getSkills().contains(skill);
    }

    private boolean checkCanUse(Skill skill, Character target) {
        // Método auxiliar para verificar possibilidade de "usarHabilidade()". Restrições sobre o target da skill.
        if (target.isDead) {
            return false;
        }
        return checkCanUse(skill);
    }

    private int applySkillEffects(Skill skill, Character target) {
        // Método que aplica os efeitos de uma skill usada, é chamado por "usarHabilidade()"; Versão para ataque em Personagens.
        if (skill.getCanTargetAllies()) {
            // Habilidades direcionadas à aliados irão curar.
            target.setHP(target.getHP() + damageDealt(skill));
        } else {
            target.takeDamage(damageDealt(skill));
        }
        setMana(this.getMana() - manaCost(skill));
        return 0;
    }

    public void calculateXP(Character target) {
        this.XP += target.getLevel()*5;
    }

    private void applySkillEffects(Skill skill, Group groupTarget) {
        // Método que aplica os efeitos de uma skill usada, é chamado por "usarHabilidade()"; Versão para ataque em Equipes.
        Set<Character> integrantes = groupTarget.getMembers();
        for (Character character : integrantes) {
            character.takeDamage(damageDealt(skill));
        }
    }

    /* public void ganharPE(int PE) {
        // Incrementa Pontos de Experiência ao Main.java.com.dungeonclash.character.Character.
        this.PE += PE;
    } */

    public void kill() {
        // Define o Main.java.com.dungeonclash.character.Character como Atordoado.
        setIsDead(true);
    }

    public String getName() {
        // Retorna o nome do Main.java.com.dungeonclash.character.Character.
        return name;
    }

    public CharacterClass getCharacterClass() {
        // Retorna a instância de classe do Main.java.com.dungeonclash.character.Character.
        return characterClass;
    }

    public String getClassName() {
        // Retorna o nome simples da classe associada ao Main.java.com.dungeonclash.character.Character.
        return characterClass.getName();
    }

    public int getLevel() {
        // Retorna o nível do Main.java.com.dungeonclash.character.Character.
        return level;
    }

    public int getID() {
        // Retorna o ID do Main.java.com.dungeonclash.character.Character.
        return ID;
    }

    public int getWaitTime() {
        // Retorna o Tempo de Espera do Main.java.com.dungeonclash.character.Character.
        return waitTime;
    }

    public float getHP() {
        // Retorna os Pontos de vida do Main.java.com.dungeonclash.character.Character.
        return HP;
    }

    public float getMana() {
        // Retorna os Pontos de Magia do Main.java.com.dungeonclash.character.Character.
        return mana;
    }

    public boolean getIsDead() {
        // Retorna o status de atordoamento do Main.java.com.dungeonclash.character.Character.
        return !isDead;
    }

    public void setWaitTime(int waitTime) {
        // Altera o Tempo de Espera do Main.java.com.dungeonclash.character.Character.
        this.waitTime = waitTime;
    }

    public void setHP(float HP) {
        // Altera os Pontos de Vida do Main.java.com.dungeonclash.character.Character.
        this.HP = HP;
    }

    public void setMana(float mana) {
        // Altera os Pontos de Magia do Main.java.com.dungeonclash.character.Character.
        this.mana = mana;
    }

    public void setIsDead(boolean isDead) {
        // Altera o estado de Atordoamento do Main.java.com.dungeonclash.character.Character.
        this.isDead = isDead;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public float getMaxMana() {
        return maxMana;
    }
}



