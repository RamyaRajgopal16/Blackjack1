package main.java;

/**
 * Player related information
 * Created by ramya on 10/11/2017.
 */
public class Player {

    private String playerName = null;
    private Hand hand = new Hand();


    public Player(String playerName) {
        this.playerName = playerName;
    }


    public void drawACard(Deck deck) {
        hand.addCard(deck.draw());
    }

    public int getHandScore() {
        return hand.getScore();
    }

    public String getPlayerName(){
        return playerName;
    }

    public String toString() {
        return "Cards in hand are : " +playerName + ":" + " " + hand.toString();
    }

    public boolean hasEmptyHand() {
        return hand.isEmpty();
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
