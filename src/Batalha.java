public class Batalha {

    // -------------------------------------------- ATRIBUTOS -------------------------------------------- //
    Equipe herois;
    Equipe inimigos;
    int contadorTurnos;

    // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
    public Batalha(Equipe herois, Equipe inimigos) {
        int contadorTurnos = 0;
        herois.getProximoAtacante();
        inimigos.getProximoAtacante();
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public void passarTurno() {
        contadorTurnos += 1;

        herois.atualizarEspera();
        inimigos.atualizarEspera();

        for (Personagem inimigo: inimigos.getIntegrantes()) {
            if (inimigo.getMorto()){
                int ganhoPE = inimigo.getNivel()*5;
                herois.distribuirPE(ganhoPE);
                inimigos.removerIntegrante(inimigo);
                }
            }
        }
    }
