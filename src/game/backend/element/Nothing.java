package game.backend.element;

public class Nothing extends BreakeableElement{

	public Nothing(){
		this.content = null;
	}

	@Override
	public Element drop(){
		return this;
	};

	@Override
	public boolean isMovable() {
		return false;
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
	
	@Override
	public String getKey() {
		return "NOTHING";
	}

}
