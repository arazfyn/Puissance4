package business;

public class Puissance4 {

    private Partie puissance4;


    public void Puissance4(){
        this.puissance4 = new Partie();
    }


    public void Puissance4(Partie puissance4) {
        this.puissance4 = puissance4;
    }

    public boolean gameIsOver() {
        return this.puissance4.isPartieFinie();
    }

    public void jouer(int numColonne) {

        if (!this.puissance4.isPartieFinie()) {

            Joueur joueurCourant = this.puissance4.getJoueurCourant();
            Jeton jeton = new Jeton(joueurCourant.getNom());
        }

    }
    public void abandonner() {

        this.puissance4.isParAbandon(true);

        Joueur joueurCourant = this.puissance4.getJoueurCourant();
        Joueur[] joueurs = this.puissance4.getJoueurs();

        if (joueurCourant == joueurs[0]) {

        }

    }
}
