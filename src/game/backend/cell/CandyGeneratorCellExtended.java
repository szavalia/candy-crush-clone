package game.backend.cell;

import game.backend.Grid;
import game.backend.element.CandyColor;
import game.backend.element.Cherry;
import game.backend.element.Element;
import game.backend.element.Hazelnut;
import game.backend.level.Level3;

public class CandyGeneratorCellExtended extends CandyGeneratorCell {
    private int cantCherrys;
    private int cantHazelnut;
    private int placedFruits = 0;
    private int cantFruits;
    public CandyGeneratorCellExtended(Grid grid, int cantFruits) {
        super(grid);
        this.cantFruits = cantFruits;
        cantCherrys = (int)(Math.random() * cantFruits);
        cantHazelnut = cantFruits - cantCherrys; //al inicar, la distribucion de las frutas queda determinada
    }

    //Quiero que me aparezca un numero determinado de frutas, de las cuales hay cantCherries y cantHazelnuts

    @Override
    public Element getContent() {
        boolean returnFruit = false;
        int i = (int)(Math.random() * (CandyColor.values().length + 1)); //Tomo al elemento Fruit como un tipo de caramelo más
        if (i == CandyColor.values().length){
            returnFruit = true; //dame una fruta
        }
        return (returnFruit? (placedFruits < cantFruits ? getFruit() : super.getContent()): super.getContent());
    } //si sale por chance que tengo que meter una fruta y me quedan frutas por poner, ponela. Sino, mete un caramelo

    private Element getFruit(){
        int random = (int) (Math.random() * 2); //puede ser 0 o 1, equivalente a tirar una moneda
        boolean giveCherry = false;
        if(cantCherrys > 0 && cantHazelnut > 0){ //todavia me quedan cosas para poner
            if (random == 0){
                giveCherry = true;
            }
        }
        else if(cantHazelnut == 0) {//no me quedan mas cherries
            giveCherry = true;
        }
       return (giveCherry? getCherry() : getHazelnut());
    }

    private Element getCherry(){
        cantCherrys--;
        placedFruits++;
        return new Cherry();
    }
    private Element getHazelnut(){
        cantHazelnut--;
        placedFruits++;
        return new Hazelnut();
    }
}
