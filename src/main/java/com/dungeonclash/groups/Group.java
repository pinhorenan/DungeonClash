package main.java.com.dungeonclash.groups;

import main.java.com.dungeonclash.character.Character;

import java.util.HashSet;
import java.util.Set;

public class Group {

    private final Set<Character> members;
    private final boolean isFoes;

    public Group(boolean isFoes) {
            // Construtor.
        this.isFoes = isFoes;
        members = new HashSet<>();
    }

    public void addMember(Character character) {
            // Adiciona novos Personagens ao conjunto Main.java.com.dungeonclash.groups.Group.
        if(getIsFoes()) {
            members.add(character);
        } else if (members.size() < 3){
            members.add(character);
        }
    }

    public void removerIntegrante(Character members) {
            // Remove personagens do conjunto Main.java.com.dungeonclash.groups.Group.
        if (!this.members.contains(members)) {
            return;
        }
        this.members.remove(members);
    }

    public Character searchForMember(int ID) {
            // Busca um Main.java.com.dungeonclash.character.Character integrante de equipe a partir de seu ID e retorna-o.
        for (Character character : members) {
            if (character.getID() == ID) {
                return character;
            }
        } return null;
    }

    /* public void distributeXP(int xpGained) {
            // Incrementa PE à todos integrantes da Main.java.com.dungeonclash.groups.Group e faz realiza a checa se algum integrante deve subir de nível.
        for (Main.java.com.dungeonclash.character.Character character: members) {
            character.getXP(xpGained);
            character.levelUp();
        }
    } */

    /*public Main.java.com.dungeonclash.character.Character getNextAttacker() {
            // Compara os tempoEspera entre integrantes para retornar o com o menor valor, que será o próximo atacante. Em caso de valores iguais a escolha é feita aleatóriamente.
        return Collections.min(members);
    }*/

    public Character getNextToAttack() {
        if (members.isEmpty()) {
            throw new IllegalStateException("A lista de integrantes está vazia. Não é possível continuar.");
        }

        Character nextToAttack = null;
        for (Character member : members) {
            if (member.getIsDead()) { // Verifica se o integrante não está atordoado
                if (nextToAttack == null || member.getWaitTime() < nextToAttack.getWaitTime()) {
                    nextToAttack = member;
                } else if (member.getWaitTime() == nextToAttack.getWaitTime()) {
                    // Em caso de empate nos tempos de espera, desempata aleatoriamente
                    if (Math.random() < 0.5) {
                        nextToAttack = member;
                    }
                }
            }
        }

        if (nextToAttack == null) {
            throw new IllegalStateException("Nenhum integrante apto para ser o próximo atacante.");
        }

        return nextToAttack;
    }


    public boolean atLeastOneStillAlive() {
            // Verifica se há pelo menos um integrante não atordoado na Main.java.com.dungeonclash.groups.Group. Quando todos integrantes de uma equipe forem atordoados uma batalha irá chegar ao seu fim.
        for (Character member : members) {
            if (member.getIsDead()) {
                return true;
            }
        }
        return false;
    }

    public Set<Character> getMembers() {
            // Retorna o conjunto de integrantes da Main.java.com.dungeonclash.groups.Group.
        return members;
    }

    public boolean getIsFoes() {
            // Retorna um valor true ou false para IsInimigo. Quando IsInimigo == True as equipes podem ter um número indeterminado de integrantes.
        return isFoes;
    }

    public void decrementsWaitingTime(){
            // Decrementa o tempo de espera de cada personagem da equipe. Será chamada pelas duas equipes no final de cada turno.
        for(Character character : members) {
            if (character.getWaitTime() >0 ) {
                character.setWaitTime(character.getWaitTime() - 1);
            }
        }
    }
}
