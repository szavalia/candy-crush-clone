package game.backend.level;

import game.backend.GameState;
import game.backend.cell.Cell;
import game.backend.element.BreakableElement;
import game.backend.element.BreakableWall;

public class Level4 extends Level {
    private static int MAXMOVES = 500;
    private static int WALLHEIGHT = 3;
    private static int WALLWIDTH = 3;
    private static int WALLCENTER = SIZE/2;
    private Cell wallCell;
    private Cell candyGenCell;
    @Override
    protected GameState newState(){ return new Level4State( MAXMOVES , WALLHEIGHT * WALLWIDTH);}

    @Override
    public void initialize(){ ;
        super.initialize();
        addBreakableWalls();
        checkWalls();
        wasUpdated();
    }

    public void checkWalls() {
        int i, j, total = 0;
        for (i = 0; i < WALLHEIGHT; i++) {
            for (j = 0; j < WALLWIDTH; j++) {
                if (g()[WALLCENTER - (WALLHEIGHT) / 2 + i][WALLCENTER - (WALLWIDTH) / 2 + j].getContent().isBreakable()) {
                    total++;
                }
            }
        }
        state().setAux(total);
        System.out.println( "Vi totales");
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            checkWalls();
        }
        return ret;
    }

    public void addBreakableWalls() {
        int i, j;
        for (i = 0; i < WALLHEIGHT; i++) {
            for (j = 0; j < WALLWIDTH; j++) {
                g()[WALLCENTER - (WALLHEIGHT)/2 + i ][WALLCENTER - (WALLWIDTH)/2 + j].setContent(new BreakableWall(g()[WALLCENTER -(WALLHEIGHT)/2 + i][WALLCENTER -(WALLWIDTH)/2 + j].getContent()));
            }
        }
    }

    private class Level4State extends GameState {

        private long maxMoves;

        public Level4State(int maxMoves, int walls ) {
            this.maxMoves = maxMoves;
            aux = walls;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }


        public boolean playerWon(){
            return (aux == 0);
        }
    }

}
