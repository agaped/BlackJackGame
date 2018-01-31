package sample;
import javafx.collections.ObservableList;
import sample.Card.Rank;
import javafx.scene.Node;


/**
 * Created by agaped on 18.01.2018.
 */
public class Player {

    private ObservableList<Node> cards;
    private int aces=0;
    private int totalSum=0;

    public Player(ObservableList<Node> cards) {
        this.cards = cards;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void takeCard(Card card) {
        cards.add(card);

        if (card.getRank() == Rank.ACE) {
            aces++;
        }

        if (totalSum + card.value > 21 && aces > 0) {
            totalSum=totalSum + card.value - 10;
            aces--;
        }
        else {
            totalSum=totalSum + card.value;
        }
    }
 /*   public String toString(){
        int i=this.cards.size()-1;
        String cardListOutput = "";
        *//*int i = 0;
        for(Card aCard : this.cards){
            cardListOutput += "\n" + aCard.toString();
            i++;
        }*//*
        Card card=this.cards.get(i);
        cardListOutput=card.toString();
        return cardListOutput;
    }*/

    public void reset() {
        cards.clear();
        totalSum=0;
        aces = 0;
    }
}
