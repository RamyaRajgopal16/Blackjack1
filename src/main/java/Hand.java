package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramya on 10/11/2017.
 */
public class Hand {

    private List<CARD> hand = new ArrayList<>();

    public void addCard(CARD card) {
        hand.add(card);
    }

    public int getScore(){
        return hand.stream().mapToInt(t->{return t.getPoints();}).sum();
    }


    public boolean isEmpty() {
        return hand.size() == 0;
    }

    public String toString() {
        String toString = hand.stream().sequential().map(t->{return t.name();}).reduce("",
                (t,q)->{return t.isEmpty()?q: t+", " + q;});
        return toString;
    }

    public void setHand(List<CARD> hand) {
        this.hand = hand;
    }

    public List<CARD> getHand() {
        return hand;
    }
}