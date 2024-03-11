import java.util.Set;

public abstract class Classe {

    // -------------------------------------------- ATRIBUTOS -------------------------------------------- //
    public int forca;
    public int agilidade;
    public int inteligencia;
    public Set<Habilidade> habilidades;

    // ------------------------------------------ CONSTRUTOR ------------------------------------------- //
    public Classe() {
    }

    // -------------------------------------------- MÉTODOS -------------------------------------------- //
    public void subirNivel() {
    }

    // -------------------------------------------- GETTERS -------------------------------------------- //

    public int getAgilidade() {
        return agilidade;
    }

    public int getForca() {
        return forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public Set<Habilidade> getHabilidades() {
        return habilidades;
    }

} // FECHAMENTO

