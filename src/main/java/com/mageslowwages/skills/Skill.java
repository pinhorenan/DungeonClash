package main.java.com.mageslowwages.skills;

import main.java.com.mageslowwages.character.classes.CharacterClass;

public class Skill {

    private final String name;
    private final AtributesModifiers strengthModifiers;
    private final AtributesModifiers manaModifiers;
    private final int waitTime;
    private final boolean canTargetGroups;
    private final boolean canTargetAllies;
    private final int ID;
    private static int nextID = 1;

    public Skill(String name, AtributesModifiers strengthModifiers, AtributesModifiers manaModifiers, int waitTime, boolean canTargetAllies, boolean canTargetGroups) {
        // Construtor completo da Main.java.com.dungeonclash.skills.Skill. Deverá ser chamado no momento de instanciamento de uma classe herdeira de "Main.java.com.dungeonclash.character.classes.CharacterClass".
        ID = getNexID();
        this.name = name;
        this.waitTime = waitTime;
        this.strengthModifiers = strengthModifiers;
        this.manaModifiers = manaModifiers;
        this.canTargetGroups = canTargetGroups;
        this.canTargetAllies = canTargetAllies;
    }

    public float getDamageDealt(CharacterClass characterClass) {
        // Retorna a "formula" de calculo de dano da Main.java.com.dungeonclash.skills.Skill. Chamado pelo método "danoCausado()" de "Main.java.com.dungeonclash.character.Character".
        float agillity = (float) characterClass.getAgillity();
        float intelligence = (float) characterClass.getIntelligence();
        float strength = (float) characterClass.getStrength();
        float strengthModifier = (float) strengthModifiers.getStrengthModifier();
        float agilityModifier = (float) strengthModifiers.getAgilityModifier();
        float intelligenceModifier = (float) strengthModifiers.getIntelligenceModifier();
        return (float) Math.ceil((strength * strengthModifier) + (agillity * agilityModifier) + (intelligence * intelligenceModifier));
    }

    public float getManaCost(CharacterClass characterClass) {
        // Retorna a "formula" de calculo de custo de mana da Main.java.com.dungeonclash.skills.Skill. Chamado pelo método "custoMagia()" de "Main.java.com.dungeonclash.character.Character".
        float agillity = (float) characterClass.getAgillity();
        float intelligence = (float) characterClass.getIntelligence();
        float strength = (float) characterClass.getStrength();
        float strengthModifier = (float) manaModifiers.getStrengthModifier();
        float agilityModifier = (float) manaModifiers.getAgilityModifier();
        float intelligenceModifier = (float) manaModifiers.getIntelligenceModifier();
        return (float)Math.ceil((strength * strengthModifier) + (agillity * agilityModifier) + (intelligence * intelligenceModifier));
    }

    private static int getNexID() {
        // Retorna um incremento do ID da última Main.java.com.dungeonclash.skills.Skill instanciada, garantindo ID's únicos para cada Main.java.com.dungeonclash.skills.Skill.
        return nextID++;
    }

    public String getName() {
        // Retorna o nome da Main.java.com.dungeonclash.skills.Skill.
        return name;
    }

    public int getWaitTime() {
        // Retorna o Tempo de Espera relacionado à Main.java.com.dungeonclash.skills.Skill.
        return waitTime;
    }

    public boolean getCanTargetGroups() {
        // Retorna se a Main.java.com.dungeonclash.skills.Skill pode ser usada contra grupos.
        return canTargetGroups;
    }

    public boolean getCanTargetAllies() {
        // Retorna se a Main.java.com.dungeonclash.skills.Skill pode ser usada em integrantes da mesma Main.java.com.dungeonclash.groups.Group do Main.java.com.dungeonclash.character.Character.
        return canTargetAllies;
    }

    public int getID() {
        // Retorna o ID da Main.java.com.dungeonclash.skills.Skill.
        return ID;
    }
}