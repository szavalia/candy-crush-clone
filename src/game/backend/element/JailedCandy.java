package game.backend.element;

import game.backend.move.Direction;

public class JailedCandy extends BreakeableElement {
    private Candy candy;

    public JailedCandy( Candy candy) {
        this.candy = candy;
    }

    public boolean isMovable(){return false;};

    public String getKey(){ return "JAILED";};

    public Element drop(){
        return this.candy;
    };

    public String getFullKey() {
        return getKey();
    }

    public boolean isSolid() {
        return false;
    }

    public Direction[] explode() {
        return null;
    }

    public long getScore() {
        return 0;
    }

}

