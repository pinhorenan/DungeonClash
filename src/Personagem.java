public class Personagem {

    private final String nome;
    private final Classe classe;
    private static int proximoID = 1;
    private final int ID;
    private int nivel, PE, tempoEspera;
    private float PVmax, PV, PMmax, PM;

    public Personagem(String nome, Classe classe) {
        this.nivel = 1;
        this.PE = 0;
        this.ID = gerarProximoID();
        this.tempoEspera = 0;
        this.nome = nome;
        this.classe = classe;
        this.PVmax = this.nivel * classe.forca + (this.nivel * ((float) classe.agilidade /2) ); // Discrepância de tipo de dado.
        this.PMmax = this.nivel * classe.inteligencia + (this.nivel * ((float) classe.agilidade /3)); // Discrepância de tipo de dado.
        this.PV = this.PVmax;
        this.PM = this.PMmax;
    }

    // Métodos

    public void subirNivel() {
        if (this.getPE()>=(this.nivel * 25)) {
            this.nivel++;
            this.PVmax += this.nivel * this.classe.forca + (this.nivel * ((float) this.classe.agilidade /2));
            this.PMmax += this.nivel * this.classe.inteligencia + (this.nivel * ((float) this.classe.agilidade /3));
            this.classe.inteligencia *= this.nivel; // ta errado nao lembro pq
            this.classe.agilidade *= this.nivel; // ta errado nao lembro pq
            this.classe.forca *= this.nivel; // ta errado nao lembro pq
            this.PE = 0; // subjetivo pq vai matar qualquer XP que sobrar
        }   // Revisar este código.
    }

    private static int gerarProximoID() {
        return proximoID++;
    }

    public void sofrerDano(int dano) {
        if (this.PV > 0) {
            this.setPV(this.getPV()-dano);
        }
    }

    public void morrer() {
        if(this.PV <= 0) {
            this.setTempoEspera(999999999);
        }
    }

    public void atacarInimigo(Habilidade habilidade, Personagem inimigo) {
        if(this.getTempoEspera()!=0) {
            System.out.println("Você não pode atacar ainda.");
        } else if(habilidade.calcularCustoMana(this.classe) > this.getPM()) {
            System.out.println("Você não tem mana.");
        } else if(!this.classe.getHabilidades().contains(habilidade)) {
            System.out.println("Você não possui essa habilidade");
        } else {
            inimigo.sofrerDano(habilidade.calcularDanoCausado(this.classe));
            this.setTempoEspera(habilidade.getTempoEspera());
            System.out.println("Você utiliza "+ habilidade.getNome() + " contra " + inimigo.getNome() + ", causando " + habilidade.calcularDanoCausado(this.classe) + "de dano!");
        }
    }

    public void atacarGrupo(Habilidade habilidade, Equipe grupo) {
        if(this.getTempoEspera()!=0) {
            System.out.println("Você não pode atacar ainda.");
        } else if(habilidade.calcularCustoMana(this.classe) > this.getPM()) {
            System.out.println("Você não tem mana.");
        } else if(!this.classe.getHabilidades().contains(habilidade)) {
            System.out.println("Você não possui essa habilidade");
        } else {

        }
    }

    public void ganharPE(int PE) {
        this.PE =+ PE;
    }

    // -------------------------------------------- GETTERS -------------------------------------------- //

    public String getNome() {
        return this.nome;
    }

    public Classe getClasse() {
        return this.classe;
    }

    public int getNivel() {
        return this.nivel;
    }

    public int getPE() {
        return this.PE;
    }

    public int getID() {
        return this.ID;
    }

    public int getTempoEspera() {
        return this.tempoEspera;
    }

    public float getPV() {
        return this.PV;
    }

    public float getPM() {
        return this.PM;
    }

    // -------------------------------------------- SETTERS -------------------------------------------- //

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public void setPE(int PE) {
        this.PE = PE;
    }

    public void setPV(float PV) {
        this.PV = PV;
    }

    public void setPM(float PM) {
        this.PM = PM;
    }
}



