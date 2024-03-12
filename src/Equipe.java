import java.util.Set;
import static java.lang.Math.random;

public class Equipe {

    // -------------------------------------------- ATRIBUTOS -------------------------------------------- //
    private Set<Personagem> integrantes;
    private final boolean isInimigos;

    // -------------------------------------------- CONSTRUTOR ------------------------------------------- //
    public Equipe(boolean isInimigos) {
    this.isInimigos = isInimigos;
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public void adicionarIntegrante(Personagem personagem) {
        if(!getIsInimigos() && integrantes.size() >= 3) {
            System.out.println("Uma equipe de heróis não pode ter mais de 3 integrantes!");
        } else {
            integrantes.add(personagem);
        }
    }

    public void removerIntegrante(Personagem integrante) {
        if(integrantes.contains(integrante)) {
            integrantes.remove(integrante);
        } else {
            System.out.println(integrante.getNome() + "não faz parte da equipe e não pode ser removido.");
        }
    }

    public void distribuirPE(int ganhoPE) {
        for (Personagem personagem: integrantes) {
            personagem.ganharPE(ganhoPE);
        }
    }

    public void atualizarEspera() {
        for(Personagem personagem : integrantes) {
            personagem.setTempoEspera(personagem.getTempoEspera()-1);
        }
    }

    // -------------------------------------------- GETTERS -------------------------------------------- //
    public Set<Personagem> getIntegrantes() {
        return integrantes;
    }

    public Personagem getProximoAtacante() {
        Personagem proximoAtacante = null;

        for (Personagem personagem: integrantes) {
            if (proximoAtacante == null) {
                proximoAtacante = personagem;
            } else if (personagem.getTempoEspera() < proximoAtacante.getTempoEspera()) {
                proximoAtacante = personagem;
            } else if (personagem.getTempoEspera() == proximoAtacante.getTempoEspera()) {
                if (random() <0.5) {
                    proximoAtacante = personagem;
                }
            }
        }
        return proximoAtacante;
    }

    public boolean getIsInimigos() {
        return isInimigos;
    }

}
