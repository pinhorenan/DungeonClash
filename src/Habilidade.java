public class Habilidade {

    private String nome; // Nome da habilidade
    private PesosDeAtributos pesosAtributos; // Define pesos de dano e mana(magia) associados a habilidade
    private int tempo; // Tempo de espera associado a habilidade
    private boolean afetaGrupo; // Se afeta todos os personagens da equipe adversária
    private boolean afetaAmigos; // Se pode ser utilizada em amigos
    private static int ID; // Identificador único da habilidade

    public Habilidade(String nome, PesosDeAtributos pesosAtributos, int tempo, boolean afetaAmigos, boolean afetaGrupo) {
        this.nome = nome;
        this.pesosAtributos = pesosAtributos;
        this.tempo = tempo;
        this.afetaGrupo = afetaGrupo;
        this.afetaAmigos = afetaAmigos;
    }

    public String getNome() {
        return nome;
    }

    public double getPesoForca() {
        return pesosAtributos.getPesoForca();
    }

    public double getPesoAgilidade() {
        return pesosAtributos.getPesoAgilidade();
    }

    public double getPesoInteligencia() {
        return pesosAtributos.getPesoInteligencia();
    }

    public int getPesoMana() {
        return pesosAtributos.getPesoMana();
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

