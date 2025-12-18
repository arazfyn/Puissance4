package business;

import java.io.Serializable;
import java.util.Objects;

/**
 * Représente un joueur du jeu Puissance 4.
 *
 * Un joueur est identifié par sa couleur (JAUNE ou ROUGE) et est immuable.
 */
public class Joueur implements Serializable {

    private final Couleur nom;

    /**
     * Crée un joueur de la couleur indiquée.
     * @param nom la couleur du joueur (JAUNE ou ROUGE)
     * @throws NullPointerException si {@code nom} est {@code null}
     */
    public Joueur(Couleur nom) {
        this.nom = Objects.requireNonNull(nom, "La couleur du joueur ne peut pas être null.");
    }

    /**
     *
     * @return {@code nom} la couleur du joueur.
     */
    public Couleur getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Joueur(" + nom + "Joueur(" + nom + ")";
    }

}
