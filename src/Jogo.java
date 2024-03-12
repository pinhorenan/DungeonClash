import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Jogo {

  private Equipe herois;
  private Equipe inimigos;
  private Path arquivo;

  public Jogo(String caminhoArquivo) {
    this.herois = new Equipe(false);
    this.inimigos = new Equipe(true);
    this.arquivo = Paths.get(caminhoArquivo);

    if (!Files.exists(arquivo)) {
      System.out.println("Arquivo não encontrado. Certifique-se de fornecer o caminho correto.");
      System.exit(1);
    }
    /*try {
      List<String> linhasArquivo = Files.readAllLines(arquivo);
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
      System.exit(1);
    }*/ // CÓDIGO DO PURO OSSO TA AQUI DENTRO
  }

  public void iniciar() {
    carregarEquipe();
    iniciarBatalha();
  }

    /* APOS ISSO, DEVE ABRIR O ARQUIVO E TRANSFORMAR SEU CONTEUDO EM FASES
    A PRIMEIRA LINHA DA FASE DEVE SER PRINTADA
    A SEGUNDA LINHA DEVE SER TRANSFORMADA NA EQUIPE INIMIGA, COM CADA ITEM SENDO UM INTEGRANTE
    APOS UMA FASE ACABAR, A PROXIMA DEVE COMEÇAR (FAZER UM SET DE FASES???????????)*/ // COMENTÁRIO DO PURO OSSO??

  public void carregarEquipe() {
    Scanner scanner = new Scanner(System.in);
    boolean criacaoPersonagens;

    for (int i = 1; i <= 3; ++i) {
      System.out.println("\nNome do " + i + "º Herói ou Heroína: ");
      String nomeHeroi = scanner.nextLine();
      System.out.println("\nQual será a classe de " + nomeHeroi + "?");
      System.out.println("\n1- GUERREIRO\n2- ARQUEIRO\n3- MAGO");
      int escolhaClasse = 0;
      do {
        try {
          escolhaClasse = Integer.parseInt(scanner.nextLine());
          if (escolhaClasse <= 3 && escolhaClasse >= 1) {
          break;         // Saia do loop se a entrada for válida
        } else {
          System.out.println("Escolha inválida! Tente novamente.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Input inválido! Tente de novo.");
        }
      } while (true);

      Personagem novoHeroi = switch (escolhaClasse) {
        case 1 -> new Personagem(nomeHeroi, new Guerreiro());
        case 2 -> new Personagem(nomeHeroi, new Arqueiro());
        case 3 -> new Personagem(nomeHeroi, new Mago());
        default -> new Personagem(nomeHeroi, new Guerreiro());
      };

      herois.adicionarIntegrante(novoHeroi);

      System.out.println("\nDeseja criar mais um personagem?");
      System.out.println("\nPara NÃO digite 'False'\nPara SIM digite 'True'");
      Boolean criacaoPersonagens = false;
      Scanner scan = new Scanner(System.in)
      do {
        try {
          criacaoPersonagens = scan.nextBoolean();
          if (!criacaoPersonagens) {
            i = 4;
          }
        } catch (InputMismatchException e) {
          System.out.println("Input inválido! Tente de novo.");
          criacaoPersonagens = false;
          scanner.nextLine();
        }
      } while (criacaoPersonagens != false);
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

}

