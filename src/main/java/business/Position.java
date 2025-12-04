package business;

public class Position {
    int ligne;
    int colonne;

    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position postion = (Position) o;
        return this.ligne == position.ligne &&
                this.colonne == position.colonne;
    }
}
