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

        // Création 2 joueur
        this.joueurs = new Joueur[2];
        this.joueurs[0] = new Joueur(Couleur.JAUNE);
        this.joueurs[1] = new Joueur(Couleur.ROUGE);

        //Choix aléatoire du joueur du début
        Random random = new Random();
        int indexJoueurDebut = random.nextInt(2);
        this.joueurCourant = joueurs[indexJoueurDebut];

        //Initialiser les autres attributs
        this.partieFinie = false;
        this.gagnant = null;
        this.parAbandon = false;


    }
    public Grille getGrille() {
        return this.grille;
    }
    public Joueur[] getJoueurs() {
        return this.joueurs;
    }
    public Joueur getJoueurCourant() {
        return this.joueurCourant;
    }
    public boolean isPartieFinie() {
        return this.partieFinie;
    }
    public Joueur getGagnant() {
        return this.gagnant;
    }
    public boolean isParAbandon(boolean parAbandon) {
        return parAbandon;
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }
    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }
    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }

    public void changerJoueur() {
        if (this.joueurCourant == this.joueurs[0]) {
            this.joueurCourant = this.joueurs[1];
        } else {
            this.joueurCourant = this.joueurs[0];
        }
    }
}
