package piece;

import main.GamePanel;
import main.Main;

public class King extends Piece{

    public King(int col, int row, int color) {
        super(col, row, color);

        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-king.png");
        }
        else{
            image = getImage("src/main/resources/b-king.png");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol,targetRow)){
            if(Math.abs(targetCol - preCol)+ Math.abs(targetRow - preRow) == 1 || Math.abs(targetCol - preCol)* Math.abs(targetRow - preRow) ==1){
                if(isValidSquare(targetCol,targetRow)){
                    return true;
                }
            }
            else return false;
        }
        return false;
    }
}
