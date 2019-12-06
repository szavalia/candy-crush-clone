package game.backend.element;

public abstract class BreakableElement extends Element {
    protected Element content;

    @Override
    public boolean isMovable(){ return false; }

    @Override
    public boolean isBreakable(){ return true; }

    public Element drop(){ return content;}


}
