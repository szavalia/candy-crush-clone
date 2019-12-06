package game.backend.level;

import game.backend.GameState;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.JailedCandy;

public class Level5 extends Level {
    private static int CENTER = SIZE/2;

    @Override
    public GameState newState(){ return new Level5State( 500, 5000); }


    @Override
    public void initialize(){
        super.initialize();
        generateJails();
    }

    private void generateJails(){
        int i;
        JailedCandy aux;
        for ( i = 0 ; i < SIZE ; i++){
            if( i != (CENTER) ){
                aux = new JailedCandy();
                aux.setColor(((Candy)g()[CENTER][i].getContent()).getColor());
                g()[CENTER][i].setContent(aux);
            }
        }
    }

    private class Level5State extends GameState {
        private long requiredScore;
        private long maxMoves;

        public Level5State(long requiredScore, int maxMoves) {
            this.requiredScore = requiredScore;
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return getScore() > requiredScore;
        }
    }

}
