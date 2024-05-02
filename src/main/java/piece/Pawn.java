package piece;

import main.GamePanel;
import main.Type;

public class Pawn extends Piece{
    public Pawn(int col, int row, int color) {
        super(col, row, color);

        type = Type.PAWN;

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
            // movement of En-passant
            if(Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue){
                for(Piece p : GamePanel.simPieces){
                    if(p.row == preRow && p.col == targetCol && p.twoStepped == true){
                        hittingP = p;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
