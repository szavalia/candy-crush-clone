package game.backend.element;

public class Hazelnut extends Element {

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public String getKey() {
        return "HAZELNUT";
    }
}
