import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

  private final Equipe herois;
  private final Equipe inimigos;
  private List<String> linhasArquivo;
  private int contadorTurnos;

  public Jogo(String caminhoArquivo) throws IOException {
      // Construtor
    this.herois = new Equipe(false);
    this.inimigos = new Equipe(true);
    Path arquivo = Paths.get(caminhoArquivo);
    this.linhasArquivo = Files.readAllLines(arquivo);
    this.contadorTurnos = 1;

    if (!Files.exists(arquivo)) {
      System.out.println("Arquivo não encontrado. Certifique-se de fornecer o caminho correto.");
      System.exit(1);
    }

    try {
      this.linhasArquivo = Files.readAllLines(arquivo);
    } catch (IOException excecao) {
      System.err.println("Erro ao ler o arquivo: " + excecao.getMessage());
      System.exit(1);
    }
  }

  public void iniciar() {
      // Prólogo
    carregarHerois();

      // História
    String procurarPor = "fase";
    boolean verificacao = false;
    for (String linha : linhasArquivo){
        if (!(linha.toLowerCase().contains(procurarPor.toLowerCase()))){
          carregarInimigos(linha);
          verificacao = true;
        } else {
          if (verificacao){
            iniciarBatalha();
            verificacao = false;
          }
          System.out.println("\n" + linha.substring(5));
        }
    }
      // Epílogo
  }

  private void carregarHerois() {
      // Utilizada para receber a entrada do usuário na hora de criar heróis. Chamada no inicio do jogo.
    Scanner scanner = new Scanner(System.in);

    for (int i = 1; i <= 3; ++i) {
      String nomeHeroi;
      do {
        System.out.println("\nNome do " + i + "º Herói ou Heroína: ");
        nomeHeroi = scanner.nextLine();

        // Verifica se já existe um personagem com o mesmo nome na equipe
        boolean nomeExiste = false;
        for (Personagem integrante : herois.getIntegrantes()) {
          if (integrante.getNome().equalsIgnoreCase(nomeHeroi)) {
            nomeExiste = true;
            System.out.println("Um personagem com este nome já existe na equipe. Por favor, escolha outro nome.");
            break;
          }
        }
        if (!nomeExiste) {
          break; // Sai do loop se o nome for válido
        }
      } while (true);

      System.out.println("\nQual será a classe de " + nomeHeroi + "?");
      System.out.println("\n1- GUERREIRO\n2- ARQUEIRO\n3- MAGO");
      int escolhaClasse;
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
        default -> throw new IllegalStateException("Valor inválido: " + escolhaClasse);
      };

      herois.adicionarIntegrante(novoHeroi);

        Scanner scan = new Scanner(System.in);

      while (i <= 2) {
        System.out.println("\nDeseja criar mais um personagem?");
        try {
          System.out.println("Digite 'sim' se deseja criar mais um personagem ou 'nao' para sair:");
          String resposta = scan.nextLine().toLowerCase();
          if (resposta.equals("nao")) {
            i = 4;
          } else if (!resposta.equals("sim")) {
            throw new InputMismatchException();
          }
          break;
        } catch (InputMismatchException e) {
          System.out.println("Input inválido! Tente de novo.");
          scan.nextLine(); // Limpa o buffer
        }
    }
  }
  System.out.println("\nEquipe criada!");
}

  private void carregarInimigos(String linha) {
    String[] split = linha.split(" ", 3);

    String nomeMonstro = split[0];
    String classeMonstro = split[1];
    int nivelMonstro = Integer.parseInt(split[2]);

    // Verifica se já existe um monstro com o mesmo nome na equipe
    for (Personagem integrante : inimigos.getIntegrantes()) {
      if (integrante.getNome().equalsIgnoreCase(nomeMonstro)) {
        nomeMonstro += "_1"; // Adiciona um sufixo ao nome se já existir
        break;
      }
    }

    Personagem novoMonstro = switch (classeMonstro.toLowerCase()) {
      case "guerreiro" -> new Personagem(nomeMonstro, new Guerreiro(), nivelMonstro);
      case "arqueiro" -> new Personagem(nomeMonstro, new Arqueiro(), nivelMonstro);
      case "mago" -> new Personagem(nomeMonstro, new Mago(), nivelMonstro);
      case "monstro" -> new Personagem(nomeMonstro, new Monstro(), nivelMonstro);
      default -> throw new IllegalArgumentException("Classe inválida: " + classeMonstro);
    };

    inimigos.adicionarIntegrante(novoMonstro);
  }

  private void iniciarBatalha() {
    System.out.println("Iniciando batalha...");

    // Sorteia de quem ataca
    Personagem atacante = sortearPrimeiroAtacante(herois, inimigos);

    while (herois.peloMenosUmVivo() && inimigos.peloMenosUmVivo()) {

      // Trocar o atacante para o próximo turno
      atacante = (herois.getIntegrantes().contains(atacante)) ? inimigos.definirProximoAtacante() : herois.definirProximoAtacante();

      exibirInformacoesEquipes(herois, inimigos);
      realizarTurno(herois, inimigos, atacante);
    }

    // Exibe o resultado da batalha
    if(exibirResultadoBatalha(inimigos)) {
      contadorTurnos = 1;
      for(Personagem personagem : herois.getIntegrantes()){
        personagem.setPV(personagem.getPVmax());
        personagem.setPM(personagem.getPMmax());
      }
    } else System.exit(1);
  }

  private void realizarTurno(Equipe herois, Equipe inimigos, Personagem atacante) {
    System.out.println("\n --- Turno " + contadorTurnos + " --- ");

    // Verifica se o atacante possui tempo de espera
    if(!turnoSilencioso(herois, inimigos)) {
      // Imprime o nome do atacante
        System.out.println("É a vez de " + atacante.getNome() + " atacar!");

        // Escolhe uma habilidade
        Habilidade habilidadeEscolhida = escolherHabilidade(atacante);

        // Escolhe um alvo
        Equipe equipeAlvo = (herois.getIntegrantes().contains(atacante)) ? inimigos : herois;
        Personagem alvo = escolherAlvo(equipeAlvo);

      // Usa a habilidade escolhida
      atacante.usarHabilidade(habilidadeEscolhida, alvo);

        // Distribui possível PE vindo de possíveis atordoamentos da habilidade usada no turno
      if (alvo.getAtordoado() && herois.getIntegrantes().contains(atacante)) {
        System.out.println("Devido à morte do inimigo, todos os heróis recebem PE.");
        for (Personagem personagem : herois.getIntegrantes()) {
          int nivelAntes = personagem.getNivel();
          personagem.calcularPE(alvo);
          int nivelDepois = personagem.getNivel();
          if (nivelDepois > nivelAntes) {
            System.out.println(personagem.getNome() + " subiu para o nível " + nivelDepois + "!");
          }
        }
      } else {
        System.out.println("Nenhum alvo válido encontrado.");
        }

        // Atualiza o tempo de espera de maneira correspondente a sua habilidade
        atacante.atualizarTempoEspera(habilidadeEscolhida.getTempoEspera());
    }

    // Decrementa o tempo de espera de todos os personagens ao final do turno
    herois.decrementaTempoEspera();
    inimigos.decrementaTempoEspera();

    // Incrementa o contador de turnos
    contadorTurnos++;
  }

  private void exibirHabilidades(Personagem personagem) {
    System.out.println("Habilidades disponíveis para " + personagem.getNome() + ":\n");

    for (Habilidade habilidade : personagem.getClasse().getHabilidades()) {
      System.out.println(habilidade.getNome() + " (PM Necessário: " + habilidade.calcularCustoMana(personagem.getClasse()) + ", Dano: " + habilidade.calcularDanoCausado(personagem.getClasse()) + " Cooldown: " + habilidade.getTempoEspera() + " turnos)\n");
    }
  }

  private void exibirInformacoes(Equipe equipe) {
    for (Personagem integrante : equipe.getIntegrantes()) {
      if(!integrante.getAtordoado()) {
        System.out.println("ID: " + integrante.getID());
        System.out.println("Nome: " + integrante.getNome());
        System.out.println("Classe: " + integrante.getNomeClasse());
        System.out.println("PV: " + integrante.getPV());
        System.out.println("PV Máximo: " + integrante.getPVmax());
        System.out.println("PM: " + integrante.getPM());
        System.out.println("PM Máximo: " + integrante.getPMmax());
        System.out.println("Nível: " + integrante.getNivel());
        System.out.println("Tempo de Espera: " + integrante.getTempoEspera());

        System.out.println("---------------------------------------");
      }
    }
  }

  private void exibirInformacoesEquipes(Equipe herois, Equipe inimigos) {
    System.out.println("\n --- Informações das Equipes ---");
    System.out.println("\nHeróis: ");
    exibirInformacoes(herois);
    System.out.println("\nInimigos: ");
    exibirInformacoes(inimigos);
  }

  private boolean exibirResultadoBatalha(Equipe inimigos) {
    if (!inimigos.peloMenosUmVivo()) {
      System.out.println("Parabéns! Você venceu a batalha!");
      return true;
    } else {
      System.out.println("Game over! Você foi derrotado!");
      return false;
    }
  }

  private boolean turnoSilencioso(Equipe herois, Equipe inimigos) {
    for (Personagem personagem : herois.getIntegrantes())
      if (personagem.getTempoEspera() == 0 || !personagem.getAtordoado()){
        return false;
      }
    for (Personagem personagem : inimigos.getIntegrantes()){
      if (personagem.getTempoEspera() == 0 || !personagem.getAtordoado()){
        return false;
      }
    }
    return true;
  }

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

  private Personagem sortearPrimeiroAtacante(Equipe herois, Equipe inimigos) {
    Random random = new Random();
    return (random.nextBoolean()) ? herois.definirProximoAtacante() : inimigos.definirProximoAtacante();
  }

  private Personagem escolherAlvo(Equipe equipe) {
    Scanner scanner = new Scanner(System.in);

    // Exibir lista de alvos disponíveis
    System.out.println("Escolha um alvo:");
    for (Personagem integrante : equipe.getIntegrantes()) {
      if (!integrante.getAtordoado()) {
        System.out.println(integrante.getNome());
      }
    }

    // Solicitar ao jogador a escolha do alvo
    while (true) {
      System.out.print("Digite o nome do alvo: ");
      String nomeAlvo = scanner.nextLine();

      // Verifica se o alvo escolhido está na lista de alvos disponíveis
      for (Personagem integrante : equipe.getIntegrantes()) {
        if (integrante.getNome().equalsIgnoreCase(nomeAlvo) && !integrante.getAtordoado()) {
          return integrante; // Retorna o alvo escolhido
        }
      }

      // Se o nome do alvo não foi encontrado, exibe uma mensagem de erro
      System.out.println("Alvo inválido! Por favor, escolha um alvo da lista.");
    }
  }
}
