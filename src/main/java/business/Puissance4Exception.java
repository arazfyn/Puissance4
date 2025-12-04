package business;
/*
Cette excepction sera appelé seulement quand on essaye d'inserer un jeton dans une
colonne pleine.
 */

public class Puissance4Exception extends RuntimeException{

    public Puissance4Exception(String message) {
        super(message);
    }
}
