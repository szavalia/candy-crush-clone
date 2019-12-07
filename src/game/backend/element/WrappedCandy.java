package game.backend.element;

import game.backend.move.Direction;

public class WrappedCandy extends SpecialCandy {

	public WrappedCandy() {
		Direction[] explosion = new Direction[4];
		explosion[0] = Direction.LEFT;
		explosion[1] = Direction.RIGHT;
		explosion[2] = Direction.UP;
		explosion[3] = Direction.DOWN;
	}

	@Override
	public String getKey() {
		return "WRAPPED-" + super.getKey();
	}
	
	@Override
	public String getFullKey() {
		return "WRAPPED-" + super.getFullKey();
	}

	@Override
	public long getScore() {
		return 60;
	}

}
