public class Habilidade {

    // -------------------------------------------- ATRIBUTOS -------------------------------------------- //
    private final String nome; // Nome da habilidade
    private final PesosDeAtributos pesosDano; // Encapsulamento dos pesos para cálculo de dano das habilidades.
    private final PesosDeAtributos pesosMana; // Encapsulamento dos pesos para cálculo de PM das habilidades.
    private final int tempoEspera; // Tempo de espera adicionado ao personagem após uso da habilidade.
    private final boolean afetaGrupo; // Se afeta todos os personagens da equipe adversária
    private final boolean afetaAmigos; // Se pode ser utilizada em amigos
    private final int ID; // Identificador único da habilidade
    private static int proximoID = 1; // Utilidade


    // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
    public Habilidade(String nome, PesosDeAtributos pesosDano, PesosDeAtributos pesosMana, int tempoEspera, boolean afetaAmigos, boolean afetaGrupo) {
        ID = gerarProximoID();
        this.nome = nome;
        this.tempoEspera = tempoEspera;
        this.pesosDano = pesosDano;
        this.pesosMana = pesosMana;
        this.afetaGrupo = afetaGrupo;
        this.afetaAmigos = afetaAmigos;
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public double calcularDanoCausado(Classe classe) { // Chamado pelo método "danoCausado()" de "Personagem"
        int agilidade = classe.getAgilidade();
        int inteligencia = classe.getInteligencia();
        int forca = classe.getForca();
        double pesoForca = pesosDano.getPesoForca();
        double pesoAgilidade = pesosDano.getPesoAgilidade();
        double pesoInteligencia = pesosDano.getPesoInteligencia();
        return Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));
    }

    public double calcularCustoMana(Classe classe) { // Chamado pelo método "custoMagia()" de "Personagem"
        int agilidade = classe.getAgilidade();
        int inteligencia = classe.getInteligencia();
        int forca = classe.getForca();
        double pesoForca = pesosMana.getPesoForca();
        double pesoAgilidade = pesosMana.getPesoAgilidade();
        double pesoInteligencia = pesosMana.getPesoInteligencia();
        return Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));
    }

    private static int gerarProximoID() {
        return proximoID++;
    }

    // -------------------------------------------- GETTERS -------------------------------------------- //
    public String getNome() {
        return nome;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public boolean getIsAfetaGrupo() {
        return afetaGrupo;
    }

    public boolean getIsAfetaAmigos() {
        return afetaAmigos;
    }

    public int getID() {
        return ID;
    }
}

