package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;

/**
 * Created by agaped on 18.01.2018.
 */

public class BlackJack extends Application {

    private Deck deck=new Deck();
    public HBox dealerCards = new HBox(20);
    public HBox playerCards = new HBox(20);
    private Player player, dealer;
    Text dealerText=new Text();
    Text playerText=new Text();
    Text result=new Text("Welcome to BlackJack !");
    Button hit=new Button("HIT");
    Button stand=new Button("STAND");
    Button play=new Button("PLAY");

    private Parent createContent(){
          player=new Player(playerCards.getChildren());
          dealer=new Player(dealerCards.getChildren());
          hit.setDisable(true);
          stand.setDisable(true);

          hit.getStyleClass().add("hit");
          stand.getStyleClass().add("stand");
          play.getStyleClass().add("play");
          hit.setPrefWidth(70);
          play.setPrefWidth(70);
          stand.setPrefWidth(70);

          Pane root =new Pane();
          root.setPrefSize(600,600);

        HBox layout=new HBox();

        Rectangle left=new Rectangle(500, 450);
        left.setFill(Color.rgb(247,177,87));
        VBox buttVBox= new VBox(20,hit,stand, play);
        buttVBox.setAlignment(Pos.CENTER);

        Rectangle right=new Rectangle(100, 450);
        right.setFill(Color.rgb(247,206,87));
        VBox playVBox=new VBox(40, dealerText, dealerCards, result, playerCards, playerText);
        playVBox.setAlignment(Pos.TOP_CENTER);

        layout.getChildren().addAll(new StackPane(left,playVBox), new StackPane(right, buttVBox));
        root.getChildren().addAll(layout);

        play.setOnAction(event -> {
            startNewGame();
        });
        hit.setOnAction(event -> {
            pressHit();
        });
        stand.setOnAction(event -> {
            pressStand();
        });


        return root;
    }

    public void startNewGame() {

        result.setText("");
        deck.fill();
        player.reset();
        dealer.reset();
        hit.setDisable(false);
        stand.setDisable(false);

        dealer.takeCard(deck.drawCard());
        dealerText.setText("Dealer scores: "+ dealer.getTotalSum());

        player.takeCard(deck.drawCard());
        player.takeCard(deck.drawCard());
        playerText.setText("Player scores: "+ player.getTotalSum());

        if(player.getTotalSum()>21){
            result.setText("Dealer won !!!");
            hit.setDisable(true);
            stand.setDisable(true);
        }
    }
    public void pressHit(){
        player.takeCard(deck.drawCard());
        playerText.setText("Player scores: "+ player.getTotalSum());

        if(player.getTotalSum()>21){
            result.setText("Dealer won !!!");
            hit.setDisable(true);
            stand.setDisable(true);
        }
    }

    public void pressStand() {
        while (dealer.getTotalSum() < 17) {
            dealer.takeCard(deck.drawCard());
            dealerText.setText("Dealer scores: " + dealer.getTotalSum());
        }
        endGame();
    }
    public void endGame () {

        int dealerValue = dealer.getTotalSum();
        int playerValue = player.getTotalSum();

        if (dealerValue == 21 || playerValue > 21 || dealerValue == playerValue
                || (dealerValue < 21 && dealerValue > playerValue)) {
            result.setText("Dealer won !!!");
        } else if (playerValue == 21 || dealerValue > 21 || playerValue > dealerValue) {
            result.setText("Player won !!!");
        }
        hit.setDisable(true);
        stand.setDisable(true);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Black Jack");
        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        File f = new File("src/javafx.css");
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        primaryStage.setWidth(600);
        primaryStage.setHeight(450);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
