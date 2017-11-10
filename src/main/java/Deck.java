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


    public CARD draw() {
        return deck.pop();
    }



    public static class Builder {

        Stack<CARD> deck=null;

        public static Builder createNew(){
            return new Deck.Builder();
        }

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

        public Builder fromStack(Stack stack) {
            this.deck = stack;
            return this;
        }

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

        public  Deck build() {
            return deck==null? new Deck():new Deck(deck);
        }


    }

    public String toString() {
        String toString = deck.stream().map(t->{return t.toString()+", ";}).reduce("", (t,q)->{return t+q;});
        if (!toString.isEmpty()) {
            toString = toString.substring(0,toString.length()-2);
        }

        return toString;
    }

}
