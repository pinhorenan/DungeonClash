import java.util.Random;

public class Batalha {

    private int contadorTurnos;

    // Construtor
    public Batalha() {
        this.contadorTurnos = 0;
    }

    public void iniciarBatalha(Equipe herois, Equipe inimigos) {
        exibirInformacoesEquipes(herois, inimigos);

        // Sorteia quem ataca primeiro
        Personagem primeiroAtacante = sortearPrimeiroAtacante(herois, inimigos);

        // Inicia os turnos
        while (herois.peloMenosUmVivo() && inimigos.peloMenosUmVivo()) {
            realizarTurno(herois, inimigos, primeiroAtacante);
            exibirInformacoesEquipes(herois, inimigos);
        }
    }

    private Personagem sortearPrimeiroAtacante(Equipe herois, Equipe inimigos) {
        Random random = new Random();
        return (random.nextBoolean()) ? herois.definirProximoAtacante() : inimigos.definirProximoAtacante();
    }
