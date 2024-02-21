public class Habilidade {

    private String nome; // Nome da habilidade
    private PesosDeAtributos pesosDano, pesosMana; // Define pesos de dano e mana(magia) associados a habilidade
    private int tempo; // Tempo de espera associado a habilidade
    private boolean afetaGrupo; // Se afeta todos os personagens da equipe adversária
    private boolean afetaAmigos; // Se pode ser utilizada em amigos
    private static int ID; // Identificador único da habilidade

    public Habilidade(String nome, PesosDeAtributos pesosDano, PesosDeAtributos pesosMana, int tempo, boolean afetaAmigos, boolean afetaTodos) {
        this.nome = nome;
        this.pesosMana = pesosMana;
        this.pesosDano = pesosDano;
        this.tempo = tempo;
        this.afetaGrupo = afetaGrupo;
        this.afetaAmigos = afetaAmigos;
    }

    public String getNome() {
        return nome;
    }

    public PesosDeAtributos getPesosDano() {
        return pesosDano;
    }

    public PesosDeAtributos getPesosMana() {
        return pesosMana;
    }

    public int getTempo() {
        return tempo;
    }

    public boolean isAfetaGrupo() {
        return afetaGrupo;
    }

    public boolean isAfetaAmigos() {
        return afetaAmigos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPesosDano(PesosDeAtributos pesosDano) {
        this.pesosDano = pesosDano;
    }

    public void setPesosMana(PesosDeAtributos pesosMana) {
        this.pesosMana = pesosMana;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setAfetaGrupo(boolean afetaGrupo) {
        this.afetaGrupo = afetaGrupo;
    }

    public void setAfetaAmigos(boolean afetaAmigos) {
        this.afetaAmigos = afetaAmigos;
    }

}

