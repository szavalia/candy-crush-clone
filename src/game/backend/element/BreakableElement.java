package game.backend.element;

public abstract class BreakableElement extends Element {
    @Override
    public boolean isMovable(){ return false; }

    @Override
    public boolean isBreakable(){ return true; }

    protected Element content;

    public Element drop(){ return content;}


}
