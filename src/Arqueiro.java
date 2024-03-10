public class Arqueiro extends Classe {

    // Construtor

    public Arqueiro() {
        this.forca = Personagem.getNivel();
        this.agilidade = Personagem.getNivel()*3;
        this.inteligencia = getNivel()*2;
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 1), new PesosDeAtributos(0,0,0),4, false, false ));
        habilidades.add(new Habilidade("Atirar Flecha", new PesosDeAtributos(0.3, 0.5, 1), new PesosDeAtributos(0, 0, 0), false, false));
        habilidades.add(new Habilidade("Flecha Encantada", new PesosDeAtributos(0.3, 0.5, 0.4), 7 ));
    }
    // Adicione métodos específicos do arqueiro, se necessário

    // Métodos para as habilidades específicas

}

//         habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 1), new PesosDeAtributos(0,0,0),4, false, false ));
