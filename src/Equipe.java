import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Equipe {

    private final Set<Personagem> integrantes;
    private final boolean isInimigos;

    // Construtor
    public Equipe(boolean isInimigos) {
        this.isInimigos = isInimigos;
        this.integrantes = new HashSet<>();
    }

    // Métodos
    public void adicionarIntegrante(Personagem personagem) {
        if(!getIsInimigos() && integrantes.size() >= 3) {
            System.out.println("Uma equipe de heróis não pode ter mais de 3 integrantes!");
        } else {
            integrantes.add(personagem);
        }
    }

    public void removerIntegrante(Personagem integrante) {
        // Verifica-se se o integrante a ser removido atualmente faz parte do conjunto de integrantes
        if(integrantes.contains(integrante)) {
            integrantes.remove(integrante);
        } else {
            System.out.println(integrante.getNome() + "não faz parte da equipe e não pode ser removido.");
        }
    }

    public void distribuirPE(int ganhoPE) {
        for (Personagem personagem: integrantes) {
            personagem.ganharPE(ganhoPE);
            personagem.subirNivel();
        }
    }

    public void exibirInformacoes() {
        // Implementar
    }

    public void atacar(Equipe equipeAlvo, Personagem personagemAtacante, Habilidade habilidade) {
        personagemAtacante.usarHabilidade(habilidade, equipeAlvo);
    }

    public void atacar(Personagem personagemAlvo, Habilidade habilidade) {
        for (Personagem atacante : integrantes ) {
            atacante.usarHabilidade(habilidade, personagemAlvo);
        }
    }

    public Personagem definirProximoAtacante() {
        return Collections.min(integrantes);
    }

    public boolean peloMenosUmVivo() {
        // Implementar
        return true;
    }

    // Getters

    public Set<Personagem> getIntegrantes() {
        return integrantes;
    }


    public boolean getIsInimigos() {
        return isInimigos;
    }
}
