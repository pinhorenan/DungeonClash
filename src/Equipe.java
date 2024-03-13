import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Equipe {

    private final Set<Personagem> integrantes;
    private final boolean isInimigos;

    public Equipe(boolean isInimigos) {
        // Construtor.
        this.isInimigos = isInimigos;
        integrantes = new HashSet<>();
    }

    public void adicionarIntegrante(Personagem personagem) {
        // Adiciona novos Personagens ao conjunto Equipe.
        if(getIsInimigos()) {
            integrantes.add(personagem);
        } else if (integrantes.size() < 3){
            integrantes.add(personagem);
        }
    }

    public void removerIntegrante(Personagem integrante) {
        // Remove personagens do conjunto Equipe.
        if (!integrantes.contains(integrante)) {
            return;
        }
        integrantes.remove(integrante);
    }

    public Personagem buscarIntegrante(Equipe equipe, int ID) {
        // Busca um Personagem integrante de equipe a partir de seu ID e retorna-o.
        for (Personagem personagem : integrantes) {
            if (personagem.getID() == ID) {
                return personagem;
            }
        } return null;
    }

    public void distribuirPE(int ganhoPE) {
        // Incrementa PE à todos integrantes da Equipe e faz realiza a checa se algum integrante deve subir de nível.
        for (Personagem personagem: integrantes) {
            personagem.ganharPE(ganhoPE);
            personagem.subirNivel();
        }
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
        // Compara os tempoEspera entre integrantes para retornar o com o menor valor, que será o próximo atacante. Em caso de valores iguais a escolha é feita aleatóriamente.
        if (integrantes.isEmpty()) {
            return null; // Luis, você pode tentar tratar de outra forma as exceções, já que você manja.
        }
        return Collections.min(integrantes);
    }

    public boolean peloMenosUmVivo() {
        // Verifica se há pelo menos um integrante não atordoado na Equipe. Quando todos integrantes de uma equipe forem atordoados uma batalha irá chegar ao seu fim.
        for (Personagem integrante : integrantes) {
            if (!integrante.getAtordoado()) {
                return true;
            }
        }
        return false;
    }

    public Set<Personagem> getIntegrantes() {
        // Retorna o conjunto de integrantes da Equipe.
        return integrantes;
    }

    public boolean getIsInimigos() {
        // Retorna um valor true ou false para IsInimigo. Quando IsInimigo == True as equipes podem ter um número indeterminado de integrantes.
        return isInimigos;
    }

    public void decrementaTempoEspera(){
        // Decrementa o tempo de espera de cada personagem da equipe. Será chamada pelas duas equipes no final de cada turno.
        for(Personagem personagem : integrantes) {
            personagem.setTempoEspera(personagem.getTempoEspera()-1);
        }
    }
}