package business;

import java.util.Objects;

/**
 * Classe permettant le fonctionnement du jeu Puissance 4.
 * Reprenant la classe Partie {@link Partie}, on pourra :
 *jouer un coup, abandonner, tester la fin de la partie.
 */
public class Puissance4 {

    private final Partie puissance4;

    /**
     * Constructeur par défaut : crée une nouvelle partie.
     */
    public Puissance4(){
        puissance4 = new Partie();
    }

    /**
     * "Constructeur" de chargement de partie : crée une partie à partir d'une partie existante.
     * @param puissance4 partie sauvegardée à reprendre.
     * @throws NullPointerException si {@code puissance4} est {@code null}.
     */
    public Puissance4(Partie puissance4) {
        this.puissance4 = Objects.requireNonNull(puissance4, "Partie ne peut pas être null.");
    }

    public Grille getGrille() {
        return puissance4.getGrille();
    }

    public Joueur getJoueurCourant() {
        return puissance4.getJoueurCourant();
    }


    public boolean isParAbandon() {
        return puissance4.isParAbandon();
    }

    public Joueur getGagnant() {
        return puissance4.getGagnant();
    }

        /**
         * @return {@code true} si la partie est finie ; {@code false} sinon.
         */
    public boolean gameIsOver() {
        return puissance4.isPartieFinie();
    }

    public void jouer(int numColonne) {

        if (puissance4.isPartieFinie()) {
            return;
        }

        Joueur joueurCourant = puissance4.getJoueurCourant();
        Jeton jeton = new Jeton(joueurCourant.getNom());

        int ligneJ = puissance4.getGrille().insererJeton(jeton, numColonne);

        Position pos = new Position(ligneJ, numColonne);
        boolean gagne = puissance4.getGrille().alignementRealise(pos);

        if (gagne) {
            puissance4.setPartieFinie(true);
            puissance4.setGagnant(joueurCourant);
            return;
        }

        if (puissance4.getGrille().isFullGrille()) {
            puissance4.setPartieFinie(true);
            puissance4.setGagnant(null);
            return;
        }
        puissance4.changerJoueur();
    }

    public void abandonner() {
        if (puissance4.isPartieFinie()) {
            return;
        }

        Joueur courant = puissance4.getJoueurCourant();
        Joueur[] js = puissance4.getJoueurs();
        Joueur autre;
        if (courant == js[0]) {
            autre = js[1];
        }else {
            autre = js[0];
        }
        puissance4.setParAbandon(true);
        puissance4.setGagnant(autre);
        puissance4.setPartieFinie(true);
    }
}
