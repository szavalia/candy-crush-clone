package game.backend.level;

import game.backend.GameState;
import game.backend.element.Candy;
import game.backend.element.JailedCandy;

public class Level5 extends Level {
    private static int CENTER = SIZE/2;
    private static int MAXMOVES = 30;
    @Override
    public GameState newState(){ return new Level5State( SIZE -1 , MAXMOVES); }


    @Override
    public void initialize(){
        super.initialize();
        generateJails();
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
    private void generateJails(){
        int i;
        JailedCandy auxJail;
        for ( i = 0 ; i < SIZE ; i++){
            if( i != (CENTER) ){
                auxJail = new JailedCandy();
                auxJail.setColor(((Candy)g()[CENTER][i].getContent()).getColor());
                g()[CENTER][i].setContent(auxJail);
            }
        }
    }

    private class Level5State extends GameState {
        public Level5State(int jailNumber , int maxMoves) {
          aux = jailNumber;
          this.maxMoves = maxMoves;
        }

        @Override
        public void updateAux(){
            int i, total = 0;
            for ( i = 0 ; i < SIZE ; i++){
                if ( g()[CENTER][i].getContent() instanceof JailedCandy ){
                    total +=1;
                }
            }
            aux = total;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() <= 0;
        }

        public boolean playerWon() {
            return aux == 0 ;
        }
    }

}
