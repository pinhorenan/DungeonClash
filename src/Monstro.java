public class Monstro extends Classe {

    public Monstro() {
        this.forca = getNivel() * 4;
        this.agilidade = getNivel();
        this.inteligencia = 0;
        this.habilidades = adicionarHabilidade(atirarFlecha);
    }
}
