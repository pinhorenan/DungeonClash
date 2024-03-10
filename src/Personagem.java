public class Personagem {

    private final String nome;
    private final Classe classe;
    private final int ID;
    private int nivel, PE, tempoEspera;
    private float PVmax, PV, PMmax, PM;
    // Talvez não possa ter os atributos PV e PM e teremos que achar outro método.


    public Personagem(String nome, Classe classe) {
        nivel = 1;
        PE = 0;
        ID = 0; // TEMPORÁRIO!!! Adicionar método para randomizar IDs.
        tempoEspera = 0;
        this.nome = nome;
        this.classe = classe;
        PVmax = nivel * classe.forca + (nivel * (classe.agilidade/2) ); // Discrepância de tipo de dado.
        PMmax = nivel * classe.inteligencia + (nivel * (classe.agilidade/3)); // Discrepância de tipo de dado.
    }

    // Métodos

    public void subirNivel() {
        if (PE>=(nivel*25)) {  // PE necessário p/ subir de nível: nívelAtual * 25
            int excessoPE = PE - (nivel*25);
            nivel++;
            PE = 0;
            PE = excessoPE;
            excessoPE = 0;
            PVmax += nivel * classe.forca + (nivel * (classe.agilidade/2));
            PMmax += nivel * classe.inteligencia + (nivel * (classe.agilidade/3));
        }   // Revisar este código.
    }

    // Getters

    public String getNome() {
        return nome;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPE() {
        return PE;
    }

    public int getID() {
        return ID;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public float getPV() {
        return PV;
    }

    public float getPM() {
        return PM;
    }

    // Setters

    public void setPE(int PE) {
        this.PE = PE;
        // Nessa implementação o cálculo de acréscimo do PE deve ser feito antes.
        // PoderÍamos substituir por um método incrementaPE()
    }

    public void setPV(float PV) {
        this.PV = PV;
        // Provavelmente seria utilizado para encher a vida após o final da batalha.
    }

    public void setPM(float PM) {
        this.PM = PM;
        // Provavelmente seria utilizado para encher a mana após o fina da batalha.
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    // Métodos

    public void sofrerDano(int dano) {
        // Implementar
        if (this.PV > 0) {
            this.PV -= dano;
        }
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


