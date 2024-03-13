import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Jogo {

  private final Equipe herois;
  private final Equipe inimigos;
  private List<String> linhasArquivo;
  private int contadorTurnos;


  // Construtor
  public Jogo(String caminhoArquivo) throws IOException {
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
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
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
          System.out.println("\n" + linha.substring(5));
          if (verificacao){
            iniciarBatalha();
            verificacao = false;
          }
        }
    }

    // Epílogo
  }

  private void carregarHerois() {
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

    // Adicionar o novoMonstro à equipe inimiga, por exemplo:
    // equipeInimiga.adicionarIntegrante(novoMonstro);
  }

  private boolean notTurnoSilencioso(Equipe herois, Equipe inimigos) {
    for (Personagem personagem : herois.getIntegrantes())
      if (personagem.getTempoEspera() == 0) {
        return true;
      }
    for (Personagem personagem : inimigos.getIntegrantes()){
      if (personagem.getTempoEspera() == 0){
        return true;
      }
    }
    return false;
  }

  private void iniciarBatalha() {
      System.out.println("Iniciando batalha...");
  
        // Exibe informações iniciais das equipes
      exibirInformacoesEquipes(herois, inimigos);
  
        // Sorteia quem ataca primeiro
      Personagem atacante = null;
  
        // Inicia os turnos
      while (herois.peloMenosUmVivo() && inimigos.peloMenosUmVivo()) {

        if (notTurnoSilencioso(herois, inimigos)){
          while (true){
              assert false;
              if (!(atacante.getTempoEspera() > 0)) break;
              atacante = (herois.getIntegrantes().contains(atacante)) ? inimigos.definirProximoAtacante() : herois.definirProximoAtacante();
              realizarTurno(herois, inimigos, atacante);
          }
        } else {
          System.out.println("Turno sem ação.");
        }


        exibirInformacoesEquipes(herois, inimigos);
      }
  
        // Exibe o resultado da batalha
      exibirResultadoBatalha(inimigos);
  }

  private void realizarTurno(Equipe herois, Equipe inimigos, Personagem atacante) {
    System.out.println("\n --- Turno " + contadorTurnos + " --- ");
    System.out.println("É a vez de " + atacante.getNome() + " atacar!");

      // Escolhe uma habilidade:
    Habilidade habilidadeEscolhida = escolherHabilidade(atacante);

      //  Escolha um alvo:
    Equipe equipeAlvo = (herois.getIntegrantes().contains(atacante)) ? inimigos : herois;
    Personagem alvo = escolherAlvo(equipeAlvo);

      // Executa a habilidade no alvo
    atacante.usarHabilidade(habilidadeEscolhida, alvo);
    int ganhoPE = atacante.usarHabilidade(habilidadeEscolhida, alvo);

      // Distribui possível PE vindo de possíveis atordoamentos da habilidade usada no turno.
    if(equipeAlvo == inimigos) {
      herois.distribuirPE(ganhoPE);
    }

      // Atualiza o tempo de espera de maneira correspondente a sua habilidade.
    atacante.atualizarTempoEspera(habilidadeEscolhida.getTempoEspera());

      // Decrementa o tempo de espera de todos os personagens ao final do turno.
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
      System.out.println(index + ". " + integrante.getNome());
      index++;
    }

    // Solicitar ao jogador a escolha do alvo
    int escolha;
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
