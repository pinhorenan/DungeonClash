public class Habilidade {

    private String nome; // Nome da habilidade
    private PesosDeAtributos pesosDano;
    private PesosDeAtributos pesosMana; // Define pesos de dano e mana(magia) associados a habilidade
    private int tempo; // Tempo de espera associado a habilidade
    private boolean afetaGrupo; // Se afeta todos os personagens da equipe adversária
    private boolean afetaAmigos; // Se pode ser utilizada em amigos
    private static int ID; // Identificador único da habilidade

    public Habilidade(String nome, PesosDeAtributos pesosDano, PesosDeAtributos pesosMana, int tempo, boolean afetaAmigos, boolean afetaGrupo) {
    // mago 
        // private Habilidade cura = new Habilidade("Cura", 
        this.nome = nome;
        this.pesosDano = pesosDano;
        this.pesosMana = pesosMana;
        this.tempo = tempo;
        this.afetaGrupo = afetaGrupo;
        this.afetaAmigos = afetaAmigos;
    }


    // Getters

    public String getNome() {
        return nome;
    }

    public double getPesoForca() {
        return PesosDeAtributos.getPesoForca();
    }

    public double getPesoAgilidade() {
        return PesosDeAtributos.getPesoAgilidade();
    }

    public double getPesoInteligencia() {
        return PesosDeAtributos.getPesoInteligencia();
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

    // Setters

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

