package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Wall;

public class Level2 extends Grid {
    private static int MAX_MOVES = 20;
    private Boolean[][] golden = new Boolean[2][SIZE];
    private Cell wallCell;
    private Cell candyGenCell;

    @Override
    protected GameState newState() {
        return new Level2.Level2State(MAX_MOVES);
    }

    @Override
    protected void fillCells() {

        wallCell = new Cell(this);
        wallCell.setContent(new Wall());
        candyGenCell = new CandyGeneratorCell(this);

        //corners
        g()[0][0].setAround(candyGenCell, g()[1][0], wallCell, g()[0][1]);
        g()[0][SIZE-1].setAround(candyGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
        g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
        g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);

        //upper line cells
        for (int j = 1; j < SIZE-1; j++) {
            g()[0][j].setAround(candyGenCell,g()[1][j],g()[0][j-1],g()[0][j+1]);
        }
        //bottom line cells
        for (int j = 1; j < SIZE-1; j++) {
            g()[SIZE-1][j].setAround(g()[SIZE-2][j], wallCell, g()[SIZE-1][j-1],g()[SIZE-1][j+1]);
        }
        //left line cells
        for (int i = 1; i < SIZE-1; i++) {
            g()[i][0].setAround(g()[i-1][0],g()[i+1][0], wallCell ,g()[i][1]);
        }
        //right line cells
        for (int i = 1; i < SIZE-1; i++) {
            g()[i][SIZE-1].setAround(g()[i-1][SIZE-1],g()[i+1][SIZE-1], g()[i][SIZE-2], wallCell);
        }
        //central cells
        for (int i = 1; i < SIZE-1; i++) {
            for (int j = 1; j < SIZE-1; j++) {
                g()[i][j].setAround(g()[i-1][j],g()[i+1][j],g()[i][j-1],g()[i][j+1]);
            }
        }

        for(int i = 0; i < SIZE; i++){
            golden[0][i] = false;
            golden[1][i] = false;
        }
    }
    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            if ( i1 == i2 ){
                System.out.println("movimiento horizontal");
                if (!golden[0][i1]) {
                    golden[0][i1] = true;
                    int j;
                    for ( j = 0 ; j < SIZE ; j++ ) {
                        g()[i2][j].goldenOn();
                    }
                }
            }
            else {
                System.out.println("movimiento vertical");
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
        private long actualGoldens;
        public Level2State(int maxMoves ) {

            this.maxMoves = maxMoves;
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
            System.out.println( actualGoldens );
            int i;
            for ( i  = 0 ; i < SIZE ; i++)
                if ( golden[0][i]) {
                    System.out.print("x|");
                }
                else {
                    System.out.print("0|");
                }
                System.out.print("\n");
            for ( i  = 0 ; i < SIZE ; i++)
                if ( golden[1][i]) {
                    System.out.print("x|");
                }
                else {
                    System.out.print("0|");
                }
            System.out.print("\n");



        }
        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return actualGoldens == SIZE * SIZE;
        }
    }


}
