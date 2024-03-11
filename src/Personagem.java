public class Personagem {

    // -------------------------------------------- ATRIBUTOS -------------------------------------------- //
    private final String nome;
    private final Classe classe;
    private static int proximoID = 1;
    private final int ID;
    private int nivel, PE, tempoEspera;
    private float PVmax, PV, PMmax, PM;
    private boolean morto;

    // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
    public Personagem(String nome, Classe classe) {
        this.nome = nome;
        this.classe = classe;
        ID = gerarProximoID();
        nivel = 1;
        PE = 0;
        tempoEspera = 0;
        PVmax = classe.forca + ((float) this.classe.agilidade / 2);
        PMmax = classe.inteligencia + ((float) this.classe.agilidade / 3);
        PV = PVmax;
        PM = PMmax;
        morto = false;
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public void subirNivel() {
        if (PE >= (nivel * 25)) {
            nivel++;
            classe.subirNivel();
            PVmax += nivel * classe.forca + (nivel * ((float) classe.agilidade / 2));
            PMmax += nivel * classe.inteligencia + (nivel * ((float) classe.agilidade / 3));
            PE = 0;
        }
    } // Será chamado após as batalhas

    private static int gerarProximoID() {
        return proximoID++;
    } // Utilidade

    public void sofrerDano(int dano) {
        if (PV > 0) {
            PV -= dano;
        }
    } // Chamado pelos métodos "atacarInimigo()" e "atacarGrupo"

    public int custoMana(Habilidade habilidade) {
        return (int) (nivel * habilidade.calcularCustoMana(classe));
    }

    public int danoCausado(Habilidade habilidade) {
        return (int) (nivel * habilidade.calcularDanoCausado(classe));
    }

    public void atacarInimigo(Habilidade habilidade, Personagem inimigo) {
        if (morto) {
            System.out.println("Você está morto.");
        } else
            if(tempoEspera != 0) {
                System.out.println("Você não pode atacar ainda.");
        } else
            if(custoMana(habilidade) > PM) {
                System.out.println("Você não tem mana.");
        } else
            if(inimigo.morto) {
                System.out.println(inimigo.nome + " já está morto.");
        } else
            if(!classe.getHabilidades().contains(habilidade)) {
                System.out.println("Você não possui essa habilidade");
        } else {
                inimigo.sofrerDano(danoCausado(habilidade));
                setTempoEspera(habilidade.getTempoEspera());
                setPM(this.getPM() - custoMana(habilidade));
                System.out.println("Você utiliza "+ habilidade.getNome() + " contra " + inimigo.getNome() + ", causando " + danoCausado(habilidade) + " de dano!");

                if (inimigo.PV==0) {
                    setMorto(inimigo);
                    System.out.println("Você MATOU VIOLENTAMENTE o " + inimigo.getNome() + "! Sinta-se orgulhoso!");
                }
            }
    }

    public void atacarGrupo(Habilidade habilidade, Equipe grupo) {
        if(tempoEspera != 0) {
            System.out.println("Você não pode atacar ainda!");
        } else if(habilidade.calcularCustoMana(classe) > PM) {
            System.out.println("Você não tem mana.");
        } else if(!classe.getHabilidades().contains(habilidade)) {
            System.out.println("Você não possui essa habilidade!");
        } else if(!habilidade.getIsAfetaGrupo()) {
            System.out.println("Essa habilidade não pode ser usada contra um grupo!");
        }
    }

    public void ganharPE(int PE) {
        this.PE =+ PE;
    }

    // -------------------------------------------- GETTERS -------------------------------------------- //

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

    public void setVivo(Personagem defunto) {
        defunto.morto = false;
    }

    public void setMorto(Personagem personagem) {
        personagem.morto = true;
    }
}



