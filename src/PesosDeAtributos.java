public class PesosDeAtributos {
   private double pesoForca;
   private double pesoAgilidade;
   private double pesoInteligencia;
   private int pesoMana;

   public PesosDeAtributos(double pesoForca, double pesoAgilidade, double pesoInteligencia) {
      this.pesoForca = pesoForca;
      this.pesoAgilidade = pesoAgilidade;
      this.pesoInteligencia = pesoInteligencia;
   }

   // Getters


   public int getPesoMana() {
      return pesoMana;
   }

   public double getPesoForca() {
      return pesoForca;
   }

   public double getPesoAgilidade() {
      return pesoAgilidade;
   }

   public double getPesoInteligencia() {
      return pesoInteligencia;
   }
}



