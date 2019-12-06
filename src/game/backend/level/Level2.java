package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Wall;

public class Level2 extends Level {
    private static int MAX_MOVES = 20;
    private Boolean[][] golden = new Boolean[2][SIZE];
    private static int TOTAL_GOLDENS = SIZE*SIZE;

    @Override
    protected void fillCells() {
        super.fillCells();
        System.out.println("llegue a generar la celda");
        for (int i = 0; i < SIZE; i++){
            golden[0][i] = false;
            golden[1][i] = false;
        }
    }

    @Override
    protected GameState newState() {
        return new Level2.Level2State(MAX_MOVES, TOTAL_GOLDENS);
    }


    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            if ( i1 == i2 ){
                if (!golden[0][i1]) {
                    golden[0][i1] = true;
                    int j;
                    for ( j = 0 ; j < SIZE ; j++ ) {
                        g()[i2][j].goldenOn();
                    }
                }
            }
            else {
                if (!golden[1][j1]) {
                    golden[1][j1] = true;
                    int i;
                    for ( i = 0 ; i < SIZE ; i++ ) {
                        g()[i][j1].goldenOn();
                    }
                }
            }
            state().addMove();
            wasUpdated();
        }
        return ret;
    }

    private class Level2State extends GameState {
        private long maxMoves;
        private int actualGoldens;
        public Level2State(int maxMoves, int totalGoldens ) {
            this.maxMoves = maxMoves;
            this.aux = totalGoldens;
        }

        @Override
        public void addMove() {
            super.addMove();
            updateGolden();
        }

        public void updateGolden(){
            int j;
            int vertical = 0 ;
            int horizontal = 0;
            for ( j = 0 ; j< SIZE ; j++){
                if ( golden[0][j]){
                    vertical ++;
                }
            }
            for ( j = 0 ; j< SIZE ; j++){
                if ( golden[1][j]){
                    horizontal++;
                }
            }
            actualGoldens = SIZE*(horizontal + vertical) - vertical*horizontal;
            aux = SIZE * SIZE - actualGoldens;
        }
        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return actualGoldens == SIZE * SIZE;
        }
    }


}
