package view;
import business.*;
import java.util.Scanner;

public class MainView {
    public static void display(Puissance4 jeu){

        Grille grille = jeu.getGrille();
        System.out.println();
        System.out.println("Grille(" + Grille.NB_LIGNES + "x" + Grille.NB_COLONNES + ") :");
        System.out.println(grille.toString());

        if (!jeu.gameIsOver()) {
            Joueur courant = jeu.getJoueurCourant();
            System.out.println("Tour du joueur : " + courant.getNom());
        } else {
            if (jeu.isParAbandon()) {
                System.out.println("Partie terminée par abandon.");
                System.out.println("Gagnant : " + jeu.getGagnant());
            } else if (jeu.getGagnant() != null) {
                System.out.println("Victoire de : " + jeu.getGagnant().getNom());
            } else {
                System.out.println("Partie nulle (grille pleine).");
            }
        }
    }

    static void main(String[] args) {
        Puissance4 jeu = new Puissance4();
        Scanner scanner = new Scanner(System.in);

        while (!jeu.gameIsOver()) {
            display(jeu);

            System.out.println("Entrez un numéro de colonne [0 à "+ (Config.NB_COLONNES - 1) +"] ou 'A' pour abandonner: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("A")) {
                jeu.abandonner();
                break;
            }
            try {
                int col = Integer.parseInt(input);
                jeu.jouer(col);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide : entrée 'A' ou un nombre entier.");
            } catch (IllegalArgumentException | Puissance4Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        System.out.println();
        display(jeu);
        System.out.println("Fin de la partie.");

    }
}

