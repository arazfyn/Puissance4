package business;
import java.io.Serializable;
import java.util.Random;

/**
 * Représente l'état d'une partie de Puissance 4.
 * La grille, les deux joueurs, le joueur courant,
 * l'état de fin, l'abandon éventuel, et le gagnant.
 */
public class Partie implements Serializable {

    private Grille grille;
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean partieFinie;
    private Joueur gagnant;
    private boolean parAbandon;

    /**
     * Crée une nouvelle partie : initialise la grille et les 2 joueurs puis choisit aléatoirement qui commence.
     */
    public Partie() {
        grille = new Grille();

        joueurs = new Joueur[2];
        joueurs[0] = new Joueur(Couleur.JAUNE);
        joueurs[1] = new Joueur(Couleur.ROUGE);

        Random random = new Random();
        int indexJoueurDebut = random.nextInt(2);
        joueurCourant = joueurs[indexJoueurDebut];

        partieFinie = false;
        gagnant = null;
        parAbandon = false;


    }

    /**
     * @return la gille de jeu
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @return le talbeau des 2 joueur (JAUNE,ROUGE).
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * @return le joueur dont c'est le tour de jouer.
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * @return true si la partie est fini.
     */
    public boolean isPartieFinie() {
        return partieFinie;
    }

    /**
     * @return le joueur gagnant de la partie est finie ; null sinon.
     */
    public Joueur getGagnant() {
        return gagnant;
    }

    /**
     * @return true si la partie se termine par abandon ;false sinon.
     */
    public boolean isParAbandon() {
        return parAbandon;
    }

    /**
     * @param joueurCourant modifie le joueur qui joue.
     */
    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /**
     * @param partieFinie Définit l'etat de la partie.
     */
    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    /**
     * @param gagnant Définit le gagnant.
     */
    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }

    /**
     * @param parAbandon Marque la partie comme finit par abandon ou non.
     */
    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }

    /**
     * Change de joueur (un "switch" entre joueur JAUNE et ROUGE)
     */
    public void changerJoueur() {
        if (joueurCourant == joueurs[0]) {
            joueurCourant = joueurs[1];
        } else {
            joueurCourant = joueurs[0];
        }
    }
}
