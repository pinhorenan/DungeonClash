public class Guerreiro extends Classe {

    public Guerreiro() {
        this.forca = Personagem.getNivel()*4;
        this.agilidade = Personagem.getNivel();
        this.inteligencia = Personagem.getNivel();
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 1), new PesosDeAtributos(0,0,0),4 , false, false));
        habilidades.add(new Habilidade("Golpe de Espada", new PesosDeAtributos(0.7,0.3,1), new PesosDeAtributos(0,0,0),5 , false, false));
        habilidades.add(new Habilidade("Espada Flamejante", new PesosDeAtributos(0.3,0.5,0.4), new PesosDeAtributos(0.2,1, 1),7 , false, false));
    }
}
