package game.backend.element;

public class JailedCandy extends Candy {

    @Override
    public String getKey() {
        return "JAIL";
    }

    @Override
    public boolean isMovable(){ return false; };

    @Override
    public String getFullKey(){ return getKey(); }

    @Override
    public boolean canExplode(){return false;}
}
