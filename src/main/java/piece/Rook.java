package piece;

import main.GamePanel;

public class Rook extends Piece {
    public Rook(int col, int row, int color) {
        super(col, row, color);
        if (color == GamePanel.WHITE) {
            image = getImage("src/main/resources/w-rook.png");
        } else {
            image = getImage("src/main/resources/b-rook.png");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow) == false ) {
            if(targetCol == preCol || targetRow == preRow){
                if(isValidSquare(targetCol,targetRow)){
                    return true;
                }
            }
        }
        return false;
    }
}


