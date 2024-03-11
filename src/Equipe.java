public class Equipe {
    /*  classe Equipe deve manter uma coleção de personagens (agregação). O projeto básico define que
    cada batalha se dá entre duas equipes, a equipe do herois e a equipe dos inimigos. Como
    funcionalidades básicas, esta classe deve implementar métodos para adicionar e buscar personagens
    em uma equipe, retornar a equipe (coleção), computar pontos de experiência para a equipe,
    determinar o próximo atacante, atualizar o tempo de espera, entre outros. */
    private List<Personagem> integrantes;

    public Equipe() {
        this.integrantes = new ArrayList<>();
    }

    public void addIntegrante(Personagem integrante) {
        integrantes.add(integrante);
    }

    public void distribuirPE(int lvlInimigo) {
        for (Personagem integrante: integrantes) {
            integrante.ganharPE(lvlInimigo);
        }
    }

    public Personagem searchID(int ID) {
        for (Personagem integrante : integrantes) {
            if (ID == personagem.getID) {
                return personagem;
            } else {
                return null;
            }
        }
    }

    // getter
    public List<Personagem> getIntegrantes() {
        return integrantes;
    }
}
