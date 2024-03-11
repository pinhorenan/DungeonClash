public class Arqueiro extends Classe {

    public Arqueiro() {

        this.forca = Personagem.getNivel();
        this.agilidade = Personagem.getNivel()*3;
        this.inteligencia = Personagem.getNivel()*2;
        PesosDeAtributos danoSocar = new PesosDeAtributos(0.3, 0.1, 1);
        PesosDeAtributos manaSocar = new PesosDeAtributos(0, 0, 0);
        habilidades.add(new Habilidade("Socar", danoSocar, manaSocar, 3, false, false));
        PesosDeAtributos manaAtirarFlecha = new PesosDeAtributos(0, 0, 0);
        habilidades.add(new Habilidade("Atirar Flecha", new PesosDeAtributos(0.3, 0.5, 1), new PesosDeAtributos(0, 0, 0), 4, false, false));

        habilidades.add(new Habilidade("Flecha Encantada", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(1, 0.2, 1), 7, false, false));

    }
}
// habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 1), new PesosDeAtributos(0, 0, 0), 4, false, false));
