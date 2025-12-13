package business;
/**
 * Puissance4Exception permet de signaler un problème dans le déroulement du
 * jeu Puissance 4.
 * Exemple : tentative d'insertion d'un jeton dans une colonne pleine.
 */

public class Puissance4Exception extends RuntimeException{

    /**
     *  Crée une exception sans message
     */
    public Puissance4Exception() {
        super();
    }

    /**
     * Crée une exception avec un message explicatif.
     * @param message description du problème métier survenu
     */
    public Puissance4Exception(String message) {
        super(message);
    }
}
