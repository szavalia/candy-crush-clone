package game.backend.element;

public class JailedCandy extends Candy {

    @Override
    public boolean isMovable(){ return false; };

    @Override
    public String getFullKey(){ return "JAIL"; }

    @Override
    public boolean canExplode(){return false;}
}
