package game.backend.level;

import game.backend.GameState;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.JailedCandy;

public class Level5 extends Level {
    private static int CENTER = SIZE/2;

    @Override
    public GameState newState(){ return new Level5State( SIZE -1 , 25); }


    @Override
    public void initialize(){
        super.initialize();
        generateJails();
        state().setAux(0);
        wasUpdated();
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            state().setAux(0);
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

        private int maxMoves;
        public Level5State(int jailNumber , int maxMoves) {
          aux = jailNumber;
          this.maxMoves = maxMoves;
        }

        @Override
        public void setAux( int value ){
            int i;
            for ( i = 0 ; i < SIZE ; i++){
                if ( g()[CENTER][i].getContent() instanceof JailedCandy ){
                    value +=1;
                }
            }
            aux = value;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return aux == 0 ;
        }
    }

}
