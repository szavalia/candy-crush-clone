package game.backend.move;

import game.backend.Figure;
import game.backend.FigureDetector;
import game.backend.Grid;
import game.backend.element.Fruit;

//public class FruitMove extends Move {
//    private Figure f1;
//    private Figure f2;
//    private FigureDetector detector;
//    private Grid grid;
//    private boolean fruit1;
//    private boolean fruit2;
//
//    public FruitMove(Grid grid){
//        super(grid);
//        this.grid = grid;
//        fruit1 = get(i1,j1) instanceof Fruit;
//        fruit2 = get(i2,j2) instanceof Fruit;
//    }
//
//    @Override
//    public boolean internalValidation(){
//        this.detector = new FigureDetector(grid);
//        f1 = fruit1? null : detector.checkFigure(i1,j1); //si es una fruta, no busques figuras
//        f2 = fruit2? null: detector.checkFigure(i2,j2);
//        return f1 != null || f2 != null;
//    }
//
//}
