package piece;

import main.GamePanel;

public class Pawn extends Piece{
    public Pawn(int col, int row, int color) {
        super(col, row, color);

        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-pawn.png");
        }
        else{
            image = getImage("src/main/resources/b-pawn.png");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow) == false){
            int moveValue;
            if(color == GamePanel.WHITE){
                moveValue = -1;
            }
            else{ moveValue = 1;}

            hittingP = getHittingP(targetCol,targetRow);
            if(targetCol == preCol && targetRow == preRow + moveValue && hittingP ==null){
                return true;
            }
            if(targetCol == preCol && targetRow == preRow + moveValue*2 && hittingP ==null && moved ==false  && pieceIsOnStraightLine(targetCol,targetRow) == false){
                return true;
            }
            if(Math.abs(targetCol - preCol)==1 && targetRow == preRow + moveValue && hittingP != null && hittingP.color != this.color){
                return true;

            }
        }
        return false;
    }
}
