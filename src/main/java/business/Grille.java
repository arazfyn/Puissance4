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

    // Vérifie que position est bien contenu dans les limites du tableau. Crée pour getJeton.
    private boolean isPositionValide(Position p) {
        return (p.getOrd() >= 0 && p.getOrd() < NB_LIGNES && p.getAbs() >= 0 && p.getAbs() < NB_COLONNES);
    }

    public Jeton getJeton(Position position) {
        // position == null car si l'objet n'existe le programme crash. -> IllegalArgumentException
        if (position == null || !isPositionValide(position)) {
            throw new IllegalArgumentException("Postion invalide, hors de la grille.");
        }

        int y = position.getOrd(); // n° ligne
        int x = position.getAbs(); // n° colonne

        return plateauJetons[y][x]; // null si la case est vide.
    }

    public boolean isFullColonne(int numColonne) {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La colonne " + numColonne + " est invalide.");
        }
        // [0] car on regarde le haut du tableau.
        return plateauJetons[0][numColonne] != null;
    }


    public int insererJeton(Jeton jeton, int numColonne) {
        // Colonne valide ?
        if (numColonne < 0 || numColonne > NB_COLONNES) {
            throw new IllegalArgumentException("La colonne " + numColonne +" est invalide");
        }
        // Colonne pleine ?
        if (isFullColonne(numColonne)) {
            throw new Puissance4Exception("La colonne " + numColonne + " est pleine.");
        }
        // On cherche la position la plus basse dans la colonne.
        // On commence par le bas.
        int ligneInsertion = -1; // Dernier index
        for (int i = NB_LIGNES - 1; i >= 0; i--) {
            if (plateauJetons[i][numColonne] == null) {
                ligneInsertion = i;
                break;
            }
        }
        //Insertion du jeton à la position libre
        plateauJetons[ligneInsertion][numColonne] = jeton;
        // return l'index de la ligne du jeton inseré.
        return ligneInsertion;
    }

    //Remplie ? oui = vrai , non = faux.
    public boolean isFullGrille() {
        //Parcours la 1 ligne du tableau
        for (int j = 0; j < NB_COLONNES; j++){
            // Si null => tableau pas rempli
            if (plateauJetons[0][j] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean alignementRealise(){
        return true; // pas fini
    }




}


