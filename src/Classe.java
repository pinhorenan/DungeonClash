import java.util.Set;

public abstract class Classe {
// ESSA CLASSE NÃO PRECISAVA EXISTIR PQP DAVA PRA SER TUDO NO PERSONAGEM
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
