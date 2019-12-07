package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorCellExtended;
import game.backend.cell.Cell;
import game.backend.element.*;

public class Level3 extends Level {
    private final int CANT_FRUITS = 2;
    private final int MAX_MOVES = 20;

    @Override
    protected GameState newState() {
        return new Level3State(MAX_MOVES, CANT_FRUITS);
    }

    @Override
    protected void fillCells() {
        wallCell = new Cell(this);
        wallCell.setContent(new Wall());
        candyGenCell = new CandyGeneratorCellExtended(this, CANT_FRUITS);
        generateCells();
    }

    @Override
    public void initialize() {
        super.initialize();
        for(int j = 0; j < SIZE; j++){
            if(g()[SIZE-1][j].getContent() instanceof Fruit){
                int i = (int) (Math.random() * CandyColor.values().length);
                g()[SIZE-1][j].setContent(new Candy(CandyColor.values()[i]));
                CandyGeneratorCellExtended aux = (CandyGeneratorCellExtended) candyGenCell;
                aux.decreaseFruits();
            }
        }
        checkFruits();
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
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
            if(get(SIZE-1, j) instanceof Fruit){
                g()[SIZE-1][j].clearContent();
                collectedFruits++;
            }
        }
        state().setAux(collectedFruits);
        fallElements();
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
