package test;

import main.java.CARD;
import main.java.Hand;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by ramya on 10/11/2017.
 */
public class HandTest {

    List<CARD> hand = new ArrayList<CARD>();

    private void initialize(){
        hand.add(CARD.C5);
        hand.add(CARD.SA);
        hand.add(CARD.D2);
    }

    @Test
    public void testAdd(){
        initialize();
        CARD individualCard = CARD.C4;
        int lengthBeforeAdd = hand.size();
        Hand handObj = new Hand();
        handObj.setHand(hand);
        handObj.addCard(individualCard);
        assertTrue(handObj.getHand().size()>lengthBeforeAdd);
    }

    @Test
    public void testEmpty(){
        initialize();
        Hand handObj = new Hand();
        handObj.setHand(hand);
        assertTrue(!handObj.isEmpty());
    }

    @Test
    public void testGetScore(){
        initialize();
        Hand handObj = new Hand();
        handObj.setHand(hand);
        assertTrue(handObj.getScore()>0);
    }

}