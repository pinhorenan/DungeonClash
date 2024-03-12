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
    
  }

  public void carregarEquipe() {
    
  }

   // public void iniciar() { <---- redundância????????????
    //}
}
