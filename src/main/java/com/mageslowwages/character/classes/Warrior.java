package main.java.com.mageslowwages.character.classes;

import main.java.com.mageslowwages.skills.Skill;
import main.java.com.mageslowwages.skills.AtributesModifiers;

import java.util.HashSet;

public class Warrior extends CharacterClass {

    public Warrior() {
        // Construtor pré-definido para Main.java.com.dungeonclash.character.classes.Warrior
        this.strength = 4;
        this.agillity = 1;
        this.intelligence = 1;
        skills = new HashSet<>();
        skills.add(new Skill("Socar", new AtributesModifiers(0.3, 0.1, 1), new AtributesModifiers(0,0,0),4 , false, false));
        skills.add(new Skill("Golpe de Espada", new AtributesModifiers(0.7,0.3,1), new AtributesModifiers(0,0,0),5 , false, false));
        skills.add(new Skill("Espada Flamejante", new AtributesModifiers(0.3,0.5,0.4), new AtributesModifiers(0.2,1, 1),7 , false, false));
    }

    public void levelUp() {
        // Implementação com valores correspondentes à progressão do Main.java.com.dungeonclash.character.classes.Warrior.
        this.strength += 4;
        this.agillity += 1;
        this.intelligence += 1;
    }

}