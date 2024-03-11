public class Jogo {
/* A única classe que pede entrada de dados e exibe mensagens na tela é a Jogo, que controlará o jogo.
A classe principal, que pode se chamar DungeonClash, apenas deve chamar um objeto de Jogo no
método main. Reforçando: Nenhuma classe pede entrada ou exibe saída em seus métodos, com
exceção da classe Jogo. Dessa forma as classes que tratam os personagens (classes de dados)
ficarão genéricas e podem ser facilmente adaptadas para um projeto com uma interface visual
diferente. */
  private Equipe herois;
  private Equipe inimigos;

  public Jogo() {
    this.herois = new Equipe();
    this.inimigos = new Equipe();
  }

  public void iniciar(string arquivo) {
    
  }

  public void carregarEquipe() {
    
  }

    public void iniciar() {
    }
}
