package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.CandyGeneratorCellExtended;
import game.backend.cell.Cell;
import game.backend.element.*;

public class Level3 extends Level {
    private final int CANT_FRUITS = 20;
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
        int decrease;
        while ( (decrease = lastLineFruits()) != 0 ) {
            CandyGeneratorCellExtended aux = (CandyGeneratorCellExtended) candyGenCell;
            aux.decreaseFruits();
        }
    }

    private int lastLineFruits(){
        int collectedFruits = 0;
        for(int j = 0; j < SIZE; j++){
            if(g()[SIZE-1][j].getContent() instanceof Fruit){
                g()[SIZE-1][j].setContent(new Nothing());
                collectedFruits++;
            }
        }
        fallElements();
        return collectedFruits;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        int decrease;
        if(!(get(i1,j1) instanceof Fruit) || !(get(i2,j2) instanceof Fruit)) {
            if (ret = super.tryMove(i1, j1, i2, j2)) { // es un movimiento valido
                state().addMove();
                while((decrease = lastLineFruits()) != 0){
                    state().setAux(state().getAux() - decrease);
                }
            }

        }
        else{
            ret = false;
        }
        return ret;
    }

        private class Level3State extends GameState{
        private int maxMoves;
        //score es la cantidad de frutas que baje, esto me queda exactamente igual al del level1!
        public Level3State(int maxMoves, int requiredFruits) {
                this.maxMoves = maxMoves;
                this.aux = requiredFruits;
        }

        @Override
        public boolean gameOver() {
            return playerWon() || maxMoves < getMoves();
            }

        @Override
        public boolean playerWon() {
            return getAux() == 0;
        } //saque todas las frutas
    }
}
