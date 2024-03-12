public class Habilidade {

    private final String nome;
    private final PesosDeAtributos pesosDano;
    private final PesosDeAtributos pesosMana;
    private final int tempoEspera;
    private final boolean afetaGrupo;
    private final boolean afetaAmigos;
    private final int ID;
    private static int proximoID = 1;

    public Habilidade(String nome, PesosDeAtributos pesosDano, PesosDeAtributos pesosMana, int tempoEspera, boolean afetaAmigos, boolean afetaGrupo) {
        // Construtor completo da Habilidade. Deverá ser chamado no momento de instanciamento de uma classe herdeira de "Classe".
        ID = gerarProximoID();
        this.nome = nome;
        this.tempoEspera = tempoEspera;
        this.pesosDano = pesosDano;
        this.pesosMana = pesosMana;
        this.afetaGrupo = afetaGrupo;
        this.afetaAmigos = afetaAmigos;
    }

    public double calcularDanoCausado(Classe classe) {
        // Retorna a "formula" de calculo de dano da Habilidade. Chamado pelo método "danoCausado()" de "Personagem".
        int agilidade = classe.getAgilidade();
        int inteligencia = classe.getInteligencia();
        int forca = classe.getForca();
        double pesoForca = pesosDano.getPesoForca();
        double pesoAgilidade = pesosDano.getPesoAgilidade();
        double pesoInteligencia = pesosDano.getPesoInteligencia();
        return Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));
    }

    public double calcularCustoMana(Classe classe) {
        // Retorna a "formula" de calculo de custo de mana da Habilidade. Chamado pelo método "custoMagia()" de "Personagem".
        int agilidade = classe.getAgilidade();
        int inteligencia = classe.getInteligencia();
        int forca = classe.getForca();
        double pesoForca = pesosMana.getPesoForca();
        double pesoAgilidade = pesosMana.getPesoAgilidade();
        double pesoInteligencia = pesosMana.getPesoInteligencia();
        return Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));
    }

    private static int gerarProximoID() {
        // Retorna um incremento do ID da última Habilidade instanciada, garantindo ID's únicos para cada Habilidade.
        return proximoID++;
    }

    public String getNome() {
        // Retorna o nome da Habilidade.
        return nome;
    }

    public int getTempoEspera() {
        // Retorna o Tempo de Espera relacionado à Habilidade.
        return tempoEspera;
    }

    public boolean getIsAfetaGrupo() {
        // Retorna se a Habilidade pode ser usada contra grupos.
        return afetaGrupo;
    }

    public boolean getIsAfetaAmigos() {
        // Retorna se a Habilidade pode ser usada em integrantes da mesma Equipe do Personagem.
        return afetaAmigos;
    }

    public int getID() {
        // Retorna o ID da Habilidade.
        return ID;
    }
}