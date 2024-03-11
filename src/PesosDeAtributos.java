public class PesosDeAtributos {

   // ------------------------------------------ ATRIBUTOS ------------------------------------------- //
   private final double pesoForca;
   private final double pesoAgilidade;
   private final double pesoInteligencia;

   // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
   public PesosDeAtributos(double pesoForca, double pesoAgilidade, double pesoInteligencia) {
      this.pesoForca = pesoForca;
      this.pesoAgilidade = pesoAgilidade;
      this.pesoInteligencia = pesoInteligencia;
   }

   // -------------------------------------------- GETTERS -------------------------------------------- //
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
