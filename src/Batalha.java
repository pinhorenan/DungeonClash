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

        // Exibe o resultado da batalha
        exibirResultadoBatalha(herois, inimigos);
    }

    private Personagem sortearPrimeiroAtacante(Equipe herois, Equipe inimigos) {
        Random random = new Random();
        return (random.nextBoolean()) ? herois.definirProximoAtacante() : inimigos.definirProximoAtacante();
    }

    private void realizarTurno(Equipe herois, Equipe inimigos, Personagem primeiroAtacante) {
        System.out.println("\n --- Turno " + contadorTurnos + " --- ");
        System.out.println("É a vez de " + primeiroAtacante.getNome() + " atacar!");

        // Exibe habilidades disponíveis do personagem que vai atacar
        primeiroAtacante.exibirHabilidades(); // Implementar

        // Implementar lógica pra escolher a habilidade e o alvo.

        // Atualiza o tempo de espera de maneira correspondente a sua habilidade.
        primeiroAtacante.atualizarTempoEspera(); // Implementar

        // Incrementa o contador de turnos
        contadorTurnos++;
    }

    private void exibirInformacoesEquipes(Equipe herois, Equipe inimigos) {
        System.out.println("\n --- Informações das Equipes ---");
        System.out.println("\nHeróis: ");
        herois.exibirInformacoes(); // Implementar
        System.out.println("\nInimigos: ");
        inimigos.exibirInformacoes(); // Implementar
    }

    private void exibirResultadoBatalha(Equipe herois, Equipe inimigos) {
        // Implementar
    }
}