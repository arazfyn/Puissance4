package business;

import java.io.*;
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

    public void sauvegarder(String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            // On sauvegarde l'objet interne 'puissance4' qui est de type Partie [cite: 74, 295]
            oos.writeObject(this.puissance4);
            System.out.println("Partie sauvegardée avec succès dans " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Charge une partie depuis un fichier et retourne une nouvelle instance de Puissance4.
     * @param nomFichier le fichier à lire
     * @return une instance de Puissance4 restaurée, ou null en cas d'erreur
     */
    public static Puissance4 charger(String nomFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            // Lecture de l'objet Partie sauvegardé [cite: 124, 270]
            Partie partieChargee = (Partie) ois.readObject();
            // Utilisation du constructeur de récupération spécifié dans l'analyse [cite: 76, 300]
            return new Puissance4(partieChargee);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
            return null;
        }
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
