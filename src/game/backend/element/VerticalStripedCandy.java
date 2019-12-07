package game.backend.element;

import game.backend.move.Direction;

public class VerticalStripedCandy extends SpecialCandy {

	public VerticalStripedCandy() {
		Direction[] explosion = new Direction[2];
		explosion[0] = Direction.DOWN;
		explosion[1] = Direction.UP;
	}
	
	@Override
	public String getKey() {
		return "VERT-STRIPED-" + super.getKey();
	}
	
	@Override
	public String getFullKey() {
		return "VERT-STRIPED-" + super.getFullKey();
	}

	@Override
	public long getScore() {
		return 80;
	}

}
