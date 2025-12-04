package business;

public class Position {

    private int ligne;
    private int colonne;

    public Position(int abs, int ord) {
        // abs = colonne
        this.ligne = abs;
        // ord = ligne
        this.colonne = ord;
    }

    public int getAbs() {
        return colonne;
    }

    public int getOrd() {
        return ligne;
    }
    @Override
    public String toString() {
        return "Ligne   : " + getOrd() + "\n" +
                "Colonne: " + getAbs();
    }

    // La méthode equals me permet de vérifier si 2 objets représentent la même position (en comparant les attributs).
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return (this.ligne == position.ligne) && (this.colonne == position.colonne);
    }
}
