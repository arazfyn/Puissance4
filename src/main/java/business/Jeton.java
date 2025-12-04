package business;

public class Jeton {
    Couleur couleur;

    public Jeton(Couleur couleur) {
        this.couleur = couleur;
    }
    @Override
    public String toString() {
        return "Jeton : " + this.couleur;
    }
}
