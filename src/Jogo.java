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
