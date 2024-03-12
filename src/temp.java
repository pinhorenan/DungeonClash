import java.nio.file.*;
import java.util.Random;
import java.util.Scanner;

public class temp {

    private Equipe herois;
    private Equipe inimigos;
    private Path arquivo;

    // Construtor
    public Jogo(String caminhoArquivo) {
        this.herois = new Equipe(false);
        this.inimigos = new Equipe(true);
        this.arquivo = Paths.get(caminhoArquivo);

        if (!Files.exists(arquivo)) {
            System.out.println("Arquivo não encontrado. Certifique-se de fornecer o caminho correto.");
            System.exit(1);
        }
    }

    public void iniciar() {
        carregarEquipe();
        iniciarBatalha();
    }

    private void carregarEquipe() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Carregando equipes...");

        for (int i = 1; i <= 3; ++i) {
            System.out.println("\nNome do " + i + "º Herói ou Heroína:");
            String nomeHeroi = scanner.nextLine();
            System.out.println("\nQual será a classe de " + nomeHeroi + "?");
            System.out.println("\n1- GUERREIRO\n2- ARQUEIRO\n3- MAGO");
            int escolhaClasse = Integer.parseInt(scanner.nextLine());

            Personagem novoHeroi = switch (escolhaClasse) {
                case 1 -> new Personagem(nomeHeroi, new Guerreiro());
                case 2 -> new Personagem(nomeHeroi, new Arqueiro());
                case 3 -> new Personagem(nomeHeroi, new Mago());
                default -> new Personagem(nomeHeroi, new Guerreiro());
            };

            herois.adicionarIntegrante(novoHeroi);
        }

    }

    private void iniciarBatalha() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int contadorTurnos = 0;

        System.out.println("Iniciando batalha...");

        // Sorteia quem ataca primeiro
        Personagem primeiroAtacante = (random.nextBoolean()) ? herois.definirProximoAtacante() : inimigos.definirProximoAtacante();

        // Inicia os turnos
        while (herois.peloMenosUmVivo() && inimigos.peloMenosUmVivo()) {
            System.out.println("\n--- Turno " + contadorTurnos + " --- ");
            System.out.println("É a vez de " + primeiroAtacante.getNome() + " atacar!");

            // Exibe habilidades disponíveis do personagem que vai atacar
            primeiroAtacante.exibirHabilidades();

            // Escolhe uma habilidade
            Habilidade habilidadeEscolhida = escolherHabilidade(primeiroAtacante);

            // Escolhe um alvo
            Personagem alvo = escolherAlvo(inimigos);

            // Atualiza o tempo de espera de maneira correspondente a sua habilidade.
            primeiroAtacante.atualizarTempoEspera(habilidadeEscolhida.getTempoEspera());

            // Incrementa o contador de turnos
            contadorTurnos++;

            // Troca de atacante para o próximo turno
            primeiroAtacante = (primeiroAtacante.getIsInimigos()) ? herois.definirProximoAtacante() : inimigos.definirProximoAtacante();

            // Exibe informações das equipes
            exibirInformacoesEquipes(herois, inimigos);
        }

        // Exibe o resultado da batalha
        exibirResultadoBatalha(herois, inimigos);
    }

    private Habilidade escolherHabilidade(Personagem personagem) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma habilidade para " + personagem.getNome() + ":");

        // Exibindo as habilidades disponíveis para o personagem
        int i = 1;
        for (Habilidade habilidade : personagem.getClasse().getHabilidades()) {
            System.out.println(i + ". " + habilidade.getNome());
            i++;
        }

        // Capturando a escolha do usuário
        int escolha = Integer.parseInt(scanner.nextLine());

        // Convertendo a escolha do usuário para o índice do conjunto de habilidades
        int indice = escolha - 1;

        // Verificando se a escolha é válida
        if (indice >= 0 && indice < personagem.getClasse().getHabilidades().size()) {
            // Convertendo o conjunto de habilidades para um array para acessar o elemento pelo índice
            Habilidade[] habilidadesArray = personagem.getClasse().getHabilidades().toArray(new Habilidade[0]);
            return habilidadesArray[indice];
        } else {
            System.out.println("Escolha inválida. Por favor, escolha uma habilidade válida.");
            return escolherHabilidade(personagem); // Chamada recursiva para permitir uma nova escolha
        }
    }

    private Personagem escolherAlvo(Equipe equipe) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um alvo:");

        // Exibindo os integrantes da equipe disponíveis como alvos
        int i = 1;
        for (Personagem personagem : equipe.getIntegrantes()) {
            System.out.println(i + ". " + personagem.getNome());
            i++;
        }

        // Capturando a escolha do usuário
        int escolha = Integer.parseInt(scanner.nextLine());

        // Convertendo a escolha do usuário para o índice do conjunto de integrantes da equipe
        int indice = escolha - 1;

        // Verificando se a escolha é válida
        if (indice >= 0 && indice < equipe.getIntegrantes().size()) {
            // Convertendo o conjunto de integrantes para um array para acessar o elemento pelo índice
            Personagem[] integrantesArray = equipe.getIntegrantes().toArray(new Personagem[0]);
            return integrantesArray[indice];
        } else {
            System.out.println("Escolha inválida. Por favor, escolha um alvo válido.");
            return escolherAlvo(equipe); // Chamada recursiva para permitir uma nova escolha
        }
    }

    private void exibirInformacoesEquipes(Equipe herois, Equipe inimigos) {
        System.out.println("\n --- Informações das Equipes ---");
        System.out.println("\nHeróis: ");
        herois.exibirInformacoes();
        System.out.println("\nInimigos: ");
        inimigos.exibirInformacoes();
    }

    private void exibirResultadoBatalha(Equipe herois, Equipe inimigos) {
        // Implementar
    }
}
