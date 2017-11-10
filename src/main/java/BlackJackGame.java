package main.java;

/**
 * Created by ramya on 10/11/2017.
 */
public class BlackJackGame {

    Player dealer = null;
    Player sam = null;
    Deck deck =  null;
    Player winner = null;
    public static final int BlackJackScore = 21;

    //Check for null
    public BlackJackGame(Player sam,Player dealer,Deck deck) {
        this.sam = sam;
        this.dealer = dealer;
        this.deck = deck;
    }

    private void playFirstRound() {

        sam.drawACard(deck);
        dealer.drawACard(deck);
        sam.drawACard(deck);
        dealer.drawACard(deck);

        int samScore = sam.getHandScore();
        int dealersScore = dealer.getHandScore();

        if(samScore == BlackJackScore) {
            winner = sam;
        } else if (dealersScore == BlackJackScore) {
            winner = dealer;
        }

        //Maybe there's something I don't get in the game
        //what is the difference between 22 and > 21?
        if(samScore > BlackJackScore) {
            winner = dealer;
        } else if (dealersScore > BlackJackScore) {
            winner = sam;
        }
    }

    private void playSecondRound() {
        if (sam.hasEmptyHand() || dealer.hasEmptyHand()) {
            throw new IllegalStateException("You need to invoke round1 first");
        }

        if(getWinner() != null) {
            throw new IllegalStateException("The game is already won");
        }

        //I'm no BlackJack pro, but I think you should have a constant for 17,
        //explaining what's its significancy
        while(sam.getHandScore() < 17) {
            sam.drawACard(deck);
        }

        int samScore = sam.getHandScore();

        if (samScore > BlackJackScore) {
            winner = dealer;
            return;
        }

        while (samScore > dealer.getHandScore()) {
            dealer.drawACard(deck);
        }

        int dealerScore = dealer.getHandScore();

        if (dealerScore > BlackJackScore) {
            winner = sam;
        } else if (dealerScore > samScore) {
            winner = dealer;
        } else {
            winner = sam;
        }
    }


    public Player play(Deck deck){
        this.deck = deck;
        playFirstRound();

        if(getWinner() != null) {
            return getWinner();
        }

        playSecondRound();
        return getWinner();
    }

    public Player getWinner() {
        return winner;
    }

    public Deck getDeck() {
        return deck;
    }
}



