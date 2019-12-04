package game.backend.element;

public class Cherry extends Element {

    @Override
    public String getKey() {
        return "Cherry";
    }

    @Override
    public boolean isMovable() {
        return true;
    }
}
