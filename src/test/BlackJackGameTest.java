package test;

import main.java.BlackJackGame;
import main.java.Deck;
import main.java.Player;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ramya on 10/11/2017.
 */
public class BlackJackGameTest {


    private BlackJackGame initialize(){
        Player sam = new Player("sam");

        Player dealer = new Player("Dealer");

        Deck.Builder deckBuilder = Deck.Builder.createNew();
        Deck.Builder deck = deckBuilder.createNew();
        Deck finalDeck = deck.validate().build();
        return new BlackJackGame(sam,dealer,finalDeck);

    }
    @Test
    public void play() throws Exception {
        BlackJackGame gameOn = initialize();
        Player winner = gameOn.play(gameOn.getDeck());
        assertNotNull(winner.getPlayerName());
    }

    @Test
    public void getWinner() throws Exception {
        BlackJackGame gameOn = initialize();
        Player winner = gameOn.play(gameOn.getDeck());
        assertNotNull(winner.getPlayerName());
    }

}