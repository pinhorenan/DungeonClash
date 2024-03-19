package main.java.com.dungeonclash.character.classes;

import main.java.com.dungeonclash.skills.Skill;

import java.util.Set;

public abstract class CharacterClass {

    protected int strength;
    protected int agillity;
    protected int intelligence;
    protected Set<Skill> skills;

    public CharacterClass() {
        // Construtor padrão.
    }

    public void levelUp() {
        // I really should revisit this implementation I don't think it's really necessary.
        // Método abstrato que aumentará os atributos das classes.
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public int getAgillity() {
        // Retorna a agilidade. Método padrão para todas classes herdam de Main.java.com.dungeonclash.character.classes.CharacterClass.
        return agillity;
    }

    public int getStrength() {
        // Retorna a forca. Método padrão para todas classes herdam de Main.java.com.dungeonclash.character.classes.CharacterClass.
        return strength;
    }

    public int getIntelligence() {
        // Retorna a inteligencia. Método padrão para todas classes herdam de Main.java.com.dungeonclash.character.classes.CharacterClass.
        return intelligence;
    }

    public Set<Skill> getHabilidades() {
        // Retorna o conjunto de Habilidades. Método padrão para todas classes herdam de Main.java.com.dungeonclash.character.classes.CharacterClass.
        return skills;
    }
}