package game.frontend;

import game.backend.CandyGame;
import game.backend.level.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("Menu de inicio");
		Button level1 = new Button("Level 1");
		Button level2 = new Button("Level 2");
		Button level3 = new Button("Level 3");
		Button level4 = new Button("Level 4");
		Button level5 = new Button("Level 5");

		Label label1 = new Label("Menu de inicio. elija el nivel:");

		VBox layout = new VBox(20);
		VBox layout2 = new VBox();
		layout.getChildren().addAll(label1, level1, level2, level3, level4, level5);

		Scene scene = new Scene(layout, 300, 250);
		Scene scene2 = new Scene(layout2);

		BackgroundImage bI = new BackgroundImage(new Image("images/valchar.jpg"),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(300, 250,false, false, false, false));
		Background back = new Background(bI);
		layout.setBackground(back);

		Stage secStage = new Stage();
		level1.setOnAction(e -> {secStage.setScene(scene2);
			secStage.setTitle("Level 1");
			startSpecial(secStage, Level1.class);
		});

		level2.setOnAction(e -> {secStage.setScene(scene2);
			secStage.setTitle("Level 2");
			startSpecial(secStage, Level2.class);
		});

        level3.setOnAction(e -> {secStage.setScene(scene2);
            startSpecial(secStage, Level3.class);
        });

		level4.setOnAction(e -> {secStage.setScene(scene2);
			secStage.setTitle("Level 4");
			startSpecial(secStage, Level4.class);
		});
		level5.setOnAction(e -> {secStage.setScene(scene2);
			secStage.setTitle("Level 5");
			startSpecial(secStage, Level5.class);
		});


		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void startSpecial(Stage primaryStage, Class clase) {
		CandyGame game = new CandyGame(clase);
		CandyFrame frame = new CandyFrame(game);
		Scene scene = new Scene(frame);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
