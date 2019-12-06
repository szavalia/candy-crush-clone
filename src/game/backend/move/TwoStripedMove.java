package game.backend.move;

import game.backend.Grid;

public class TwoStripedMove extends Move {

	public TwoStripedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		for(int i = 0; i < Grid.SIZE; i++) {
			if ( grid.get(i,j2).canExplode()){
				clearContent(i, j2);
			}
		}
		for(int j = 0; j < Grid.SIZE; j++) {
			if ( grid.get(i2,j).canExplode()){
				clearContent(i2, j);
			}
		}
	}

}
