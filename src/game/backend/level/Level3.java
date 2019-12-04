package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.CandyGeneratorCellExtended;
import game.backend.cell.Cell;
import game.backend.element.Cherry;
import game.backend.element.Hazelnut;
import game.backend.element.Wall;

public class Level3 extends Level {
    private final int WIN_FRUITS = 5;
    private final int MAX_FRUITS = 10;
    private final int MAX_MOVES = 20;
    private int winFruits = 0;
    private CandyGeneratorCellExtended candyGenCellExt;

    @Override
    protected GameState newState() {
        return new Level3State(WIN_FRUITS, MAX_MOVES);
    }

    @Override
    protected void fillCells() {
        wallCell = new Cell(this);
        wallCell.setContent(new Wall());
        candyGenCellExt = new CandyGeneratorCellExtended(this, MAX_FRUITS);

        //corners
        g()[0][0].setAround(candyGenCellExt, g()[1][0], wallCell, g()[0][1]);
        g()[0][SIZE - 1].setAround(candyGenCellExt, g()[1][SIZE - 1], g()[0][SIZE - 2], wallCell);
        g()[SIZE - 1][0].setAround(g()[SIZE - 2][0], wallCell, wallCell, g()[SIZE - 1][1]);
        g()[SIZE - 1][SIZE - 1].setAround(g()[SIZE - 2][SIZE - 1], wallCell, g()[SIZE - 1][SIZE - 2], wallCell);

        //upper line cells
        for (int j = 1; j < SIZE - 1; j++) {
            g()[0][j].setAround(candyGenCellExt, g()[1][j], g()[0][j - 1], g()[0][j + 1]);
        }
        //bottom line cells
        for (int j = 1; j < SIZE - 1; j++) {
            g()[SIZE - 1][j].setAround(g()[SIZE - 2][j], wallCell, g()[SIZE - 1][j - 1], g()[SIZE - 1][j + 1]);
        }
        //left line cells
        for (int i = 1; i < SIZE - 1; i++) {
            g()[i][0].setAround(g()[i - 1][0], g()[i + 1][0], wallCell, g()[i][1]);
        }
        //right line cells
        for (int i = 1; i < SIZE - 1; i++) {
            g()[i][SIZE - 1].setAround(g()[i - 1][SIZE - 1], g()[i + 1][SIZE - 1], g()[i][SIZE - 2], wallCell);
        }
        //central cells
        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                g()[i][j].setAround(g()[i - 1][j], g()[i + 1][j], g()[i][j - 1], g()[i][j + 1]);
            }
        }
    }
    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            if ((i1 == SIZE - 1 || i2 == SIZE - 1) && (get(i1,j1) instanceof Cherry || get(i2,j2) instanceof Hazelnut)){
                winFruits++;
            }
        }
        return ret;
    }
        private class Level3State extends GameState{
        private long maxMoves;
        private int winFruit;

        public Level3State(int winFruit, long maxMoves) {
            this.maxMoves = maxMoves;
            this.winFruit = winFruit;
        }

        @Override
        public boolean gameOver() {
            return maxMoves < getMoves();
        }

        @Override
        public boolean playerWon() {
            return winFruit < winFruits;
        }
    }
}
