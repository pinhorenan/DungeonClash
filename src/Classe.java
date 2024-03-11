import java.util.Set;

public abstract class Classe {
    // Atributos
    public int forca;
    public int agilidade;
    public int inteligencia;
    public Set<Habilidade> habilidades;

    // Construtor padrão
    public Classe() {}

    // Getters


    public int getAgilidade() {
        return agilidade;
    }

    public int getForca() {
        return forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }
}
