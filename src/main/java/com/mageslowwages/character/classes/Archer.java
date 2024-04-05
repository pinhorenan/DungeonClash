package main.java.com.mageslowwages.character.classes;

import main.java.com.mageslowwages.skills.Skill;
import main.java.com.mageslowwages.skills.AtributesModifiers;

import java.util.HashSet;

public class Archer extends CharacterClass {

    public Archer() {
        // Construtor pré-definido para Main.java.com.dungeonclash.character.classes.Archer
        this.strength = 1;
        this.agillity = 3;
        this.intelligence = 2;
        skills = new HashSet<>();
        skills.add(new Skill("Socar", new AtributesModifiers(0.3, 0.1, 1), new AtributesModifiers(0, 0, 0), 3, false, false));
        skills.add(new Skill("Atirar Flecha", new AtributesModifiers(0.3, 0.5, 1), new AtributesModifiers(0, 0, 0), 4, false, false));
        skills.add(new Skill("Flecha Encantada", new AtributesModifiers(0.3, 0.5, 0.4), new AtributesModifiers(1, 0.2, 1), 7, false, false));
    }

    public void levelUp() {
        // Implementação com valores correspondentes à progressão do Main.java.com.dungeonclash.character.classes.Archer.
        this.strength += 1;
        this.agillity += 3;
        this.intelligence += 2;
    }
}