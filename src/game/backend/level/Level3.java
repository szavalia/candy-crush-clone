package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.CandyGeneratorCellExtended;
import game.backend.cell.Cell;
import game.backend.element.*;

public class Level3 extends Level {
    private final int CANT_FRUITS = 10;
    private final int MAX_MOVES = 20;
    private CandyGeneratorCellExtended candyGenCellExt;

    @Override
    protected GameState newState() {
        return new Level3State(MAX_MOVES, CANT_FRUITS);

    }

    @Override
    protected void fillCells() {
        wallCell = new Cell(this);
        wallCell.setContent(new Wall());
        candyGenCellExt = new CandyGeneratorCellExtended(this, CANT_FRUITS);

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
        for(int j = 0; j < SIZE; j++){
            int i = (int) (Math.random() * CandyColor.values().length);
            g()[SIZE-2][j].setContent(new Candy(CandyColor.values()[i])); //los del piso siempre tienen que ser caramelos
        }
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (get(i1, j1) instanceof Hazelnut || get(i2, j2) instanceof Hazelnut) {
            return false;
        }
        else if(get(i1, j1) instanceof Cherry || get(i2, j2) instanceof Cherry) {
            return false;
        } //si es una fruta, no lo muevas
        if (ret = super.tryMove(i1, j1, i2, j2)) { // es un movimiento valido
            state().addMove();
        }
        checkFruits(); //fijate si me quedaron frutas al fondo y agregalas al aux de GameState
        return ret;
    }
    private void checkFruits(){
        int j;
        int collectedFruits = 0;
        for(j = 0; j < SIZE; j++){
            if(get(SIZE-2, j) instanceof Fruit){
                g()[SIZE-2][j].clearContent();
                collectedFruits++;
            }
        }
        state().addAux(collectedFruits);
    }
        private class Level3State extends GameState{
        private int maxMoves;
        private long requiredFruits;
        //score es la cantidad de frutas que baje, esto me queda exactamente igual al del level1!
        public Level3State(int maxMoves, int requiredFruits) {
                this.maxMoves = maxMoves;
                this.requiredFruits = requiredFruits;
        }

        @Override
        public boolean gameOver() {
            return playerWon() || maxMoves < getMoves();
            }

        @Override
        public boolean playerWon() {
            return getAux() >= requiredFruits;
        }
    }
}
