package main.java.com.dungeonclash.character.classes;

import main.java.com.dungeonclash.skills.Skill;
import main.java.com.dungeonclash.skills.AtributesModifiers;

import java.util.HashSet;

public class Mage extends CharacterClass {

    public Mage()  {
        // Construtor pré-definido para Main.java.com.dungeonclash.character.classes.Mage
        this.strength = 1;
        this.agillity = 2;
        this.intelligence = 3;
        skills = new HashSet<>();
        skills.add(new Skill("Socar", new AtributesModifiers(0.1,0.1,1), new AtributesModifiers(0,0,0), 2, false, false));
        skills.add(new Skill("Enfraquecer", new AtributesModifiers(0.3,0.2,0.5), new AtributesModifiers(1, 1, 0.5), 5, false, false));
        skills.add(new Skill("Cura Amigo", new AtributesModifiers(0.5,0.2,0.8), new AtributesModifiers(1,1,0.7), 4, true, false));
    }

    public void levelUp() {
        // Implementação com valores correspondentes à progressão do Main.java.com.dungeonclash.character.classes.Mage.
        this.strength += 1;
        this.agillity += 2;
        this.intelligence += 3;
    }
}