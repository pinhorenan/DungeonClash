import java.util.Set;

public class Personagem implements Comparable<Personagem> {

    private final String nome;
    private final Classe classe;
    private static int proximoID = 1;
    private final int ID;
    private int nivel, PE, tempoEspera;
    private float PVmax, PV, PMmax, PM;
    private boolean atordoado;

    public Personagem(String nome, Classe classe) {
        // Construtor. Deverá passar o nome (String) e uma classe, a classe deverá ser criada no campo de parâmetro
        this.nome = nome;
        this.classe = classe;
        ID = gerarProximoID();
        nivel = 1;
        PE = 0;
        tempoEspera = 0;
        PVmax = classe.forca + ((float) classe.agilidade / 2);
        PMmax = classe.inteligencia + ((float) classe.agilidade / 3);
        PV = PVmax;
        PM = PMmax;
        atordoado = false;
    }

    public Personagem(String nome, Classe classe, int nivel) {
        // Construtor. Deverá passar o nome (String) e uma classe, a classe deverá ser criada no campo de parâmetro
        this.nome = nome;
        this.classe = classe;
        ID = gerarProximoID();
        this.nivel = nivel;
        PE = 0;
        tempoEspera = 0;
        PVmax = nivel * classe.forca + (nivel * ((float)  classe.agilidade / 2));
        PMmax = nivel * classe.inteligencia + (nivel * ((float) classe.agilidade / 3));
        PV = PVmax;
        PM = PMmax;
        atordoado = false;
    }

    @Override
    public int compareTo(Personagem outro) {
        // Define um método de comparação entre instâncias de Personagens para comparar seus tempoEspera. Chamado para definir o próximoAtacante.
        return Integer.compare(this.getTempoEspera(), outro.getTempoEspera());
    }

    public void ganharPE(int somaPE) {
        PE += somaPE;
    }

    public void subirNivel() {
        // Incrementa o nível do Personagem que chamar. Também irá "aumentar o nível" da instância de classe desse Personagem. Os PE são zerados após a subida de nível e qualquer excesso é desperdiçado.
        if (PE >= (nivel * 25)) {
            nivel++;
            classe.subirNivel();
            PVmax += nivel * classe.forca + (nivel * ((float) classe.agilidade / 2));
            PMmax += nivel * classe.inteligencia + (nivel * ((float) classe.agilidade / 3));
            PE = 0;
        }
    }

    public void atualizarTempoEspera(int tempo) {
        // Define o tempo de espera do Personagem que chamar para "tempo". Chamado após usar habilidades.
        this.setTempoEspera(tempo);
    }

    private static int gerarProximoID() {
        // Gera um novo ID incrementando o último ID registrado. Chamado durante instanciação de Personagens.
        return proximoID++;
    }

    public void sofrerDano(float dano) {
        // Subtrai o PV do Personagem que chamar pelo int Dano. Chamado durante uso de habilidades.
        if (PV > 0) {
            PV -= dano;
        }
        if (PV <= 0){
            PV = 0;
            System.out.println("\n" + nome + " foi atordoado.");
            atordoar();
        }
    }

    public float custoMana(Habilidade habilidade) {
        // Retorna o custo em PM ao usar uma habilidade dependendo do nível do personagem que chamar.
        return (nivel * habilidade.calcularCustoMana(classe));
    }

    public float danoCausado(Habilidade habilidade) {
        // Retorna o dano em PV ao usar uma habilidade dependendo do nível do personagem que chamar.
        return nivel * habilidade.calcularDanoCausado(classe);
    }

    public int calcularPE(Personagem alvo) {
        return alvo.getNivel() * 5;
    }

    public void usarHabilidade(Habilidade habilidade, Personagem alvo) {
        // Método para usar a Habilidade. Primeiro irá verificar se quem chamou cumpre os requisitos e então aplica os efeitos no Alvo.
        if (verificarRestricoes(habilidade, alvo) && verificarRestricoes(habilidade)) {
            // Aplica de fato o efeito da habilidade no alvo.
            aplicarEfeitoHabilidade(habilidade, alvo);

            // Atualiza o tempo de espera do personagem que utilizou a habilidade (aumenta).
            atualizarTempoEspera(habilidade.getTempoEspera());
        }
    }

    public void usarHabilidade(Habilidade habilidade, Equipe grupoAlvo) {
        // Método para usar a Habilidade. Primeiro irá verificar se quem chamou cumpre os requisitos e então aplica os efeitos no grupoAlvo.
        if (verificarRestricoes(habilidade)) {
            // Aplica de fato o efeito da habilidade no grupo alvo.
            aplicarEfeitoHabilidade(habilidade, grupoAlvo);

            // Atualiza o tempo de espera do personagem que utilizou a habilidade (aumenta).
            atualizarTempoEspera(habilidade.getTempoEspera());
        }
    }

    private boolean verificarRestricoes(Habilidade habilidade) {
        // Método auxiliar para verificar possibilidade de "usarHabilidade()". Restrições sobre quem chama a Habilidade.
        if (atordoado) {
            return false;
        }
        if (tempoEspera != 0) {
            return false;
        }
        if (habilidade.calcularCustoMana(classe) > PM) {
            return false;
        }
        return classe.getHabilidades().contains(habilidade);
    }

    private boolean verificarRestricoes(Habilidade habilidade, Personagem alvo) {
        // Método auxiliar para verificar possibilidade de "usarHabilidade()". Restrições sobre o alvo da habilidade.
        if (alvo.atordoado) {
            return false;
        }
        return verificarRestricoes(habilidade);
    }

    private void aplicarEfeitoHabilidade(Habilidade habilidade, Personagem alvo) {
        // Método que aplica os efeitos de uma habilidade usada, é chamado por "usarHabilidade()"; Versão para ataque em Personagens.
        if (habilidade.getIsAfetaAmigos()) {
            // Habilidades direcionadas à aliados irão curar.
            float cura = danoCausado(habilidade);
            System.out.println(this.getNome() + " usou " + habilidade.getNome() + " para curar " + alvo.getNome() + " em " + cura + " pontos de vida.");
            alvo.setPV(alvo.getPV() + cura);
        } else {
            float dano = danoCausado(habilidade);
            System.out.println(this.getNome() + " usou " + habilidade.getNome() + " contra " + alvo.getNome() + " e causou " + dano + " de dano.");
            alvo.sofrerDano(dano);
        }
        setPM(this.getPM() - custoMana(habilidade));
    }

    private void aplicarEfeitoHabilidade(Habilidade habilidade, Equipe grupoAlvo) {
        // Método que aplica os efeitos de uma habilidade usada, é chamado por "usarHabilidade()"; Versão para ataque em Equipes.
        Set<Personagem> integrantes = grupoAlvo.getIntegrantes();
        for (Personagem personagem : integrantes) {
            if (habilidade.getIsAfetaAmigos()) {
                // Habilidades direcionadas à aliados irão curar.
                float cura = danoCausado(habilidade);
                System.out.println(this.getNome() + " usou " + habilidade.getNome() + " para curar " + personagem.getNome() + " em " + cura + " pontos de vida.");
                personagem.setPV(personagem.getPV() + cura);
            } else {
                float dano = danoCausado(habilidade);
                System.out.println(this.getNome() + " usou " + habilidade.getNome() + " contra " + personagem.getNome() + " e causou " + dano + " de dano.");
                personagem.sofrerDano(dano);
            }
        }
        setPM(this.getPM() - custoMana(habilidade));
    }

    public void atordoar() {
        // Define o Personagem como Atordoado.
        setAtordoado(true);
    }

    public String getNome() {
        // Retorna o nome do Personagem.
        return nome;
    }

    public Classe getClasse() {
        // Retorna a instância de classe do Personagem.
        return classe;
    }

    public String getNomeClasse() {
        // Retorna o nome simples da classe associada ao Personagem.
        return classe.getName();
    }

    public int getNivel() {
        // Retorna o nível do Personagem.
        return nivel;
    }

    public int getID() {
        // Retorna o ID do Personagem.
        return ID;
    }

    public int getTempoEspera() {
        // Retorna o Tempo de Espera do Personagem.
        return tempoEspera;
    }

    public float getPV() {
        // Retorna os Pontos de vida do Personagem.
        return PV;
    }

    public float getPM() {
        // Retorna os Pontos de Magia do Personagem.
        return PM;
    }

    public boolean getAtordoado() {
        // Retorna o status de atordoamento do Personagem.
        return atordoado;
    }

    public void setTempoEspera(int tempoEspera) {
        // Altera o Tempo de Espera do Personagem.
        this.tempoEspera = tempoEspera;
    }

    public void setPV(float PV) {
        // Altera os Pontos de Vida do Personagem.
        this.PV = PV;
    }

    public void setPM(float PM) {
        // Altera os Pontos de Magia do Personagem.
        this.PM = PM;
    }

    public void setAtordoado(boolean atordoado) {
        // Altera o estado de Atordoamento do Personagem.
        this.atordoado = atordoado;
    }

    public float getPVmax() {
        return PVmax;
    }

    public float getPMmax() {
        return PMmax;
    }


}



