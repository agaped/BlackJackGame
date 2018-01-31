package sample;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by agaped on 18.01.2018.
 */
public class Card extends Parent{

    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10), ACE(11);

        final int value;
        Rank(int value) {
            this.value = value;
        }

        String displayName() {
            return ordinal() < 9 ? String.valueOf(value) : name().substring(0, 1);
        }
    }

    public enum Suit {
        HEART, SPADE, DIAMOND, CLUB
    }

    private Suit suit;
    private Rank rank;
    public final int value;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
        this.value = rank.value;

        Rectangle r= new Rectangle(80,100);
        r.setArcWidth(30);
        r.setArcHeight(30);
        r.setFill(Color.rgb(247,159,87));

        Text cardNumber=new Text(rank.displayName());
        cardNumber.setX(80-cardNumber.getLayoutBounds().getWidth()-10);
        cardNumber.setY(cardNumber.getLayoutBounds().getHeight());

        Text desc=new Text(this.rank.toString() + " of " + this.suit.toString());
        desc.setX(15);
        desc.setY(40);
        desc.setWrappingWidth(60);

        getChildren().addAll(r,cardNumber,desc);
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return this.rank.toString() + " of " + this.suit.toString();
    }
}
