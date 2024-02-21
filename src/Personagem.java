public class Personagem {
    // Atributos
    private String nome;
    private int nivel, PE;
    private float PV, PM;
    private int tempoEspera;
    private Classe classe;
    private static int ID;

    // Getters

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPE() {
        return PE;
    }

    public float getPV() {
        return PV;
    }

    public float getPM() {
        return PM;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getID() {
        return ID;
    }

    // Setters

    public void setPE(int PE) {
        this.PE = PE;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setPV(float PV) {
        this.PV = PV;
    }

    public void setPM(float PM) {
        this.PM = PM;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setID(int ID) {
        Personagem.ID = ID;
    }

    // Métodos

    public void sofrerDano(int dano) {
        // Implementar
    }

    public void atacarInimigo(Habilidade habilidade) {
        // Implementar
    }

    public void atacarGrupo(Habilidade habilidade) {
        // Implementar
    }

    public void ganharPE(int PE) {
        // Implementar
    }

    public void subirNivel(int PE) {
        // Implementar
    }
}


