package game.backend;

public abstract class GameState {

	protected int maxMoves = 0;
	private long score = 0;
	private int moves = 0;
	protected int aux = 0;

	void addScore(long value) {
		this.score = this.score + value;
	}

	public long getScore(){
		return score;
	}

	public void addMove() {
		moves++;
	}

	protected int getMoves() {
		return maxMoves - moves;
	}

	public abstract boolean gameOver();

	public abstract boolean playerWon();

	public int getAux(){
		return aux;
	}

	public void setAux(int value){
		aux = value;
	}

	public void updateAux(){}

}
