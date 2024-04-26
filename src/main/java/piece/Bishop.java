package piece;

import main.GamePanel;

public class Bishop extends Piece{
    public Bishop(int col, int row, int color) {
        super(col, row, color);
        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-bishop.png");
        }
        else{
            image = getImage("src/main/resources/b-bishop.png");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow) == false){
            if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)){
                if(isValidSquare(targetCol,targetRow) && pieceIsOnDiagonal(targetCol,targetRow)==false) {
                    return true;
                }
            }
        }



        return false;
    }
}
