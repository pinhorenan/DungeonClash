package main.java.com.dungeonclash.character.classes;

import main.java.com.dungeonclash.skills.Skill;
import main.java.com.dungeonclash.skills.AtributesModifiers;

import java.util.HashSet;

public class Monster extends CharacterClass {

    public Monster() {
        // Construtor pré-definido para Main.java.com.dungeonclash.character.classes.Monster
        this.strength = 4;
        this.agillity = 1;
        this.intelligence = 0;
        skills = new HashSet<>();
        skills.add(new Skill("Socar", new AtributesModifiers(0.8, 0.4, 1), new AtributesModifiers(0, 0, 0), 5, false, false));
        skills.add(new Skill("Chutar", new AtributesModifiers(1, 0.5, 1), new AtributesModifiers(0, 0, 0), 8, false, false));
        skills.add(new Skill("Grito atordoante", new AtributesModifiers(0.4, 0.2, 1), new AtributesModifiers(0, 0, 0), 6, false, true));
    }

    public void levelUp() {
        // Implementação com valores correspondentes à progressão do Mostro.
        this.strength += 4;
        this.agillity += 1;
        this.intelligence = 0;
    }
}