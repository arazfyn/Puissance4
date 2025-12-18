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

        System.out.println("Voulez-vous charger une partie existante ? (O/N)");
        if (scanner.nextLine().trim().equalsIgnoreCase("O")) {
            Puissance4 charge = Puissance4.charger("sauvegarde.ser");
            if (charge != null) {
                jeu = charge;
                System.out.println("Partie rechargée !");
            }
        }

        while (!jeu.gameIsOver()) {
            display(jeu);

            // Mise à jour du message pour inclure l'option 'S' [cite: 322]
            System.out.println("Actions : [0-6] = Colonne, 'A' = Abandonner, 'S' = Sauvegarder");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("A")) {
                jeu.abandonner(); // [cite: 79, 308]
                break;
            } else if (input.equals("S")) {
                jeu.sauvegarder("sauvegarde.ser");
                System.out.println("Partie sauvegardée. Fermeture du jeu...");
                break;
            }

            try {
                int col = Integer.parseInt(input);
                jeu.jouer(col); // [cite: 78, 305]
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Tapez un chiffre, 'A' ou 'S'.");
            } catch (IllegalArgumentException | Puissance4Exception e) {
                System.out.println("Erreur : " + e.getMessage()); // [cite: 126, 143, 144]
            }
        }

        System.out.println();
        display(jeu);
        System.out.println("Fin de la partie.");
    }
}

