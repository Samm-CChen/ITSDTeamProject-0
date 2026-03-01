package structures.basic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {

    private final List<Card> cards = new ArrayList<>();

    // add one card to deck
    public void addCard(Card card){
        if (card == null) return;
        cards.add(card);
    }

    // add lots of card to deck
    public void addCards(List<Card> newCards){
        if (newCards == null) return;
        for (Card c : newCards) addCard(c);
    }

    // shuffle
    public void shuffle(){
        Collections.shuffle(cards);
    }

    // draw a card
    public Card draw(){
        if (cards.isEmpty()) return null;
        return cards.remove(cards.size() - 1);
    }

    public int size(){
        return cards.size();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

}
