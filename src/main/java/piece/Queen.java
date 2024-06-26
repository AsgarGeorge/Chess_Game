package piece;

import main.GamePanel;
import main.Type;

public class Queen extends Piece{

    public Queen(int col, int row, int color) {
        super(col, row, color);
        type = Type.QUEEN;

        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-queen.png");
        }
        else{
            image = getImage("src/main/resources/b-queen.png");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow) == false) {

            if( targetCol == preCol || targetRow == preRow){
                if (isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                    //System.out.println("queen moving");
                    return true;

                }
            }

            if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                if (isValidSquare(targetCol, targetRow) && pieceIsOnDiagonal(targetCol, targetRow) == false) {
                    return true;
                }
            }
        }
        return false;
    }
}
