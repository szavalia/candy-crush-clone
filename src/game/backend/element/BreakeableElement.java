package game.backend.element;

public abstract class BreakeableElement  extends  Element {
        protected Element content;
        public boolean isSolid() {
        return false;
    }
        public abstract Element drop();
}
