package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;
import game.backend.element.Nothing;
import game.backend.element.*;
import game.backend.move.Direction;

public class Cell {
	private boolean golden;
	private Grid grid;
	private Cell[] around = new Cell[Direction.values().length];
	private Element content;
	private boolean wall;
	public Cell(Grid grid) {
		this.grid = grid;
		this.content = new Nothing();
		this.golden = false;
		this.wall = false;
	}

	public void setAround(Cell up, Cell down, Cell left, Cell right) {
		this.around[Direction.UP.ordinal()] = up;
		this.around[Direction.DOWN.ordinal()] = down;
		this.around[Direction.LEFT.ordinal()] = left;
		this.around[Direction.RIGHT.ordinal()] = right;
	}

	public boolean hasFloor() {
		return !around[Direction.DOWN.ordinal()].isEmpty();
	}
	
	public boolean isMovable(){
		return content.isMovable();
	}
	
	public boolean isEmpty() {
		return !content.isSolid();
	}

	public Element getContent() {
		return content;
	}
	
	public void clearContent() {
		if ((content.isMovable() || !content.canExplode())) {
			if(content instanceof SpecialCandy){
				wallOff();
			}
			Direction[] explosionCascade = content.explode();
			grid.cellExplosion(content);
			this.content = new Nothing();
			if (explosionCascade != null) {
				expandExplosion(explosionCascade);
			}
			this.content = new Nothing();
		}
	}
	
	private void expandExplosion(Direction[] explosion) {
		for(Direction d: explosion) {
			this.around[d.ordinal()].explode(d);
		}
	}
	
	private void explode(Direction d) {
		if (content.canExplode()){
			wallOff();
			clearContent();
		}
		if (this.around[d.ordinal()] != null)
			this.around[d.ordinal()].explode(d);
	}
	
	public Element getAndClearContent() {
		if (content.isMovable()) {
			Element ret = content;
			this.content = new Nothing();
			return ret;
		}
		return null;
	}

	public boolean fallUpperContent() {
		Cell up = around[Direction.UP.ordinal()];
		if (this.isEmpty() && !up.isEmpty() && up.isMovable()) {
			this.content = up.getAndClearContent();
			grid.wasUpdated();
			if (this.hasFloor()) {
				grid.tryRemove(this);
				return true;
			} else {
				Cell down = around[Direction.DOWN.ordinal()];
				return down.fallUpperContent();
			}
		}
		if( up.getContent() instanceof JailedCandy ) {
			grid.wasUpdated();
		}
		return false;
	}

	public void goldenOn(){
		golden = true;
	}

	public boolean golden() { return golden; }

	public void setContent(Element content) {
		this.content = content;
	}

	public void wallOn(){ wall = true; }

	public void wallOff(){ wall = false;}

	public boolean hasWall(){return wall; }
}
