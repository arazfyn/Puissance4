package business;
import java.util.Random;

public class Grille {

    public static final int NB_LIGNES = Config.NB_LIGNES;
    public static final int NB_COLONNES = Config.NB_COLONNES;
    private Jeton[][] plateauJetons;

    //Constructeur pas défault : grille vide.
    public Grille() {

        this.plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    // Ce constructeur ne sera utilisé que pour les tests.
    //Il initialise la grille avec le tableau passé en paramètre.
    public Grille (Jeton [][] plateauJetons) {

        this.plateauJetons = plateauJetons;
    }

    public Jeton getJeton(Position position) {
        if (position == null || !isPositionValide(position)) {
            throw new IllegalArgumentException("Postion invalide, hors de la grille.");

        }
        int y = position.getOrd(); // n° ligne
        int x = position.getAbs(); // n° colonne

        return plateauJetons[y][x];
    }

    public boolean isFullColonne(int numColonne) {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La colonne " + numColonne + " est invalide.");
        }
        // [0] car on regarde le haut du tableau.
        return plateauJetons[0][numColonne] != null;
    }

    // Vérifie que position est bien contenu dans les limites du tableau.
    private boolean isPositionValide(Position p) {
        return p.getOrd() >= 0 && p.getOrd() < NB_LIGNES && p.getAbs() >= 0 && p.getAbs() < NB_COLONNES;
    }

    public int insererJeton(Jeton jeton, int numColonne) {
        return 0;
    }


}


