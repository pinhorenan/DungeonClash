import java.io.IOException;
import java.nio.file.*;
import java.util.*;

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
    this.contadorTurnos = 0;

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
    boolean criacaoPersonagens;

    for (int i = 1; i <= 3; ++i) {
      System.out.println("\nNome do " + i + "º Herói ou Heroína: ");
      String nomeHeroi = scanner.nextLine();
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
          System.out.println("Digite 'true' se deseja criar mais um personagem ou 'false' para sair:");
          criacaoPersonagens = scan.nextBoolean();
          if (!criacaoPersonagens) {
            i = 4;
          }
            break;
        } catch (InputMismatchException e) {
          System.out.println("Input inválido! Tente de novo.");
          scan.nextLine(); // Clear the input buffer
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

    Personagem novoMonstro = null;
    switch (classeMonstro.toLowerCase()) {
      case "guerreiro":
        novoMonstro = new Personagem(nomeMonstro, new Guerreiro(), nivelMonstro);
        break;
      case "arqueiro":
        novoMonstro = new Personagem(nomeMonstro, new Arqueiro(), nivelMonstro);
        break;
      case "mago":
        novoMonstro = new Personagem(nomeMonstro, new Mago(), nivelMonstro);
        break;
      case "monstro":
        novoMonstro = new Personagem(nomeMonstro, new Monstro(), nivelMonstro);
        break;
      default:
        // Lógica para tratamento de classe desconhecida
        break;
    }

    inimigos.adicionarIntegrante(novoMonstro);
  }

  private void iniciarBatalha() {
      System.out.println("Iniciando batalha...");

        // Exibe informações iniciais das equipes
      exibirInformacoesEquipes(herois, inimigos);


        // Sorteia de quem ataca
      Personagem atacante = sortearPrimeiroAtacante(herois, inimigos);

      while (herois.peloMenosUmVivo() && inimigos.peloMenosUmVivo()) {
        realizarTurno(herois, inimigos, atacante);
        exibirInformacoesEquipes(herois, inimigos);

        // Trocar o atacante para o próximo turno
        atacante = (herois.getIntegrantes().contains(atacante)) ? inimigos.definirProximoAtacante() : herois.definirProximoAtacante();
      }

        // Exibe o resultado da batalha
      exibirResultadoBatalha(inimigos);

        // Remover inimigos no fim da batalha
      for (Personagem personagem : inimigos.getIntegrantes()) {
        inimigos.removerIntegrante(personagem);
      }
  }

  private void realizarTurno(Equipe herois, Equipe inimigos, Personagem atacante) {
    System.out.println("\n --- Turno " + contadorTurnos + " --- ");

    int acumuloPE;

    // Verifica se o atacante possui tempo de espera
    if(!turnoSilencioso(herois, inimigos)) {
        // Imprime
        System.out.println("É a vez de " + atacante.getNome() + " atacar!");

        // Escolhe uma habilidade
        Habilidade habilidadeEscolhida = escolherHabilidade(atacante);

        // Escolhe um alvo
        Equipe equipeAlvo = (herois.getIntegrantes().contains(atacante)) ? inimigos : herois;
        Personagem alvo = escolherAlvo(equipeAlvo);

        // Executa a habilidade no alvo
        if (alvo != null) {
          atacante.usarHabilidade(habilidadeEscolhida, alvo);
          acumuloPE = atacante.usarHabilidade(habilidadeEscolhida, alvo);
        } else {
          acumuloPE = 0;
          System.out.println("Nenhum alvo válido encontrado.");
        }

        // Distribui possível PE vindo de possíveis atordoamentos da habilidade usada no turno
        if (equipeAlvo == inimigos) {
          herois.distribuirPE(acumuloPE);
        } else if(turnoSilencioso(herois, inimigos)) {
          System.out.println("Não há ninguém que possa atacar neste turno.");
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
    System.out.println("Habilidades disponíveis para " + personagem.getNome() + ":");

    for (Habilidade habilidade : personagem.getClasse().getHabilidades()) {
      System.out.println(habilidade.getNome() + " (PM Necessário: " + habilidade.calcularCustoMana(personagem.getClasse())+", Dano: " + habilidade.calcularDanoCausado(personagem.getClasse()) +")");
    }
  }

  private void exibirInformacoes(Equipe equipe) {
    for (Personagem integrante : equipe.getIntegrantes()) {
      if(!integrante.getAtordoado()) {
        System.out.println("ID: " + integrante.getID());
        System.out.println("Nome: " + integrante.getNome());
        System.out.println("Classe: " + integrante.getNomeClasse());
        System.out.println("PV: " + integrante.getPV());
        System.out.println("PM: " + integrante.getPM());
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

  private void exibirResultadoBatalha(Equipe inimigos) {
    if (!inimigos.peloMenosUmVivo()) {
      System.out.println("Parabéns! Você venceu a batalha!");
    } else {
      System.out.println("Game over! Você foi derrotado!");
      System.exit(1);
    }
  }

  private boolean turnoSilencioso(Equipe herois, Equipe inimigos) {
    for (Personagem personagem : herois.getIntegrantes())
      if (personagem.getTempoEspera() == 0) {
        return false;
      }
    for (Personagem personagem : inimigos.getIntegrantes()){
      if (personagem.getTempoEspera() == 0){
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
    Set<Personagem> integrantes = equipe.getIntegrantes();

    // Exibir lista de alvos disponíveis
    System.out.println("Escolha um alvo:");

    int index = 1;
    for (Personagem integrante : integrantes) {
      if (!integrante.getAtordoado()) {
        System.out.println(index + ". " + integrante.getNome());
        index++;
      }
    }

    // Solicitar ao jogador a escolha do alvo
    int escolha;
    do {
      System.out.print("Digite o número correspondente ao alvo: ");
      try {
        escolha = Integer.parseInt(scanner.nextLine());

        // Verifica se a escolha é válida
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
      if(!integrante.getAtordoado()) {
        if (count == escolha) {
          return integrante;
        }
        count++;
      }
    }

    // Caso algo inesperado ocorra, retornamos null
    return null;
  }

  //not ok
  //private Set<Personagem> escolherAlvo(Equipe equipe) { return null;}
}
