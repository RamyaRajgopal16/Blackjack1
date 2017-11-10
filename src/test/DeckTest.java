package test;


import main.java.Deck;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ramya on 10/11/2017.
 */
public class DeckTest {

    @Test
    public void autoInitializeDeck (){
        Deck.Builder deckBuilder = Deck.Builder.createNew();
        Deck.Builder deck = deckBuilder.createNew();
        Deck finalDeck = deck.validate().build();
        assertTrue(finalDeck instanceof Deck);
        assertEquals(finalDeck.getDeck().size(), 52);
    }

    @Test
    public void initializeDeckFromFile() throws IOException {
        String filePath = "/Users/ramya/Downloads/Blackjack/src/resources/sampleInput.txt";
        Deck.Builder deckBuilder = Deck.Builder.createNew();
        Deck.Builder deck = deckBuilder.fromFilePath(filePath);
        Deck finalDeck = deck.validate().build();
        assertTrue(finalDeck instanceof Deck);
        assertEquals(finalDeck.getDeck().size(), 52);
    }

    @Test
    public void testDraw(){
        Deck.Builder deckBuilder = Deck.Builder.createNew();
        Deck.Builder deck = deckBuilder.createNew();
        Deck finalDeck = deck.validate().build();
        finalDeck.draw();
        assertTrue(finalDeck.getDeck().size() < 52);
    }
}
