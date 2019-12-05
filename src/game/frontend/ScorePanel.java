package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private Label scoreLabel1;
    private Label scoreLabel2;
    private Label scoreLabel3;
//quiero tener 3 labels para ponerle texto, todas en linea
    public ScorePanel() {
		setStyle("-fx-background-color: #5490ff");
		scoreLabel1 = new Label("0");
		scoreLabel1.setAlignment(Pos.CENTER_LEFT);
		scoreLabel1.setStyle("-fx-font-size: 24");
        setStyle("-fx-background-color: #5490ff");
        scoreLabel2 = new Label("0");
        scoreLabel2.setAlignment(Pos.CENTER);
        scoreLabel2.setStyle("-fx-font-size: 24");
        setStyle("-fx-background-color: #5490ff");
        scoreLabel3 = new Label("0");
        scoreLabel3.setAlignment(Pos.CENTER_RIGHT);
        scoreLabel3.setStyle("-fx-font-size: 24");
	}
	
	public void updateScore(String textLeft, String textCentre, String textRight) {
        scoreLabel1.setText(textLeft);
        scoreLabel2.setText(textCentre);
        scoreLabel3.setText(textRight);
    }
}