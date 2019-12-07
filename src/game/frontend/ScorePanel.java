package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {
	private Label auxLabel;
	private Label scoreLabel;
	private Label moveLabel;
	public ScorePanel() {
		setStyle("-fx-background-color: #5490ff");
		auxLabel = new Label("Especiales : -");
		auxLabel.setAlignment(Pos.CENTER_LEFT);
		auxLabel.setStyle("-fx-font-size: 24");
		setStyle("-fx-background-color: #5490ff");
		setLeft(auxLabel);
		scoreLabel = new Label("Score : 0");
		scoreLabel.setAlignment(Pos.CENTER);
		scoreLabel.setStyle("-fx-font-size: 24");
		setStyle("-fx-background-color: #5490ff");
		setCenter(scoreLabel);
		moveLabel = new Label("Moves : -");
		moveLabel.setAlignment(Pos.CENTER_RIGHT);
		moveLabel.setStyle("-fx-font-size: 24");
		setRight(moveLabel);
	}

	public void updateScore( String textCentre) {
		scoreLabel.setText(textCentre);
	}
	public void updateAux(String textLeft){
		auxLabel.setText(textLeft);
	}
	public void updateMove(String textRight){
		moveLabel.setText(textRight);
	}

}