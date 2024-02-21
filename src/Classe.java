import java.util.Set;

public abstract class Classe extends Personagem {

    // Atributos
    public int forca;
    public int agilidade;
    public int inteligencia;
    public Set<Habilidade> habilidades;

    // Construtor padrão
    public Classe() {
        super();
    }
}