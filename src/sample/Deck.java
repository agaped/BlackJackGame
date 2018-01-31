package sample;
import sample.Card.Rank;
import sample.Card.Suit;

/**
 * Created by agaped on 18.01.2018.
 */
public class Deck {

    private Card[] cards = new Card[52];


    public Deck() {
        fill();
    }

    public final void fill() {
        int i = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards[i++] = new Card(suit, rank);
            }
        }
    }

    public Card drawCard() {
        Card card = null;
        while (card == null) {
            int index = (int)(Math.random()*cards.length);
            card = cards[index];
            cards[index] = null;
        }
        return card;
    }



}
