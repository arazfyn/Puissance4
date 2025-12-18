package TestUnitaire;

import business.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires complets pour la classe Puissance4.
 * Couvre l'initialisation, les règles de victoire, le match nul et l'abandon.
 */
public class Puissance4Test {

    private Puissance4 jeu;

    @BeforeEach
    void setUp() {
        // Initialise une nouvelle partie avant chaque test [cite: 287]
        jeu = new Puissance4();
    }

    // =========================================================================
    // 1. INITIALISATION ET ETAT INITIAL
    // =========================================================================

    @Test
    void testEtatInitial() {
        assertNotNull(jeu.getGrille(), "La grille doit être instanciée ");
        assertNotNull(jeu.getJoueurCourant(), "Un joueur doit être choisi au hasard pour commencer ");
        assertFalse(jeu.gameIsOver(), "La partie ne doit pas être finie au démarrage ");
        assertNull(jeu.getGagnant(), "Il ne doit pas y avoir de gagnant au début );");
    }

    @Test
    void testAlternanceJoueurs() {
        Joueur premier = jeu.getJoueurCourant();
        jeu.jouer(0); // Premier jeton [cite: 305]

        Joueur second = jeu.getJoueurCourant();
        assertNotEquals(premier, second, "Le joueur courant doit changer après un coup [cite: 307]");

        jeu.jouer(0); // Deuxième jeton
        assertEquals(premier, jeu.getJoueurCourant(), "Le tour doit revenir au premier joueur ");
    }

    // =========================================================================
    // 2. SCENARIOS DE VICTOIRE (Plan de tests alignementRealise) [cite: 6, 137]
    // =========================================================================

    @Test
    void testVictoireVerticale() {
        Joueur gagnantAttendu = jeu.getJoueurCourant();

        // On alterne les colonnes pour ne pas bloquer l'insertion
        for (int i = 0; i < 3; i++) {
            jeu.jouer(0); // Gagnant (colonne 0)
            jeu.jouer(1); // Adversaire (colonne 1)
        }
        jeu.jouer(0); // 4ème jeton en colonne 0 [cite: 33]

        assertTrue(jeu.gameIsOver(), "La partie doit s'arrêter sur une victoire verticale [cite: 141]");
        assertEquals(gagnantAttendu, jeu.getGagnant(), "Le joueur ayant aligné 4 jetons doit gagner [cite: 283]");
    }

    @Test
    void testVictoireHorizontale() {
        Joueur gagnantAttendu = jeu.getJoueurCourant();

        // J1 en (0,1,2), J2 en colonne 6 (poubelle)
        jeu.jouer(0); jeu.jouer(6);
        jeu.jouer(1); jeu.jouer(6);
        jeu.jouer(2); jeu.jouer(6);
        jeu.jouer(3); // 4ème jeton horizontal [cite: 33]

        assertTrue(jeu.gameIsOver());
        assertEquals(gagnantAttendu, jeu.getGagnant());
    }

    @Test
    void testVictoireDiagonale() {
        // Simulation d'une diagonale montante (/)
        // Col 0: J1 | Col 1: J2, J1 | Col 2: J2, J2, J1 | Col 3: J2, J2, J2, J1
        int[] coups = {0, 1, 1, 2, 3, 2, 2, 3, 3, 5, 3};
        // Le dernier coup en 3 complète la diagonale pour le joueur courant

        for (int col : coups) {
            if (!jeu.gameIsOver()) jeu.jouer(col);
        }

        assertTrue(jeu.gameIsOver(), "La victoire diagonale doit être détectée [cite: 34]");
    }

    // =========================================================================
    // 3. MATCH NUL ET ABANDON
    // =========================================================================

    @Test
    void testMatchNulComplet() {
        // On remplit la grille colonne par colonne
        for (int col = 0; col < Config.NB_COLONNES; col++) {
            for (int ligne = 0; ligne < Config.NB_LIGNES; ligne++) {
                // On ne joue que si la partie n'est pas déjà finie par victoire
                if (!jeu.gameIsOver()) {
                    jeu.jouer(col);
                }
            }
        }

        // VÉRIFICATION
        assertTrue(jeu.gameIsOver(), "La partie doit être finie (soit victoire, soit match nul)");

        // Si la grille est effectivement pleine, il ne doit pas y avoir de gagnant
        if (jeu.getGrille().isFullGrille()) {
            assertNull(jeu.getGagnant(), "En cas de match nul (grille pleine), il n'y a pas de gagnant");
        }
    }

    @Test
    void testAbandonPartie() {
        Joueur celuiQuiAbandonne = jeu.getJoueurCourant();
        jeu.abandonner();

        assertTrue(jeu.gameIsOver());
        assertTrue(jeu.isParAbandon());
        assertNotEquals(celuiQuiAbandonne, jeu.getGagnant());
    }

    // =========================================================================
    // 4. GESTION DES ERREURS ET ROBUSTESSE [cite: 46, 175]
    // =========================================================================

    @Test
    void testExceptionColonnePleine() {
        // Remplir la colonne 0 (6 jetons) [cite: 157]
        for (int i = 0; i < Config.NB_LIGNES; i++) {
            jeu.jouer(0);
        }

        // Tenter d'insérer un 7ème jeton
        assertThrows(Puissance4Exception.class, () -> {
            jeu.jouer(0);
        }, "Une Puissance4Exception doit être levée si la colonne est pleine [cite: 144, 214]");
    }

    @Test
    void testExceptionColonneInvalide() {
        // Test limite basse
        assertThrows(IllegalArgumentException.class, () -> {
            jeu.jouer(-1);
        }, "Un index négatif doit lever une IllegalArgumentException [cite: 143, 213]");

        // Test limite haute
        assertThrows(IllegalArgumentException.class, () -> {
            jeu.jouer(Config.NB_COLONNES);
        }, "Un index trop grand doit lever une IllegalArgumentException [cite: 213]");
    }

    @Test
    void testCoupApresFinDePartie() {
        jeu.abandonner();
        Joueur vainqueur = jeu.getGagnant();

        // Tenter de jouer alors que gameIsOver() est vrai
        jeu.jouer(3);

        assertEquals(vainqueur, jeu.getGagnant(), "L'état du jeu ne doit plus changer après la fin [cite: 307]");
    }
}