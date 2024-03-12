import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Jogo {

  private Equipe herois;
  private Equipe inimigos;
  private File arquivo;

  public Jogo(File arquivo) {
    this.herois = new Equipe();
    this.inimigos = new Equipe();
    this.arquivo = new arquivo;
  }

// comentário pra lembrar de implementar alguma forma de portar o arquivo .txt e transformar ele em string (espero q seja minimamente parecido com shell script)

  public void iniciar() {
    // ANOTAÇÕES
    // "INICIAR" DEVE ABRIR UM MENU PRA CRIAÇÃO DE PERSONAGENS
    for (int i = 1; i <=3; ++i){
      System.out.println("\nNome do " + i + "º Herói ou Heroína: ");
      String nomeHeroi = scanner.nextLine();
      System.out.println("\nQual será a classe de " + nomeHeroi + "?");
      System.out.println("\n1- GUERREIRO\n2- ARQUEIRO\n3- MAGO");
      int escolhaClasse = scanner.nextLine();
      switch (escolhaClasse) {
        case 1:
            Personagem novoHeroi = new Personagem(nomeHeroi, new Guerreiro());
        case 2:
            Personagem novoHeroi = new Personagem(nomeHeroi, new Arqueiro());
        case 3:
            Personagem novoHeroi = new Personagem(nomeHeroi, new Mago());
        default:
            Personagem novoHeroi = new Personagem(nomeHeroi, new Guerreiro());
      }

      herois.adicionarIntegrante(novoHeroi);
    }
    
    // APOS ISSO, DEVE ABRIR O ARQUIVO E TRANSFORMAR SEU CONTEUDO EM FASES

    
    // A PRIMEIRA LINHA DA FASE DEVE SER PRINTADA
    // A SEGUNDA LINHA DEVE SER TRANSFORMADA NA EQUIPE INIMIGA, COM CADA ITEM SENDO UM INTEGRANTE
    // APOS UMA FASE ACABAR, A PROXIMA DEVE COMEÇAR (FAZER UM SET DE FASES???????????)
  }

  public void carregarEquipe() {
    
  }

   // public void iniciar() { <---- redundância????????????
    //}
}
