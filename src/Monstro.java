public class Monstro extends Classe {

    public Monstro() {
        this.forca = Personagem.getNivel() * 4;
        this.agilidade = Personagem.getNivel();
        this.inteligencia = 0; // O MONSTRO NUNCA UPA INTELIGÊNCIA "BURRO COMO UMA PORTA"
        habilidades.add(new Habilidade("Socar", new PesosDeAtributos(0.8, 0.4, 1), new PesosDeAtributos(0, 0, 0), 5, false, false));
        habilidades.add(new Habilidade("Chutar", new PesosDeAtributos(1, 0.5, 1), new PesosDeAtributos(0, 0, 0), 8, false, false));
        habilidades.add(new Habilidade("Grito atordoante", new PesosDeAtributos(0.4, 0.2, 1), new PesosDeAtributos(0, 0, 0), 6, false, true));
    }
}
