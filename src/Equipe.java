import java.util.Set;

public class Equipe {

    private Set<Personagem> equipe;

    public Equipe() {
    }

    public void addIntegrante(Personagem integrante) {
        equipe.add(integrante);
    }

    public void distribuirPE(int lvlInimigo) {
        for (Personagem integrante: grupo) {
            integrante.ganharPE(lvlInimigo);
        }
    }

    public Personagem searchID(int ID) {
        for (Personagem integrante : grupo) {
            if (ID == Personagem.getID()) {
                return personagem;
            } else {
                return null;
            }
        }
    }
    public int atualizarEspera(Set<Habilidade> habilidades, String nome) {
        for(Habilidades habilidade: habilidades) {
            if (habilidade.getNome() == nome) {
                return habilidade.getTempoEspera();
            } else {
                return 0;
            }
        }
    }
    // getter
    public Set<Personagem> getGrupo() {
        return grupo;
    }
}
