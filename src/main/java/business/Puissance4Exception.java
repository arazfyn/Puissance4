package business;
/*
Cette excepction sera appelé seulement quand on essaye d'inserer un jeton dans une
colonne pleine.
 */

public class Puissance4Exception extends Exception{

    // Identifiant pour sauvegarder les erreurs liées au jeu.
    private static final long  serialVersionUID = 1L;

    public Puissance4Exception(String message) {
        super(message);
    }

}
