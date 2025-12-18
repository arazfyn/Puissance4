package business;

import java.io.Serializable;

/**
 * Un Jeton possède un attribut couleur de type Couleur.
 * Un Jeton ne change pas → immuable
 */

public class Jeton implements Serializable {

    private final Couleur couleur;

    /**
     * Crée un jeton de la couleur indiquée.
     * @param couleur couleur du jeton.
     */
    public Jeton(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     *
     * @return la couleur de ce jeton.
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Redéfinition de la classe toString
     * @return couleur renvoi la couleur du jeton
     */
    @Override
    public String toString() {
        return "Jeton : " + couleur;
    }
}
