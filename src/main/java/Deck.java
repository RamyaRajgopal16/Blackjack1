package main.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Deck of cards.
 *
 * Created by ramya on 10/11/2017.
 */
public class Deck {

    public Stack<CARD> getDeck() {
        return deck;
    }

    private Stack<CARD> deck = new Stack<>();



    private Deck(Stack<CARD> deck) {
        this.deck = deck;
    }

    private Deck(){
        shuffleDeck();
    }

    /**
     * Simulating shuffling of cards
     */
    private void shuffleDeck() {
        // we need it mutable and the list provided by Arrays is Immutable
        List<CARD> allCards = new ArrayList(Arrays.asList(CARD.values()));
        int max = 52;
        Random rand = new Random();
        while (allCards.size()>0) {
            CARD removedCard = allCards.remove(rand.nextInt(max)) ;
            this.deck.add(removedCard);
            --max;
        }
    }

    /**
     * Draw one card
     * @return CARD
     */
    public CARD draw() {
        return deck.pop();
    }


    /**
     * Static class for building a deck from filePath if argument provided
     *
     */
    public static class Builder {

        Stack<CARD> deck=null;

        public static Builder createNew(){
            return new Deck.Builder();
        }

        /**
         * deck created from the file path provided
         * @param filePath
         * @return
         * @throws IOException
         */
        public  Builder fromFilePath(String filePath) throws IOException {
            Scanner scanner=null;
            File file = new File(filePath);
            try {
                scanner = new Scanner(file);
                deck = new Stack<>();
                for (String card : scanner.nextLine().split(",")) {
                    deck.add(CARD.valueOf(card.trim()));
                }


            } finally {
                if (scanner!=null) {
                    scanner.close();
                }
            }


            return this;
        }

        /**
         * Validate the deck . For uniqueness, number of cards
         * @return Builder
         */
        public  Builder validate() {
            if (deck==null) {
                return this;
            }
            if (deck.size()>52) {
                throw new RuntimeException("Critical error, deck is not initialized correctly.You need 52 unique cards.");

            }
            HashSet<CARD> allUniqueCards = new HashSet<>(deck==null?new HashSet<>():deck);
            // verify uniqueness of cards in case of 52 card condition is met but some are duplicates
            if (deck==null || allUniqueCards.size()==52) {
                return this;
            }
            // this is error that is not recoverable therefore we define it as RuntimeException
            throw new RuntimeException("Critical error, deck is not initialized correctly.You need 52 unique cards.");

        }

        /**
         * If deck isnt intialized build a new one
         * @return Deck
         */
        public  Deck build() {
            return deck==null? new Deck():new Deck(deck);
        }


    }

    /**
     * Overload toSTring method to make display easier
     * @return
     */
    public String toString() {
        String toString = deck.stream().map(t->{return t.toString()+", ";}).reduce("", (t,q)->{return t+q;});
        if (!toString.isEmpty()) {
            toString = toString.substring(0,toString.length()-2);
        }

        return toString;
    }

}
