public class Guerreiro extends Classe {

    // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
    public Guerreiro() {
        this.forca = 4;
        this.agilidade = 1;
        this.inteligencia = 1;
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 1), new PesosDeAtributos(0,0,0),4 , false, false));
        habilidades.add(new Habilidade("Golpe de Espada", new PesosDeAtributos(0.7,0.3,1), new PesosDeAtributos(0,0,0),5 , false, false));
        habilidades.add(new Habilidade("Espada Flamejante", new PesosDeAtributos(0.3,0.5,0.4), new PesosDeAtributos(0.2,1, 1),7 , false, false));
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public void subirNivel() { // Implementação com valores correspondentes à progressão do Guerreiro.
        this.forca += 4;
        this.agilidade += 1;
        this.inteligencia += 1;
    }

} // FECHAMENTO
