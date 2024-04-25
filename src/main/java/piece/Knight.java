package piece;

import main.GamePanel;
import main.Main;

public class Knight extends Piece{

    public Knight(int col, int row, int color) {
        super(col, row, color);

        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-knight.png");
        }
        else{
            image = getImage("src/main/resources/b-knight.png");
        }
    }

    public boolean canMove(int targetCol,int targetRow){
        if(isWithinBoard(targetCol,targetRow)){
            if(Math.abs(targetCol - preCol)*Math.abs(targetRow - preRow) ==2){
                if(isValidSquare(targetCol,targetRow)){
                    return true;

                }
            }
        }
        return false;
    }
}
