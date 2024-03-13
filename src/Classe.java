import java.util.Set;

public abstract class Classe {

    protected int forca;
    protected int agilidade;
    protected int inteligencia;
    protected Set<Habilidade> habilidades;

    public Classe() {
        // Construtor padrão.
    }

    public void subirNivel() {
        // Método abstrato que aumentará os atributos das classes.
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public int getAgilidade() {
        // Retorna a agilidade. Método padrão para todas classes herdam de Classe.
        return agilidade;
    }

    public int getForca() {
        // Retorna a forca. Método padrão para todas classes herdam de Classe.
        return forca;
    }

    public int getInteligencia() {
        // Retorna a inteligencia. Método padrão para todas classes herdam de Classe.
        return inteligencia;
    }

    public Set<Habilidade> getHabilidades() {
        // Retorna o conjunto de Habilidades. Método padrão para todas classes herdam de Classe.
        return habilidades;
    }
}