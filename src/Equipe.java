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
        // Verifica se a equipe é de inimigos ou se ainda há espaço para mais personagens
        if (!getIsInimigos() && integrantes.size() >= 3) {
            throw new IllegalArgumentException("A equipe já está cheia.");
        }

        // Verifica se já existe um personagem com o mesmo nome na equipe
        for (Personagem integrante : integrantes) {
            if (integrante.getNome().equalsIgnoreCase(personagem.getNome())) {
                throw new IllegalArgumentException("Um personagem com este nome já existe na equipe.");
            }
        }

        // Adiciona o novo personagem à equipe
        integrantes.add(personagem);
    }

    public void removerIntegrante(Personagem integrante) {
            // Remove personagens do conjunto Equipe.
        if (!integrantes.contains(integrante)) {
            return;
        }
        integrantes.remove(integrante);
    }

    public Personagem buscarIntegrante(String nome) {
        // Busca um Personagem integrante de equipe a partir de seu nome e retorna-o.
        for (Personagem personagem : integrantes) {
            if (personagem.getNome().equalsIgnoreCase(nome)) {
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

    public Personagem definirProximoAtacante() {
        if (integrantes.isEmpty()) {
            throw new IllegalStateException("A lista de integrantes está vazia. Não é possível continuar.");
        }

        Personagem proximoAtacante = null;
        for (Personagem integrante : integrantes) {
            if (!integrante.getAtordoado()) { // Verifica se o integrante não está atordoado
                if (proximoAtacante == null || integrante.getTempoEspera() < proximoAtacante.getTempoEspera()) {
                    proximoAtacante = integrante;
                } else if (integrante.getTempoEspera() == proximoAtacante.getTempoEspera()) {
                    // Em caso de empate nos tempos de espera, desempata aleatoriamente
                    if (Math.random() < 0.5) {
                        proximoAtacante = integrante;
                    }
                }
            }
        }

        if (proximoAtacante == null) {
            throw new IllegalStateException("Nenhum integrante apto para ser o próximo atacante.");
        }

        return proximoAtacante;
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
            if (personagem.getTempoEspera() >0 ) {
                personagem.setTempoEspera(personagem.getTempoEspera() - 1);
            }
        }
    }
}

