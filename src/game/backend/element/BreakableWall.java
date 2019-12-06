package game.backend.element;

public class BreakableWall extends BreakableElement {

    public BreakableWall( Element content ){
        this.content = content;
    }
    @Override
    public String getKey(){return "BREAKABLEWALL";}

}
