public class Mago extends Classe{

    public Mago() {
        this.forca = getNivel();
        this.agilidade = getNivel()*2;
        this.inteligencia = getNivel()*3;
        this.habilidades = adicionarHabilidade("Atirar Flecha", new PesosDeAtributos());
    }

}
