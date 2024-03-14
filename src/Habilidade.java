public class Habilidade {

    private final String nome;
    private final PesosDeAtributos pesosDano;
    private final PesosDeAtributos pesosMana;
    private final int tempoEspera;
    private final boolean afetaGrupo;
    private final boolean afetaAmigos;
    private final int ID;
    private static int proximoID = 1;


    // Construtor

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

    // Métodos

    public float calcularDanoCausado(Classe classe) {
        // Retorna a "formula" de calculo de dano da Habilidade. Chamado pelo método "danoCausado()" de "Personagem".
        float agilidade = (float)classe.getAgilidade();
        float inteligencia = (float)classe.getInteligencia();
        float forca = (float)classe.getForca();
        float pesoForca = (float)pesosDano.getPesoForca();
        float pesoAgilidade = (float)pesosDano.getPesoAgilidade();
        float pesoInteligencia = (float)pesosDano.getPesoInteligencia();
        return (float) Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));
    }

    public float calcularCustoMana(Classe classe) {
        // Retorna a "formula" de calculo de custo de mana da Habilidade. Chamado pelo método "custoMagia()" de "Personagem".
        float agilidade = (float)classe.getAgilidade();
        float inteligencia = (float)classe.getInteligencia();
        float forca = (float)classe.getForca();
        float pesoForca = (float)pesosMana.getPesoForca();
        float pesoAgilidade = (float)pesosMana.getPesoAgilidade();
        float pesoInteligencia = (float)pesosMana.getPesoInteligencia();
        return (float)Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));
    }

    private static int gerarProximoID() {
        // Retorna um incremento do ID da última Habilidade instanciada, garantindo ID's únicos para cada Habilidade.
        return proximoID++;
    }

    // Getters

    public String getNome() {
        // Retorna o nome da Habilidade.
        return nome;
    }

    public int getID() {
        // Retorna o ID da Habilidade. Até então não foi utilizado, mas pode ser útil para futuras implementações.
        return ID;
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


}