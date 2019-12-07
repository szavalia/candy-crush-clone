package game.frontend;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class BoardPanel extends TilePane {

	private StackPane[][] cells;

	public BoardPanel(final int rows, final int columns, final int cellSize) {
		setPrefRows(rows);
		setPrefColumns(columns);
		setPrefTileHeight(cellSize);
		setPrefTileWidth(cellSize);
		this.cells = new StackPane[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new StackPane();
				getChildren().add(cells[i][j]);
			}
		}
	}

	public void setImage(int row, int column, Image image) {
		cells[row][column].getChildren().add(new ImageView(image));
		cells[row][column].setEffect(null);
	}

	public void setWallImage(int row, int column, Image image) {
		setImage( row , column , image );
		Light.Distant spotLight = new Light.Distant();
		spotLight.setColor(Color.SANDYBROWN);
		spotLight.setElevation(100);
		Lighting lighting = new Lighting(spotLight);
		cells[row][column].setEffect(lighting);
	}

	public void setJailImage( int row , int column , Image image , Image Jail){
		setImage(row , column , image );
		cells[row][column].getChildren().add(new ImageView(Jail));

	}

	public void setGoldenImage( int row, int column, Image image){
		setImage(row, column , image);
		Light.Distant spotLight = new Light.Distant();
		spotLight.setColor(Color.YELLOW);
		spotLight.setElevation(100);
		Lighting lighting = new Lighting(spotLight);
		cells[row][column].setEffect(lighting);
	}

	public void setEffect(int row, int column, Image image, Color color){
		setImage(row, column , image);
		Light.Distant spotLight = new Light.Distant();
		spotLight.setColor(color);
		spotLight.setElevation(100);
		Lighting lighting = new Lighting(spotLight);
		cells[row][column].setEffect(lighting);
	}

}