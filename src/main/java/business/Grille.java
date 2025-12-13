package business;

/**
 * Représente la grille de jeu Puissance 4 (NB_LIGNES x NB_COLONNES).
 */

public class Grille {
    /**
     * On utilise les attributs de Config.java pour définir le nombre de lignes et de colonnes dans la grille.
    */
    public static final int NB_LIGNES = Config.NB_LIGNES;
    public static final int NB_COLONNES = Config.NB_COLONNES;
    
    private final Jeton[][] plateauJetons;


    /**
     * Constructeur par défaut = crée une grille vide.
     */
    public Grille() {
        this.plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }


    /**
     * Ce constructeur ne sera utilisé que pour les tests. Il initialise la grille avec le tableau passé en paramètre.
     * @param plateauJetons tableau des jetons à utiliser
     * @throws Puissance4Exception si le tableau est null ou si ses dimensions ne sont pas celle données dans Config
     */

    public Grille (Jeton [][] plateauJetons) {
        if (plateauJetons == null
                || plateauJetons.length != NB_LIGNES
                || plateauJetons[0] == null
                || plateauJetons[0].length != NB_COLONNES) {
            throw new Puissance4Exception("Grille invalide, mauvaise dimension.");
        }

        this.plateauJetons = plateauJetons;
    }

    /**
     * Vérifie si position est dans les bornes de la grille.
     * @param p position à tester
     * @throws IllegalArgumentException c < 0 || c >= NB_COLONNES || l < 0 || l > NB_LIGNES
     */
    public static void checkPositionDansGrille(Position p) {
        if (p == null) {
            throw new IllegalArgumentException("Position invalide :  Jeton null.");
        }
        int l = p.getLigne();
        int c = p.getColonne();
        if(l < 0 || l >= NB_LIGNES || c < 0 || c >= NB_COLONNES) {
            throw new IllegalArgumentException("Position hors de la grille. Colonne :" + c +"0 < colonne < " + NB_COLONNES +
                    "Ligne :"+ l +"0 < ligne < "+NB_LIGNES);
        }
    }

    /**
     * Retourne la position de jeton , null si la case est vide.
     * @param position la position tester
     * @return renvoi le Jeton de la position tester
     */
    public Jeton getJeton(Position position) {
        checkPositionDansGrille(position);
        return plateauJetons[position.getLigne()][position.getColonne()]; // null si la case est vide.
    }

    /**
     * @param numColonne la colonne tester
     * @return true si la colonne est pleine.
     * @throws IllegalArgumentException si numColonne est hors bornes
     */
    public boolean isFullColonne(int numColonne) {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide : "+ numColonne);
        }
        return plateauJetons[0][numColonne] != null;
    }

    /**
     * Insère le jeton dans la colonne indiquée, dans la case libre la plus basse
     * @param jeton
     * @param numColonne
     * @return
     */
    public int insererJeton(Jeton jeton, int numColonne) {
        if (numColonne < 0 || numColonne > NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide: "+ numColonne);
        }
        if (isFullColonne(numColonne)) {
            throw new Puissance4Exception("La colonne " + numColonne + " est pleine.");
        }
        if (jeton == null) {
            throw new IllegalArgumentException("Jeton null interdit");
        }
        for (int i = NB_LIGNES - 1; i >= 0; i--) {
            if (plateauJetons[i][numColonne] == null) {
                plateauJetons[i][numColonne] = jeton;
                return i;
            }
        }
        throw new Puissance4Exception("Insertion impossible : colonne pleine.");
    }

    /**
     * On test la ligne 0 car si elle est pleine => toute la grille l'est.
     * @return true si toute le grille est remplie; false sinon
     */
    public boolean isFullGrille() {

        for (int j = 0; j < NB_COLONNES; j++){
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


