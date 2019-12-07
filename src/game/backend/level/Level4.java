package game.backend.level;

import game.backend.GameState;


public class Level4 extends Level {
    private final int MAXMOVES = 20;
    private static int WALLHEIGHT = 3;
    private static int WALLWIDTH = 3;
    private static int WALLCENTER = SIZE/2;
    @Override
    protected GameState newState(){ return new Level4State( MAXMOVES , WALLHEIGHT * WALLWIDTH);}

    @Override
    public void initialize(){
        super.initialize();
        addBreakableWalls();
        state().updateAux();
        wasUpdated();
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            state().updateAux();
        }
        return ret;
    }

    private void addBreakableWalls() {
        int i, j;
        for (i = 0; i < WALLHEIGHT; i++) {
            for (j = 0; j < WALLWIDTH; j++) {
                g()[WALLCENTER - (WALLHEIGHT)/2 + i ][WALLCENTER - (WALLWIDTH)/2 + j].wallOn();
            }
        }
    }

    private class Level4State extends GameState {
        private Level4State(int maxMoves, int walls ) {
            this.maxMoves = maxMoves;
            aux = walls;
        }

        @Override
        public void updateAux() {
            int i, j, total = 0;
            for (i = 0; i < WALLHEIGHT; i++) {
                for (j = 0; j < WALLWIDTH; j++) {
                    if (g()[WALLCENTER - (WALLHEIGHT) / 2 + i][WALLCENTER - (WALLWIDTH) / 2 + j].hasWall()){
                        total++;
                    }
                }
            }
            aux = total;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() <= 0;
        }


        public boolean playerWon(){
            return (aux == 0);
        }
    }

}

