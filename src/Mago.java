public class Mago extends Classe{

    public Mago() {
        this.forca = super.getNivel();
        this.agilidade = super.getNivel()*2;
        this.inteligencia = super.getNivel()*3;
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.1,0.1,1), new PesosDeAtributos(0,0,0), 2, false, false));
        habilidades.add(new Habilidade("Enfraquecer", new PesosDeAtributos(0.3,0.2,0.5), new PesosDeAtributos(1, 1, 0.5), 5, false, false));
        habilidades.add(new Habilidade("Cura Amigo", new PesosDeAtributos(0.5,0.2,0.8), new PesosDeAtributos(1,1,0.7), 4, true, false));
    }
}
