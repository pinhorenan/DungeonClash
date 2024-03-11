public class Mago extends Classe{

    // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
    public Mago()  {
        this.forca = 1;
        this.agilidade = 2;
        this.inteligencia = 3;
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.1,0.1,1), new PesosDeAtributos(0,0,0), 2, false, false));
        habilidades.add(new Habilidade("Enfraquecer", new PesosDeAtributos(0.3,0.2,0.5), new PesosDeAtributos(1, 1, 0.5), 5, false, false));
        habilidades.add(new Habilidade("Cura Amigo", new PesosDeAtributos(0.5,0.2,0.8), new PesosDeAtributos(1,1,0.7), 4, true, false));
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public void subirNivel() { // Implementação com valores correspondentes à progressão do Mago.
        this.forca += 1;
        this.agilidade += 2;
        this.inteligencia += 3;
    }

} // FECHAMENTO
