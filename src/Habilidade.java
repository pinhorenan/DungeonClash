public class Habilidade {

    private final String nome; // Nome da habilidade
    private final PesosDeAtributos pesosDano;
    private final PesosDeAtributos pesosMana;
    private final int tempo; // Tempo de espera associado a habilidade
    private final boolean afetaGrupo; // Se afeta todos os personagens da equipe adversária
    private final boolean afetaAmigos; // Se pode ser utilizada em amigos
    private static int ID; // Identificador único da habilidade

    public Habilidade(String nome, PesosDeAtributos pesosDano, PesosDeAtributos pesosMana, int tempo, boolean afetaAmigos, boolean afetaGrupo) {
        this.ID = 0;
        this.nome = nome;
        this.tempo = tempo;
        this.pesosDano = pesosDano;
        this.pesosMana = pesosMana;
        this.afetaGrupo = afetaGrupo;
        this.afetaAmigos = afetaAmigos;
    }

    // Métodos

    public double calcularDanoCausado(Classe classe) {
        int agilidade = classe.getAgilidade();
        int inteligencia = classe.getInteligencia();
        int forca = classe.getForca();
        double pesoForca = pesosDano.getPesoForca();
        double pesoAgilidade = pesosDano.getPesoAgilidade();
        double pesoInteligencia = pesosDano.getPesoInteligencia();
        return Personagem.getNivel()*Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));
    }

    public double calcularCustoMana(Classe classe) {
        int agilidade = classe.getAgilidade();
        int inteligencia = classe.getInteligencia();
        int forca = classe.getForca();
        double pesoForca = pesosMana.getPesoForca();
        double pesoAgilidade = pesosMana.getPesoAgilidade();
        double pesoInteligencia = pesosMana.getPesoInteligencia();
        return Personagem.getNivel()*Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));
    }

    private static int gerarProximoID() {
        return proximoID++;
    }

    // Getters

    public String getNome() {
        return nome;
    }

    public int getTempo() {
        return tempo;
    }

    public boolean isAfetaGrupo() {
        return afetaGrupo;
    }

    public boolean isAfetaAmigos() {
        return afetaAmigos;
    }
}

