import java.util.Set;

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
    }                                                           // Pronto

    private static int gerarProximoID() {
        return proximoID++;
    }                              // Pronto

    public void sofrerDano(int dano) {
        if (PV > 0) {
            PV -= dano;
        }
    }                                                   // Pronto

    public int custoMana(Habilidade habilidade) {
        return (int) (nivel * habilidade.calcularCustoMana(classe));
    }                                        // Pronto

    public int danoCausado(Habilidade habilidade) {
        return (int) (nivel * habilidade.calcularDanoCausado(classe));
    }                                      // Pronto

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
                    inimigo.setMorto();
                    System.out.println("Você MATOU VIOLENTAMENTE o " + inimigo.getNome() + "! Sinta-se orgulhoso!");
                }
            }
    }               // Pronto

    public void atacarGrupo(Habilidade habilidade, Equipe grupo) {
        if(tempoEspera != 0) {
                System.out.println("Você não pode atacar ainda!");

        } else if(habilidade.calcularCustoMana(classe) > PM) {
                System.out.println("Você não tem mana.");

        } else if(!classe.getHabilidades().contains(habilidade)) {
                System.out.println("Você não possui essa habilidade!");

        } else if(!habilidade.getIsAfetaGrupo()) {
                System.out.println("Essa habilidade não pode ser usada contra um grupo!");
        } else {
                Set<Personagem> integrantes = grupo.getIntegrantes();

                for(Personagem personagem: integrantes) {
                    personagem.sofrerDano(danoCausado(habilidade));
                    if (personagem.getPV() == 0){
                        personagem.setMorto();
                    }
                }

        }
    }                       // Implementar

    public void ganharPE(int PE) {
        this.PE += PE;
    }                                          // Pronto

    // -------------------------------------------- GETTERS -------------------------------------------- //

    public String getNome() {
        return nome;
    }                                                 // Pronto

    public Classe getClasse() {
        return classe;
    }                                             // Pronto

    public int getNivel() {
        return nivel;
    }                                                  // Pronto

    public int getPE() {
        return PE;
        }                                                        // Pronto

    public int getID() {
        return ID;
    }                                                        // Pronto

    public int getTempoEspera() {
        return tempoEspera;
    }                                      // Pronto

    public float getPV() {
        return PV;
    }                                                      // Pronto

    public float getPM() {
        return PM;
    }                                                      // Pronto

    // -------------------------------------------- SETTERS -------------------------------------------- //

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }          // Pronto

    public void setPE(int PE) {
        this.PE = PE;
    }                                              // Pronto

    public void setPV(float PV) {
        this.PV = PV;
    }                                            // Pronto

    public void setPM(float PM) {
        this.PM = PM;
    }                                            // Pronto

    public void setVivo() {
        morto = false;
    }                                            // Pronto

    public void setMorto() {
        morto = true;
    }
}



