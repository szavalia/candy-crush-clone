package game.backend.cell;

import game.backend.element.Cherry;
import game.backend.element.Element;
import game.backend.element.Hazelnut;
import game.backend.level.Level3;

public class CandyGeneratorCellExtended extends CandyGeneratorCell {
    private int cantCherrys;
    private int cantHazelnut;

    public CandyGeneratorCellExtended(Level3 grid, int max) {
        super(grid);
        cantCherrys = (int)(Math.random() * max);
        cantHazelnut = (int) (Math.random() * (max - cantCherrys));
    }

    @Override
    public Element getContent() {
        int random = (int) (Math.random() * 2);
        if (random == 0){
            return super.getContent();
        }
        Element aux = getFruit();
        return aux == null? super.getContent() : getFruit();
    }

    private Element getFruit(){
        Element aux;
        int random = (int) (Math.random() * 2);
        if (random == 0 && cantCherrys > 0){
            cantCherrys --;
            return new Cherry();
        }
        else if ((random == 1 && cantHazelnut > 0) || cantCherrys == 0 ) {
            cantHazelnut--;
            return new Hazelnut();
        }
        return null;
    }
}
