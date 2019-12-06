package game.backend.move;

import game.backend.Grid;
import game.backend.element.Bomb;
import game.backend.element.BreakableWall;
import game.backend.element.Candy;

public class BombMove extends Move {
	
	public BombMove(Grid grid) {
		super(grid);
	}

	@Override
	public void removeElements() {
		Candy candy = (Candy) (get(i1, j1) instanceof Bomb ? get(i2, j2) : get(i1, j1));
		clearContent(i1, j1);
		clearContent(i2, j2);
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (candy.equals(get(i, j))) {
					clearContent(i, j);
				}
				else if ( get(i , j ).isBreakable() ){
					BreakableWall aux = (BreakableWall) get(i , j );
					if ( candy.equals(aux.drop())){
						clearContent(i,j);
					}
				}
			}
		}
	}

}
