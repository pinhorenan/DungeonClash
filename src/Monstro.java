public class Monstro extends Classe {

    // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
    public Monstro() {
        this.forca = 4;
        this.agilidade = 1;
        this.inteligencia = 0;
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.8, 0.4, 1), new PesosDeAtributos(0, 0, 0), 5, false, false));
        habilidades.add(new Habilidade("Chutar", new PesosDeAtributos(1, 0.5, 1), new PesosDeAtributos(0, 0, 0), 8, false, false));
        habilidades.add(new Habilidade("Grito atordoante", new PesosDeAtributos(0.4, 0.2, 1), new PesosDeAtributos(0, 0, 0), 6, false, true));
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public void subirNivel() { // Implementação com valores correspondentes à progressão do Mostro.
        this.forca += 4;
        this.agilidade += 1;
        this.inteligencia = 0;
    }

} // FECHAMENTO
