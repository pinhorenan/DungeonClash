import java.nio.file.*;
import java.util.*;

public class Jogo {

  private Equipe herois;
  private Equipe[] inimigos;
  private Path arquivo;
  private List<String> linhasArquivo;
  private int contadorTurnos;


  // Construtor
  public Jogo(String caminhoArquivo) {
    this.herois = new Equipe(false);
    this.inimigos = new Equipe(true);
    this.arquivo = Paths.get(caminhoArquivo);
    this.linhasArquivo = Files.readAllLines(arquivo);
    this.contadorTurnos = 0;

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
    // Prólogo
    carregarHerois();

    // História
    int i = 0;
    for (String linha : linhasArquivo){
        if (linha.toLowerCase().contains(procurarPor.toLowerCase())){;
            if (i != 0){
              iniciarBatalha();
            }
            System.out.println(linha.substring(4));
            i++;
        } else {
            carregarInimigos(linha, i);
        }
    }

    // Epílogo
  }

    /* APOS ISSO, DEVE ABRIR O ARQUIVO E TRANSFORMAR SEU CONTEUDO EM FASES
    A PRIMEIRA LINHA DA FASE DEVE SER PRINTADA
    A SEGUNDA LINHA DEVE SER TRANSFORMADA NA EQUIPE INIMIGA, COM CADA ITEM SENDO UM INTEGRANTE
    APOS UMA FASE ACABAR, A PROXIMA DEVE COMEÇAR (FAZER UM SET DE FASES???????????)*/ // COMENTÁRIO DO PURO OSSO??

  public void carregarHerois() {
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
      criacaoPersonagens = false;
      Scanner scan = new Scanner(System.in);
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
      } while (!criacaoPersonagens);
    }
  }

  public void carregarInimigos(String linha, int index) {
    String[] split = linha.split(" ", 3);
    
    String nomeMonstro = split[0];
    String classeMonstro = split[1];
    int nivelMonstro = integer.value0f(split[2]);

  private void iniciarBatalha() {
    System.out.println("Iniciando batalha...");

    // Exibe informações iniciais das equipes
    exibirInformacoesEquipes(herois, inimigos);

    // Sorteia quem ataca primeiro
    Personagem atacante = sortearPrimeiroAtacante(herois, inimigos);

    // Inicia os turnos
    while (herois.peloMenosUmVivo() && inimigos.peloMenosUmVivo()) {
      realizarTurno(herois, inimigos, atacante);
      exibirInformacoesEquipes(herois, inimigos);

      // Trocar o atacante para o próximo turno
      atacante = (herois.getIntegrantes().contains(atacante)) ? inimigos.definirProximoAtacante() : herois.definirProximoAtacante();
    }

    // Exibe o resultado da batalha
    exibirResultadoBatalha(herois, inimigos);
  }

  //ok
  private void realizarTurno(Equipe herois, Equipe inimigos, Personagem atacante) {
    System.out.println("\n --- Turno " + contadorTurnos + " --- ");
    System.out.println("É a vez de " + atacante.getNome() + " atacar!");

    // Exibe habilidades disponíveis do personagem que vai atacar:
    exibirHabilidades(atacante);

    // Escolhe uma habilidade:
    Habilidade habilidadeEscolhida = escolherHabilidade(atacante);

    //  Escolha um alvo:
    Equipe equipeAlvo = (herois.getIntegrantes().contains(atacante)). ? inimigos : herois;
    Personagem alvo = escolherAlvo(equipeAlvo);

    // Executa a habilidade no alvo
    atacante.usarHabilidade(habilidadeEscolhida, alvo);

    // Atualiza o tempo de espera de maneira correspondente a sua habilidade.
    atacante.atualizarTempoEspera(habilidadeEscolhida.getTempoEspera());

    // Incrementa o contador de turnos
    contadorTurnos++;
  }

  //ok
  public void exibirHabilidades(Personagem personagem) {
    System.out.println("Habilidades disponíveis para " + personagem.getNome() + ":");

    for (Habilidade habilidade : personagem.getClasse().getHabilidades()) {
      System.out.println(habilidade.getNome());
    }
  }

  //ok
  public void exibirInformacoes(Equipe equipe) {
    for (Personagem integrante : equipe.getIntegrantes()) {
      System.out.println("ID: " + integrante.getID());
      System.out.println("Nome: " + integrante.getNome());
      System.out.println("Classe: " + integrante.getClasse());
      System.out.println("PV: " + integrante.getPV());
      System.out.println("PM: " + integrante.getPM());
      System.out.println("Nível: " + integrante.getNivel());
      System.out.println("Tempo de Espera: " + integrante.getTempoEspera());
      System.out.println("---------------------------------------");
    }
  }

  //ok
  private void exibirInformacoesEquipes(Equipe herois, Equipe inimigos) {
    System.out.println("\n --- Informações das Equipes ---");
    System.out.println("\nHeróis: ");
    exibirInformacoes(herois);
    System.out.println("\nInimigos: ");
    exibirInformacoes(inimigos);
  }

  //ok
  private void exibirResultadoBatalha(Equipe herois, Equipe inimigos) {
    if (!inimigos.peloMenosUmVivo()) {
      System.out.println("Parabéns! Você venceu a batalha!");
    } else {
      System.out.println("Game over! Você foi derrotado!");
    }
  }

  //ok
  private Habilidade escolherHabilidade(Personagem personagem) {
    Scanner scanner = new Scanner(System.in);

    // Mostra as habilidades disponíveis para o personagem
    System.out.println("Escolha uma habilidade para " + personagem.getNome() + ":");
    exibirHabilidades(personagem);

    // Solicita a entrada do usuário até que uma habilidade válida seja escolhida
    while (true) {
      System.out.print("Digite o nome da habilidade: ");
      String nomeHabilidade = scanner.nextLine();

      // Verifica se a habilidade escolhida está na lista de habilidades do personagem
      for (Habilidade habilidade : personagem.getClasse().getHabilidades()) {
        if (habilidade.getNome().equalsIgnoreCase(nomeHabilidade)) {
          return habilidade; // Retorna a habilidade escolhida
        }
      }

      // Se o nome da habilidade não foi encontrado, exibe uma mensagem de erro
      System.out.println("Habilidade inválida! Por favor, escolha uma habilidade da lista.");
    }
  }

  //ok
  private Personagem sortearPrimeiroAtacante(Equipe herois, Equipe inimigos) {
    Random random = new Random();
    return (random.nextBoolean()) ? herois.definirProximoAtacante() : inimigos.definirProximoAtacante();
  }
  //ok
  private Personagem escolherAlvo(Equipe equipe) {
    Scanner scanner = new Scanner(System.in);
    Set<Personagem> integrantes = equipe.getIntegrantes();

    // Exibir lista de alvos disponíveis
    System.out.println("Escolha um alvo:");
    int index = 1;
    for (Personagem integrante : integrantes) {
      System.out.println(index + ". " + integrante.getNome());
      index++;
    }

    // Solicitar ao jogador a escolha do alvo
    int escolha = 0;
    do {
      System.out.print("Digite o número correspondente ao alvo: ");
      try {
        escolha = Integer.parseInt(scanner.nextLine());
        if (escolha < 1 || escolha > integrantes.size()) {
          System.out.println("Escolha inválida. Digite um número válido.");
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Digite um número válido.");
      }
    } while (true);

    // Retornar o alvo escolhido
    int count = 1;
    for (Personagem integrante : integrantes) {
      if (count == escolha) {
        return integrante;
      }
      count++;
    }

    // Caso algo inesperado ocorra, retornamos null
    return null;
  }

  //not ok
  //private Set<Personagem> escolherAlvo(Equipe equipe) { return null;}

}
