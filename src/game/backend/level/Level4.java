package game.backend.level;

import game.backend.GameState;
import game.backend.cell.Cell;
import game.backend.element.BreakableElement;
import game.backend.element.BreakableWall;

public class Level4 extends Level {


    private Cell wallCell;
    private Cell candyGenCell;
    @Override
    protected GameState newState(){ return new Level4State(1000, 1000);}

    @Override
    public void initialize(){
        super.initialize();
        addBreakableWalls();
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    public void addBreakableWalls(){
        g()[SIZE/2][SIZE/2].setContent(new BreakableWall( g()[SIZE/2][SIZE/2].getContent() ) {} );
    }

    private class Level4State extends GameState {
        private long requiredScore;
        private long maxMoves;

        public Level4State(long requiredScore, int maxMoves) {
            this.requiredScore = requiredScore;
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return getScore() > requiredScore;
        }
    }

}
