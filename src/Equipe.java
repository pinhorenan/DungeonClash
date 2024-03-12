import java.util.Set;
import static java.lang.Math.random;

public class Equipe {

    // -------------------------------------------- ATRIBUTOS -------------------------------------------- //
    private Set<Personagem> integrantes;
    private boolean isInimigos;

    // -------------------------------------------- CONSTRUTOR ------------------------------------------- //
    public Equipe() {
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public void adicionarIntegrante(Personagem integrante) {
        if(!isInimigos||integrantes.size()>=3) {
            System.out.println("Uma equipe de heróis não pode ter mais de 3 integrantes!");
        } else {
            integrantes.add(integrante);
        }
        // Necessário implementar condicional para garantir que uma equipe de heróis não tenha mais de 3 heróis.
    }

    public void removerIntegrante(Personagem integrante) {
        if(integrantes.contains(integrante)) {
            integrantes.remove(integrante);
        } else {
            System.out.println(integrante.getNome() + "não faz parte da equipe e não pode ser removido.");
        }
    }

    public void computaPE() {
    } // IMPLEMENTAR!!

    // -------------------------------------------- GETTERS -------------------------------------------- //
    public Set<Personagem> getIntegrantes() {
        return integrantes;
    }

    public Personagem getProximoAtacante() { // Estou retornando o próximo a atacar como sendo o personagem com o menor tempo de espera, o que não significa que ele irá atacar neste turno (caso o tempoEspera > 0) mas será o primeiro a atacar uma vez que tds turnos irão decrementar 1 do tempo de espera.
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

    public int atualizarEspera(Set<Habilidade> habilidades, String nome) {
        return 0;
    } // IMPLEMENTAR!!

} // FECHAMENTO
