import java.util.List;

public class Arqueiro extends Classe {

    // Construtor

    public Arqueiro() {
        this.forca = getNivel();
        this.agilidade = getNivel()*3;
        this.inteligencia = getNivel()*2;
        habilidades.add(new Habilidade("Atirar Flecha", new PesosDeAtributos(0.3, 0.5, 0), 3, false, false));
        habilidades.add(new Habilidade())
    }// SINCERAMENTE QUE ESPECIFICACAO MERDA ESSA PARTE DE BOTAR TIPO PESOS DE ATRIBUTOS COMO REQUISITO P PARAMETRO DE HABILIDADE QUE IMPLEMENTACAO BURRA PQ Q A GENTE EH OBRIGADO A FAZER ASSIM AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

    // Adicione métodos específicos do arqueiro, se necessário

    // Métodos para as habilidades específicas

}
