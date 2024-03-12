import java.nio.file.*;
import java.util.Scanner;
import java.util.List;

public class Jogo {

  private Equipe herois;
  private Equipe inimigos;
  private Path arquivo;

  public Jogo(String caminhoArquivo) {
    this.herois = new Equipe(false);
    this.inimigos = new Equipe(true);
    this.arquivo = Paths.get(caminhoArquivo);
    try {
      List<String> linhasArquivo = Files.readAllLines(arquivo);  // Leia todas as linhas do arquivo
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
      System.exit(1); // Isso encerra o programa com código de erro 1.
    }
  }
  
  // comentário pra lembrar de implementar alguma forma de portar o arquivo .txt e transformar ele em string (espero q seja minimamente parecido com shell script)

  public void iniciar() {
    Scanner scanner = new Scanner(System.in);
    // ANOTAÇÕES
    // "INICIAR" DEVE ABRIR UM MENU PRA CRIAÇÃO DE PERSONAGENS
    for (int i = 1; i <= 3; ++i) {
      System.out.println("\nNome do " + i + "º Herói ou Heroína: ");
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

    // APOS ISSO, DEVE ABRIR O ARQUIVO E TRANSFORMAR SEU CONTEUDO EM FASES


    // A PRIMEIRA LINHA DA FASE DEVE SER PRINTADA
    // A SEGUNDA LINHA DEVE SER TRANSFORMADA NA EQUIPE INIMIGA, COM CADA ITEM SENDO UM INTEGRANTE
    // APOS UMA FASE ACABAR, A PROXIMA DEVE COMEÇAR (FAZER UM SET DE FASES???????????)
  }

  public void carregarEquipe() {
    // public void iniciar() { <---- redundância????????????
    //}
  }
}

