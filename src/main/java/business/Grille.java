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
     *
     * @param plateauJetons tableau des jetons à utiliser
     * @throws Puissance4Exception si le tableau est null ou si ses dimensions ne sont pas celle données dans Config
     */

    public Grille(Jeton[][] plateauJetons) {

        if (plateauJetons == null || plateauJetons.length != NB_LIGNES) {
            throw new Puissance4Exception("Grille invalide : " + NB_LIGNES + " lignes attendues.");
        }
        for (int i = 0; i < NB_LIGNES; i++) {
            if (plateauJetons[i] == null || plateauJetons[i].length != NB_COLONNES) {
                throw new Puissance4Exception(
                        "Grille invalide : ligne " + i + " doit avoir " + NB_COLONNES + " colonnes."
                );
            }
        }
        this.plateauJetons = plateauJetons;
    }

    /**
     * Vérifie si position est dans les bornes de la grille.
     *
     * @param p position à tester
     * @throws IllegalArgumentException c < 0 || c >= NB_COLONNES || l < 0 || l > NB_LIGNES
     */
    public static void checkPositionDansGrille(Position p) {
        if (p == null) {
            throw new IllegalArgumentException("Position invalide :  Position null.");
        }
        int l = p.getLigne();
        int c = p.getColonne();
        if (l < 0 || l >= NB_LIGNES || c < 0 || c >= NB_COLONNES) {
            throw new IllegalArgumentException("Position hors de la grille. Colonne :" + c + "0 < colonne <= " + NB_COLONNES +
                    "Ligne :" + l + "0 < ligne <= " + NB_LIGNES);
        }
    }

    /**
     * Retourne la position de jeton , null si la case est vide.
     *
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
            throw new IllegalArgumentException("La colonne est invalide : " + numColonne);
        }
        return plateauJetons[0][numColonne] != null;
    }

    /**
     * Insère le jeton dans la colonne indiquée, dans la case libre la plus basse
     *
     * @param jeton      à insérer (non null)
     * @param numColonne position de la colonne choisie (0 <= numColonne < NB_COLONNE)
     * @return l'index de la ligne inserée
     * @throws IllegalArgumentException si la colonne est invalide / jeton null
     * @throws Puissance4Exception      si la colonne est pleine
     */
    public int insererJeton(Jeton jeton, int numColonne) {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide: " + numColonne);
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
     *
     * @return true si toute le grille est remplie; false sinon
     */
    public boolean isFullGrille() {

        for (int j = 0; j < NB_COLONNES; j++) {
            if (plateauJetons[0][j] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test s'il existe un alignement (horizontal/vertical/diagonale) de 4 jetons de même couleur
     * passant par la poisition donné.
     *
     * @param position position de base.
     * @return true s'il y a un alignement de 4; false sinon.
     * @throws IllegalArgumentException si position hors limite de la grille.
     */
    public boolean alignementRealise(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        checkPositionDansGrille(position);

        if (plateauJetons[ligne][colonne] == null) {
            return false;
        }
        return alignementHorizontal(position) >= 4 || alignementVertical(position) >= 4 || alignementDiagonal1(position) >= 4 || alignementDiagonal2(position) >= 4;
    }

    /**
     * Test les alignement horizontalement (gauche + droite) de la même couleur passant par la position donné.
     *
     * @param position position de base (utilise pour le centre).
     * @return {@code nbAlignes} nombre d'alignement; 0 si la case est vide.
     * @throws IllegalArgumentException si la position est hors limite.
     */
    public int alignementHorizontal(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        checkPositionDansGrille(position);

        Jeton centre = plateauJetons[ligne][colonne];
        if (centre == null) return 0;

        Couleur couleur = centre.getCouleur();

        int nbAligne = 1;
        int c = colonne - 1;
        while (c >= 0 && c < NB_COLONNES) {
            Jeton jTest = plateauJetons[ligne][c];
            if (jTest != null && jTest.getCouleur() == couleur) {
                nbAligne++;
                c--;
            } else {
                break;
            }
        }

        c = colonne + 1;
        while (c < NB_COLONNES && c >= 0) {
            Jeton jTest = plateauJetons[ligne][c];
            if (jTest != null && jTest.getCouleur() == couleur) {
                nbAligne++;
                c++;
            } else {
                break;

            }
        }

        return nbAligne;
    }

    /**
     * Retourne le nombre de jetons alignés verticalement (haut + bas) de la même couleur passant par la position
     * de base
     *
     * @param position de base.
     * @return {@code nbAlignes} nombre d'alignement vertical; 0 si case vide.
     * @throws IllegalArgumentException si la position est hors limite de la grille.
     */
    public int alignementVertical(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        checkPositionDansGrille(position);

        Jeton centre = plateauJetons[ligne][colonne];
        if (centre == null) return 0;

        Couleur couleur = centre.getCouleur();

        int nbAligne = 1;

        int l = ligne - 1;
        while (l >= 0 && l < NB_LIGNES) {
            Jeton jTest = plateauJetons[l][colonne];
            if (jTest != null && jTest.getCouleur() == couleur) {
                nbAligne++;
                l--;
            } else {
                break;
            }
        }

        l = ligne + 1;
        while (l >= 0 && l < NB_LIGNES) {
            Jeton jTest = plateauJetons[l][colonne];
            if (jTest != null && jTest.getCouleur() == couleur) {
                nbAligne++;
                l++;
            } else {
                break;
            }
        }

        return nbAligne;
    }

    /**
     * Diagonale "\" haut-gauche + bas-droite. Retourne le nombre d'alignement de 4 et de même couleur.
     *
     * @param position position de base donnée.
     * @return {@code nbAlignes} nombre d'alignement diagonal; 0 si case vide.
     * @throws IllegalArgumentException si la position est hors limite de la grille.
     */
    public int alignementDiagonal1(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        checkPositionDansGrille(position);

        Jeton centre = plateauJetons[ligne][colonne];
        if (centre == null) return 0;
        Couleur couleur = centre.getCouleur();

        int nbAligne = 1;

        int l = ligne - 1;
        int c = colonne - 1;
        while (l >= 0 && c >= 0 && l < NB_LIGNES && c < NB_COLONNES) {
            Jeton jTest = plateauJetons[l][c];
            if (jTest != null && jTest.getCouleur() == couleur) {
                nbAligne++;
                l--;
                c--;
            } else {
                break;
            }
        }

        l = ligne + 1;
        c = colonne + 1;
        while (l >= 0 && c >= 0 && l < NB_LIGNES && c < NB_COLONNES) {
            Jeton jTest = plateauJetons[l][c];
            if (jTest != null && jTest.getCouleur() == couleur) {
                nbAligne++;
                l--;
                c--;
            } else {
                break;
            }
        }

        return nbAligne;
    }

    /**
     * Diagonale "/" haut-droite + bas-gauche. Retourne le nombre d'alignement de 4 et de même couleur.
     *
     * @param position position de base donnée.
     * @return {@code nbAlignes} nombre d'alignement diagonal; 0 si case vide
     * @throws IllegalArgumentException si la position est hors limite de la grille.
     */
    public int alignementDiagonal2(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        checkPositionDansGrille(position);

        Jeton centre = plateauJetons[ligne][colonne];
        if (centre == null) return 0;
        Couleur couleur = centre.getCouleur();

        int nbAligne = 1;

        int l = ligne - 1;
        int c = colonne + 1;
        while (l >= 0 && c >= 0 && l < NB_LIGNES && c < NB_COLONNES) {
            Jeton jTest = plateauJetons[l][c];
            if (jTest != null && jTest.getCouleur() == couleur) {
                nbAligne++;
                l--;
                c++;
            } else {
                break;
            }
        }

        l = ligne + 1;
        c = colonne - 1;
        while (l >= 0 && c >= 0 && l < NB_LIGNES && c < NB_COLONNES) {
            Jeton jTest = plateauJetons[l][c];
            if (jTest != null && jTest.getCouleur() == couleur) {
                nbAligne++;
                l++;
                c--;
            } else {
                break;
            }
        }

        return nbAligne;
    }

    /**
     * Affichage de la grille.
     * @return une grille avec ses indexs.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        sb.append("   ");
        for (int c = 0; c < NB_COLONNES; c++) {
            sb.append(c);
            if (c < NB_COLONNES - 1) sb.append(' ');
        }
        sb.append('\n');

        for (int l = 0; l < NB_LIGNES; l++) {
            sb.append(l).append("  ");
            for (int c = 0; c < NB_COLONNES; c++) {
                Jeton j = plateauJetons[l][c];
                char ch;
                if (j == null) {
                    ch = '.';
                } else {
                    ch = (j.getCouleur() == Couleur.JAUNE) ? 'J' : 'R';
                }
                sb.append(ch);
                if (c < NB_COLONNES - 1) sb.append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}


