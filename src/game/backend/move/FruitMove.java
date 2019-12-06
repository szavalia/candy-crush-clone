package game.backend.move;

import game.backend.Figure;
import game.backend.FigureDetector;
import game.backend.Grid;
import game.backend.element.Fruit;

public class FruitMove extends Move {
    private Figure f;
    private int fi;
    private int fj;
    private FigureDetector detector;
    private Grid grid;

    public FruitMove(Grid grid){
        super(grid);
        this.grid = grid;
    }

    @Override
    public boolean internalValidation() {
        this.detector = new FigureDetector(grid);
       if(get(i1,j1) instanceof Fruit){
           f =  detector.checkFigure(i2, j2);
           fi = i2;
           fj = j2;
       }
       else {
           f = detector.checkFigure(i1, j1);
           fi = i1;
           fj = j1;
       }
        System.out.println(f == null);
       return f != null;
    }

    @Override
    public void removeElements() {
        System.out.println("entre al metodo");
        if (f != null) {
            System.out.println("che entre");
            detector.removeFigure(fi, fj, f);
        }
    }
}
