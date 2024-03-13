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
        PVmax = classe.forca + ((float) classe.agilidade / 2);
        PMmax = classe.inteligencia + ((float) classe.agilidade / 3);
        PV = PVmax;
        PM = PMmax;
        atordoado = false;
    }

    @Override
    public int compareTo(Personagem outro) {
        // Define um método de comparação entre instâncias de Personagens para comparar seus tempoEspera. Chamado para definir o próximoAtacante.
        return Integer.compare(this.getTempoEspera(), outro.getTempoEspera());
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

    public void sofrerDano(int dano) {
        // Subtrai o PV do Personagem que chamar pelo int Dano. Chamado durante uso de habilidades.
        if (PV > 0) {
            PV -= dano;
        }
    }

    public int custoMana(Habilidade habilidade) {
        // Retorna o custo em PE ao usar uma habilidade dependendo do nível do personagem que chamar.
        return (int) (nivel * habilidade.calcularCustoMana(classe));
    }

    public int danoCausado(Habilidade habilidade) {
        // Retorna o dano em PV ao usar uma habilidade dependendo do nível do personagem que chamar.
        return (int) (nivel * habilidade.calcularDanoCausado(classe));
    }

    public void usarHabilidade(Habilidade habilidade, Personagem alvo) {
        // Método para usar a Habilidade. Primeiro irá verificar se quem chamou cumpre os requisitos e então aplica os efeitos no Alvo.
        if (verificarRestricoes(habilidade, alvo)&&verificarRestricoes(habilidade)) {
            aplicarEfeitoHabilidade(habilidade, alvo);
        }
    }

    public void usarHabilidade(Habilidade habilidade, Equipe grupoAlvo) {
        // Método para usar a Habilidade. Primeiro irá verificar se quem chamou cumpre os requisitos e então aplica os efeitos no grupoAlvo.
        if (verificarRestricoes(habilidade)) {
            aplicarEfeitoHabilidade(habilidade, grupoAlvo);
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
            alvo.setPV(alvo.getPV() + danoCausado(habilidade));
        } else {
            alvo.sofrerDano(danoCausado(habilidade));
        }
        setPM(this.getPM() - custoMana(habilidade));
        if (alvo.PV == 0) {
            alvo.atordoar();
        }
    }

    private void aplicarEfeitoHabilidade(Habilidade habilidade, Equipe grupoAlvo) {
        // Método que aplica os efeitos de uma habilidade usada, é chamado por "usarHabilidade()"; Versão para ataque em Equipes.
        Set<Personagem> integrantes = grupoAlvo.getIntegrantes();
        for (Personagem personagem : integrantes) {
            personagem.sofrerDano(danoCausado(habilidade));
        }
    }

    public void ganharPE(int PE) {
        // Incrementa Pontos de Experiência ao Personagem.
        this.PE += PE;
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

    public int getPE() {
        // Retorna os Pontos de Experiência do Personagem.
        return PE;
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

    public void setPE(int PE) {
        // Altera os Pontos de Experiência do Personagem.
        this.PE = PE;
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
}



