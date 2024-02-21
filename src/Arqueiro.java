import java.util.List;

public class Arqueiro extends Classe {

    // Construtor

    public Arqueiro() {
        this.forca = getNivel();
        this.agilidade = getNivel()*3;
        this.inteligencia = getNivel()*2;
        this.habilidades = adicionarHabilidade(atirarFlecha);
    }

    // Adicione métodos específicos do arqueiro, se necessário

    // Métodos para as habilidades específicas

}
