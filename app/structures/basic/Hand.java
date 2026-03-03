package structures.basic;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();
    private int maxSize = 6;

    public Hand() {}

    public Hand(int maxSize){
        this.maxSize = maxSize;
    }

    // getters
    public List<Card> getCards() {
        return cards;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    // add card to hand
    public boolean add(Card card){
        if (card == null) return false;
        if (cards.size() >= maxSize) return false;
        cards.add(card);
        return true;
    }


    // play a card
    public Card remove(int index){
        if (index < 0 || index >= cards.size()) return null;
        return cards.remove(index);
    }

    // Returns the card at the given index without removing it from the hand.
    public Card get(int index){
        if (index < 0 || index >= cards.size()) return null;
        return cards.get(index);
    }

    public int size(){
        return cards.size();
    }



}
