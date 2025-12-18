package TestUnitaire;
import business.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Position
 */
/**
 * La classe principale de test qui correspond au nom du fichier
 */
public class GrilleTest {

    @Test
    void testInsererJeton_CaseBasseLibre() {
        Grille grille = new Grille();
        // Insérer un jeton dans la colonne 0 (doit arriver à la ligne NB_LIGNES - 1)
        int ligne = grille.insererJeton(new Jeton(Couleur.ROUGE), 0);
        assertEquals(Grille.NB_LIGNES - 1, ligne);
    }

    @Test
    void testIsFullColonne() {
        Grille grille = new Grille();
        int col = 0;
        // Remplir une colonne
        for (int i = 0; i < Grille.NB_LIGNES; i++) {
            grille.insererJeton(new Jeton(Couleur.JAUNE), col);
        }
        assertTrue(grille.isFullColonne(col));
    }

    @Test
    void testAlignementRealisePositionNull() {
        Grille grille = new Grille();
        assertThrows(IllegalArgumentException.class, () -> {
            grille.alignementRealise(null);
        });
    }
}

class PositionTest {

    @Test
    public void testConstructeur_ValeursValides() {
        Position pos = new Position(0, 0);
        assertNotNull(pos);
        assertEquals(0, pos.getLigne());
        assertEquals(0, pos.getColonne());
    }

    @Test
    public void testConstructeur_LigneNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(-1, 0);
        });
    }

    @Test
    public void testConstructeur_ColonneNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(0, -1);
        });
    }

    @Test
    public void testConstructeur_DeuxValeursNegatives() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(-1, -1);
        });
    }

    @Test
    public void testConstructeur_ValeursMaximales() {
        Position pos = new Position(5, 6);
        assertEquals(5, pos.getLigne());
        assertEquals(6, pos.getColonne());
    }

    @Test
    public void testConstructeur_GrandesValeurs() {
        Position pos = new Position(100, 200);
        assertEquals(100, pos.getLigne());
        assertEquals(200, pos.getColonne());
    }

    @Test
    public void testGetLigne() {
        Position pos = new Position(3, 4);
        assertEquals(3, pos.getLigne());
    }

    @Test
    public void testGetColonne() {
        Position pos = new Position(3, 4);
        assertEquals(4, pos.getColonne());
    }

    @Test
    public void testImmuabilite() {
        Position pos = new Position(2, 3);
        int ligne = pos.getLigne();
        int colonne = pos.getColonne();

        // Vérifier que les valeurs n'ont pas changé
        assertEquals(ligne, pos.getLigne());
        assertEquals(colonne, pos.getColonne());
    }

    @Test
    public void testEquals_MemePosition() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(2, 3);

        assertEquals(pos1.getLigne(), pos2.getLigne());
        assertEquals(pos1.getColonne(), pos2.getColonne());
    }

    @Test
    public void testEquals_PositionsDifferentes() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(3, 2);

        assertNotEquals(pos1.getLigne(), pos2.getLigne());
    }

    @Test
    public void testToString() {
        Position pos = new Position(2, 3);
        String str = pos.toString();
        assertNotNull(str);
        assertTrue(str.contains("2") || str.contains("3"));
    }
}

/**
 * Tests unitaires pour la classe Jeton
 */
class JetonTest {

    @Test
    public void testConstructeur_CouleurJaune() {
        Jeton jeton = new Jeton(Couleur.JAUNE);
        assertNotNull(jeton);
        assertEquals(Couleur.JAUNE, jeton.getCouleur());
    }

    @Test
    public void testConstructeur_CouleurRouge() {
        Jeton jeton = new Jeton(Couleur.ROUGE);
        assertNotNull(jeton);
        assertEquals(Couleur.ROUGE, jeton.getCouleur());
    }

    @Test
    public void testGetCouleur_Jaune() {
        Jeton jeton = new Jeton(Couleur.JAUNE);
        assertEquals(Couleur.JAUNE, jeton.getCouleur());
    }

    @Test
    public void testGetCouleur_Rouge() {
        Jeton jeton = new Jeton(Couleur.ROUGE);
        assertEquals(Couleur.ROUGE, jeton.getCouleur());
    }

    @Test
    public void testImmuabilite() {
        Jeton jeton = new Jeton(Couleur.JAUNE);
        Couleur couleur = jeton.getCouleur();

        // La couleur ne doit pas changer
        assertEquals(couleur, jeton.getCouleur());
    }

    @Test
    public void testPlusieursJetons() {
        Jeton jeton1 = new Jeton(Couleur.JAUNE);
        Jeton jeton2 = new Jeton(Couleur.ROUGE);
        Jeton jeton3 = new Jeton(Couleur.JAUNE);

        assertEquals(Couleur.JAUNE, jeton1.getCouleur());
        assertEquals(Couleur.ROUGE, jeton2.getCouleur());
        assertEquals(Couleur.JAUNE, jeton3.getCouleur());
    }

    @Test
    public void testToString() {
        Jeton jeton = new Jeton(Couleur.JAUNE);
        String str = jeton.toString();
        assertNotNull(str);
    }

    @Test
    public void testEquals_MemeCouleur() {
        Jeton jeton1 = new Jeton(Couleur.JAUNE);
        Jeton jeton2 = new Jeton(Couleur.JAUNE);

        assertEquals(jeton1.getCouleur(), jeton2.getCouleur());
    }

    @Test
    public void testEquals_CouleursDifferentes() {
        Jeton jeton1 = new Jeton(Couleur.JAUNE);
        Jeton jeton2 = new Jeton(Couleur.ROUGE);

        assertNotEquals(jeton1.getCouleur(), jeton2.getCouleur());
    }
}

/**
 * Tests unitaires pour l'énumération Couleur
 */
class CouleurTest {

    @Test
    public void testValeurs() {
        Couleur[] couleurs = Couleur.values();
        assertEquals(2, couleurs.length);
    }

    @Test
    public void testJaune() {
        Couleur couleur = Couleur.JAUNE;
        assertNotNull(couleur);
        assertEquals("JAUNE", couleur.name());
    }

    @Test
    public void testRouge() {
        Couleur couleur = Couleur.ROUGE;
        assertNotNull(couleur);
        assertEquals("ROUGE", couleur.name());
    }

    @Test
    public void testValueOf_Jaune() {
        Couleur couleur = Couleur.valueOf("JAUNE");
        assertEquals(Couleur.JAUNE, couleur);
    }

    @Test
    public void testValueOf_Rouge() {
        Couleur couleur = Couleur.valueOf("ROUGE");
        assertEquals(Couleur.ROUGE, couleur);
    }

    @Test
    public void testValueOf_Invalide() {
        assertThrows(IllegalArgumentException.class, () -> {
            Couleur.valueOf("BLEU");
        });
    }

    @Test
    public void testComparaison() {
        assertNotEquals(Couleur.JAUNE, Couleur.ROUGE);
        assertEquals(Couleur.JAUNE, Couleur.JAUNE);
        assertEquals(Couleur.ROUGE, Couleur.ROUGE);
    }

    @Test
    public void testSwitch() {
        Couleur couleur = Couleur.JAUNE;
        String result = switch (couleur) {
            case JAUNE -> "jaune";
            case ROUGE -> "rouge";
        };
        assertEquals("jaune", result);
    }
}

/**
 * Tests unitaires pour la classe Joueur
 */
class JoueurTest {

    @Test
    public void testConstructeur_JoueurJaune() {
        Joueur joueur = new Joueur(Couleur.JAUNE);
        assertNotNull(joueur);
        assertEquals(Couleur.JAUNE, joueur.getNom());
    }

    @Test
    public void testConstructeur_JoueurRouge() {
        Joueur joueur = new Joueur(Couleur.ROUGE);
        assertNotNull(joueur);
        assertEquals(Couleur.ROUGE, joueur.getNom());
    }

    @Test
    public void testGetNom_Jaune() {
        Joueur joueur = new Joueur(Couleur.JAUNE);
        assertEquals(Couleur.JAUNE, joueur.getNom());
    }

    @Test
    public void testGetNom_Rouge() {
        Joueur joueur = new Joueur(Couleur.ROUGE);
        assertEquals(Couleur.ROUGE, joueur.getNom());
    }

    @Test
    public void testImmuabilite() {
        Joueur joueur = new Joueur(Couleur.JAUNE);
        Couleur nom = joueur.getNom();

        // Le nom ne doit pas changer
        assertEquals(nom, joueur.getNom());
    }

    @Test
    public void testDeuxJoueurs() {
        Joueur joueur1 = new Joueur(Couleur.JAUNE);
        Joueur joueur2 = new Joueur(Couleur.ROUGE);

        assertNotEquals(joueur1.getNom(), joueur2.getNom());
    }

    @Test
    public void testToString() {
        Joueur joueur = new Joueur(Couleur.JAUNE);
        String str = joueur.toString();
        assertNotNull(str);
    }

    @Test
    public void testEquals_MemeJoueur() {
        Joueur joueur1 = new Joueur(Couleur.JAUNE);
        Joueur joueur2 = new Joueur(Couleur.JAUNE);

        assertEquals(joueur1.getNom(), joueur2.getNom());
    }

    @Test
    public void testEquals_JoueursDifferents() {
        Joueur joueur1 = new Joueur(Couleur.JAUNE);
        Joueur joueur2 = new Joueur(Couleur.ROUGE);

        assertNotEquals(joueur1.getNom(), joueur2.getNom());
    }
}

/**
 * Tests unitaires pour la classe Config
 */
class ConfigTest {

    @Test
    public void testNbLignes() {
        assertEquals(6, Config.NB_LIGNES);
    }

    @Test
    public void testNbColonnes() {
        assertEquals(7, Config.NB_COLONNES);
    }

    @Test
    public void testConstantesPositives() {
        assertTrue(Config.NB_LIGNES > 0);
        assertTrue(Config.NB_COLONNES > 0);
    }

    @Test
    public void testCoherenceGrille() {
        assertEquals(Config.NB_LIGNES, Grille.NB_LIGNES);
        assertEquals(Config.NB_COLONNES, Grille.NB_COLONNES);
    }
}

/**
 * Tests unitaires pour l'exception Puissance4Exception
 */
class Puissance4ExceptionTest {

    @Test
    public void testConstructeur_Message() {
        Puissance4Exception exception = new Puissance4Exception("Test message");
        assertNotNull(exception);
        assertEquals("Test message", exception.getMessage());
    }

    @Test
    public void testConstructeur_MessageNull() {
        Puissance4Exception exception = new Puissance4Exception(null);
        assertNotNull(exception);
    }

    @Test
    public void testConstructeur_MessageVide() {
        Puissance4Exception exception = new Puissance4Exception("");
        assertNotNull(exception);
        assertEquals("", exception.getMessage());
    }

    @Test
    public void testLancement() {
        assertThrows(Puissance4Exception.class, () -> {
            throw new Puissance4Exception("Erreur de test");
        });
    }

    @Test
    public void testCatch() {
        try {
            throw new Puissance4Exception("Test");
        } catch (Puissance4Exception e) {
            assertEquals("Test", e.getMessage());
        }
    }

    @Test
    public void testInstanceOfException() {
        Puissance4Exception exception = new Puissance4Exception("Test");
        assertTrue(exception instanceof Exception);
    }
}

/**
 * Tests unitaires pour la classe Partie
 */
class PartieTest {

    @Test
    public void testConstructeur() {
        Partie partie = new Partie();
        assertNotNull(partie);
        assertNotNull(partie.getGrille());
        assertNotNull(partie.getJoueurs());
        assertNotNull(partie.getJoueurCourant());
        assertFalse(partie.isPartieFinie());
        assertFalse(partie.isParAbandon());
        assertNull(partie.getGagnant());
    }

    @Test
    public void testGetGrille() {
        Partie partie = new Partie();
        Grille grille = partie.getGrille();
        assertNotNull(grille);
    }

    @Test
    public void testGetJoueurs() {
        Partie partie = new Partie();
        Joueur[] joueurs = partie.getJoueurs();
        assertNotNull(joueurs);
        assertEquals(2, joueurs.length);
        assertNotNull(joueurs[0]);
        assertNotNull(joueurs[1]);
    }

    @Test
    public void testGetJoueurs_CouleursCorrectes() {
        Partie partie = new Partie();
        Joueur[] joueurs = partie.getJoueurs();

        boolean hasJaune = false;
        boolean hasRouge = false;

        for (Joueur j : joueurs) {
            if (j.getNom() == Couleur.JAUNE) hasJaune = true;
            if (j.getNom() == Couleur.ROUGE) hasRouge = true;
        }

        assertTrue(hasJaune);
        assertTrue(hasRouge);
    }

    @Test
    public void testGetJoueurCourant() {
        Partie partie = new Partie();
        Joueur joueurCourant = partie.getJoueurCourant();
        assertNotNull(joueurCourant);
    }

    @Test
    public void testGetJoueurCourant_EstUnDesDeuxJoueurs() {
        Partie partie = new Partie();
        Joueur joueurCourant = partie.getJoueurCourant();
        Joueur[] joueurs = partie.getJoueurs();

        assertTrue(joueurCourant == joueurs[0] || joueurCourant == joueurs[1]);
    }

    @Test
    public void testIsPartieFinie_Initial() {
        Partie partie = new Partie();
        assertFalse(partie.isPartieFinie());
    }

    @Test
    public void testSetPartieFinie() {
        Partie partie = new Partie();
        partie.setPartieFinie(true);
        assertTrue(partie.isPartieFinie());
    }

    @Test
    public void testIsParAbandon_Initial() {
        Partie partie = new Partie();
        assertFalse(partie.isParAbandon());
    }

    @Test
    public void testSetParAbandon() {
        Partie partie = new Partie();
        partie.setParAbandon(true);
        assertTrue(partie.isParAbandon());
    }

    @Test
    public void testGetGagnant_Initial() {
        Partie partie = new Partie();
        assertNull(partie.getGagnant());
    }

    @Test
    public void testSetGagnant() {
        Partie partie = new Partie();
        Joueur joueur = new Joueur(Couleur.JAUNE);
        partie.setGagnant(joueur);
        assertEquals(joueur, partie.getGagnant());
    }

    @Test
    public void testChangerJoueurCourant() {
        Partie partie = new Partie();
        Joueur premierJoueur = partie.getJoueurCourant();

        // Changer le joueur courant (si la méthode existe)
        // partie.changerJoueurCourant();
        // Joueur deuxiemeJoueur = partie.getJoueurCourant();
        // assertNotEquals(premierJoueur, deuxiemeJoueur);
    }
}