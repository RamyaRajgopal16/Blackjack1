package main.java;

import java.io.IOException;

/**
 * Created by ramya on 10/11/2017.
 */
public class BeginGame {

    public static void printWinner(Player winner,Player looser) {
        System.out.println("Winner is:" + winner.getPlayerName());
        System.out.println(winner.getPlayerName().equals("sam")?winner:looser);
        System.out.println(winner.getPlayerName().equals("sam")?looser:winner);
    }

    public static void main(String[] args) throws IOException {

        Deck.Builder deckBuilder = Deck.Builder.createNew();

        if(args.length == 0) {
            deckBuilder = deckBuilder.createNew();
        } else if (args.length == 1) {
            deckBuilder = deckBuilder.fromFilePath(args[1]);
        } else {
            throw new IllegalArgumentException("Expected at most one argument");
        }

        Deck deck = deckBuilder.validate().build();
        Player dealer = new Player("dealer");
        Player sam = new Player("sam");

        BlackJackGame play = new BlackJackGame(sam,dealer,deck);
        Player winner = play.play(deck);
        printWinner(winner, winner==sam?dealer:sam);
    }
}
