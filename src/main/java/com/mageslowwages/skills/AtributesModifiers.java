package main.java.com.mageslowwages.skills;

public class AtributesModifiers {

   // ------------------------------------------ ATRIBUTOS ------------------------------------------- //
   private final double strengthModifier;
   private final double agilityModifier;
   private final double intelligenceModifier;

   // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
   public AtributesModifiers(double strengthModifier, double agilityModifier, double intelligenceModifier) {
      this.strengthModifier = strengthModifier;
      this.agilityModifier = agilityModifier;
      this.intelligenceModifier = intelligenceModifier;
   }

   // -------------------------------------------- GETTERS -------------------------------------------- //
   public double getStrengthModifier() {
      return strengthModifier;
   }

   public double getAgilityModifier() {
      return agilityModifier;
   }

   public double getIntelligenceModifier() {
      return intelligenceModifier;
   }
}
