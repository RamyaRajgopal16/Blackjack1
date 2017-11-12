package main.java;

/**
 * The game in itself.
 *
 * Created by ramya on 10/11/2017.
 */
public class BlackJackGame {

    public static final int CAN_DRAW = 17;
    Player dealer = null;
    Player sam = null;
    Deck deck =  null;
    Player winner = null;
    public static final int BlackJackScore = 21;


    public BlackJackGame(Player sam,Player dealer,Deck deck) {
        this.sam = sam;
        this.dealer = dealer;
        this.deck = deck;
    }

    /**
     * The first round. We check if any one has blackjackscore.
     * Or if equal is it more than blackjackScore.
     * And set the winner if so
     */
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


        if(samScore > BlackJackScore) {
            winner = dealer;
        } else if (dealersScore > BlackJackScore) {
            winner = sam;
        }
    }

    /**
     * The second round  continues if no winner in first round
     */
    private void playSecondRound() {
        if (sam.hasEmptyHand() || dealer.hasEmptyHand()) {
            throw new IllegalStateException("You need to invoke round1 first");
        }

        if(getWinner() != null) {
            throw new IllegalStateException("The game is already won");
        }


        while(sam.getHandScore() < CAN_DRAW) {
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

    /**
     * Start the game
     * @param deck
     * @return winner
     */
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



