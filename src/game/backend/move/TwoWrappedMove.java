package game.backend.move;

import game.backend.Grid;
import game.backend.element.Candy;

public class TwoWrappedMove extends Move {
	
	public TwoWrappedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		int currI, currJ;
		if (i1 == i2) {
			if (j1 < j2) {
				currI = i1;
				currJ = j1;
			} else {
				currI = i2;
				currJ = j2;
			}
			if ( grid.get(currI, currJ-1).canExplode()){
				clearContent(currI, currJ-1);
			}
			if ( grid.get(currI , currJ +2 ).canExplode()) {
				clearContent(currI, currJ + 2);
			}
			for(int n = -1; n < 3; n++) {
				{
					if (grid.get(currI - 1, currJ + n).canExplode()) {
						clearContent(currI - 1, currJ + n);
					}
				}
				if (grid.get(currI + 1, currJ + n).canExplode()) {
					clearContent(currI + 1, currJ + n);
				}
			}
		} else {
			if (i1 < i2) {
				currI = i1;
				currJ = j1;
			} else {
				currI = i2;
				currJ = j2;
			}
			if ( grid.get( currI , currJ -1 ).canExplode()) {
				clearContent(currI, currJ - 1);
			}
			if ( grid.get(currI , currJ+2).canExplode()) {
				clearContent(currI, currJ + 2);
			}
			for(int n = -1; n < 3; n++) {
				if ( grid.get(currI -1 , currJ + n).canExplode() ) {
					clearContent(currI - 1, currJ + n);
				}
				if ( grid.get( currI - 1, currJ + n).canExplode() ) {
					clearContent(currI + 1, currJ + n);
				}
			}
		}
	}

}
