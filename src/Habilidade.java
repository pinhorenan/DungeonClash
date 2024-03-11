public class Habilidade {

    private final String nome; // Nome da habilidade
    private final PesosDeAtributos pesosDano;
    private final PesosDeAtributos pesosMana; // Define pesos de dano e mana(magia) associados a habilidade
    private final int tempo; // Tempo de espera associado a habilidade
    private final boolean afetaGrupo; // Se afeta todos os personagens da equipe adversária
    private final boolean afetaAmigos; // Se pode ser utilizada em amigos
    private static int ID; // Identificador único da habilidade

    public Habilidade(String nome, PesosDeAtributos pesosDano, PesosDeAtributos pesosMana, int tempo, boolean afetaAmigos, boolean afetaGrupo) {
        ID = 0;
        this.nome = nome;
        this.tempo = tempo;
        this.pesosDano = pesosDano;
        this.pesosMana = pesosMana;
        this.afetaGrupo = afetaGrupo;
        this.afetaAmigos = afetaAmigos;
    }

    // Métodos

    public double calculaDanoCausado(Classe classe) {
        int agilidade = classe.getAgilidade();
        int inteligencia = classe.getInteligencia();
        int forca = classe.getForca();
        double pesoForca = pesosDano.getPesoForca();
        double pesoAgilidade = pesosDano.getPesoAgilidade();
        double pesoInteligencia = pesosDano.getPesoInteligencia();
        return Personagem.getNivel()*Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));

    }

    public double calculaCustoMana(Classe classe) {
        int agilidade = classe.getAgilidade();
        int inteligencia = classe.getInteligencia();
        int forca = classe.getForca();
        double pesoForca = pesosMana.getPesoForca();
        double pesoAgilidade = pesosMana.getPesoAgilidade();
        double pesoInteligencia = pesosMana.getPesoInteligencia();
        return Personagem.getNivel()*Math.ceil((forca * pesoForca) + (agilidade * pesoAgilidade) + (inteligencia * pesoInteligencia));

    };

    public void usarHabilidade(){

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

