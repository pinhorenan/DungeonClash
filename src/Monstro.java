import java.util.HashSet;

public class Monstro extends Classe {

    public Monstro() {
        // Construtor pré-definido para Monstro
        this.forca = 4;
        this.agilidade = 1;
        this.inteligencia = 0;
        habilidades = new HashSet<>();
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.8, 0.4, 1), new PesosDeAtributos(0, 0, 0), 5, false, false));
        habilidades.add(new Habilidade("Chutar", new PesosDeAtributos(1, 0.5, 1), new PesosDeAtributos(0, 0, 0), 8, false, false));
        habilidades.add(new Habilidade("Grito atordoante", new PesosDeAtributos(0.4, 0.2, 1), new PesosDeAtributos(0, 0, 0), 6, false, true));
    }

    public void subirNivel() {
        // Implementação com valores correspondentes à progressão do Mostro. A principio os monstros não irão upar mas isso foi decidido de última hora e nao faz mal manter o método aqui.
        this.forca += 4;
        this.agilidade += 1;
        this.inteligencia = 0;
    }
}
