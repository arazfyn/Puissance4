package business;
import java.util.Random;

public class Partie {

    private Grille grille;
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean partieFinie;
    private Joueur gagnant;
    private boolean parAbandon;

    public Partie() {
        this.grille = new Grille();

    }

}
