package game.backend.element;

import game.backend.move.Direction;

public class HorizontalStripedCandy extends SpecialCandy {
	
	public HorizontalStripedCandy() {
		Direction[] explosion = new Direction[2];
		explosion[0] = Direction.LEFT;
		explosion[1] = Direction.RIGHT;
	}
	
	@Override
	public String getKey() {
		return "HORIZ-STRIPED-" + super.getKey();
	}
	
	@Override
	public String getFullKey() {
		return "HORIZ-STRIPED-" + super.getFullKey();
	}

	@Override
	public long getScore() {
		return 80;
	}

}
