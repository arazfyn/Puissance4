package business;
import java.io.Serializable;

/**
 * Représente une position (ligne, colonne) sur la grille.
 * Cette classe est immuable.
 */
public class Position implements Serializable {
    private final int ligne;
    private final int colonne;


    /**
     * Crée une position.
     * @param ligne index de ligne (>=0)
     * @param colonne index de colonne (>= 0)
     * @throws IllegalArgumentException si l'un des indices est négatif.
     */
    public Position(int ligne, int colonne) {
        if (ligne < 0 || colonne < 0) {
            throw new IllegalArgumentException("Postion Invalid(pas de valeur négative pour la position.)");
        }

        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     *
     * @return l'index de la colonne de Position p.
     */
    public int getColonne() {
        return colonne;
    }

    /**
     *
     * @return l'index de la ligne de Position p.
     */
    public int getLigne() {
        return ligne;
    }
    @Override
    public String toString() {
        return "Position(ligne=" + ligne + ", colonne =" + colonne +")";
    }

    /**
     * La méthode equals me permet de vérifier si 2 objets
     * représentent la même position (en comparant les attributs).
     * @param o   la réference de l'objet avec laquelle on le compare.
     * @return Position (int ligne , int colonne) valide.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position that = (Position) o;
        return this.ligne == that.ligne && this.colonne == that.colonne;
    }
}
