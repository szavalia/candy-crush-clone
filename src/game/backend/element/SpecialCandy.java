package game.backend.element;

import game.backend.move.Direction;

public abstract class SpecialCandy extends Candy{
    protected Direction[] explosion;

    @Override
    public Direction[] explode() {
        return explosion;
    }
}
