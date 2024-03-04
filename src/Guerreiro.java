public class Guerreiro extends Classe {

    public Guerreiro() {
        this.forca = getNivel()*4;
        this.agilidade = getNivel();
        this.inteligencia = getNivel();
        this.habilidades = adicionarHabilidade(socar);
        this.habilidades = adicionarHabilidade(golpeDeEspada);
        this.habilidades = adicionarHabilidade(espadaFlamejanta);
    }
}
