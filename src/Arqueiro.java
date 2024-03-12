public class Arqueiro extends Classe {

    public Arqueiro() {
        // Construtor pré-definido para Arqueiro
        this.forca = 1;
        this.agilidade = 3;
        this.inteligencia = 2;
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 1), new PesosDeAtributos(0, 0, 0), 3, false, false));
        habilidades.add(new Habilidade("Atirar Flecha", new PesosDeAtributos(0.3, 0.5, 1), new PesosDeAtributos(0, 0, 0), 4, false, false));
        habilidades.add(new Habilidade("Flecha Encantada", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(1, 0.2, 1), 7, false, false));
    }

    public void subirNivel() {
        // Implementação com valores correspondentes à progressão do Arqueiro.
        this.forca += 1;
        this.agilidade += 3;
        this.inteligencia += 2;
    }
}