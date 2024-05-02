package piece;

import main.GamePanel;
import main.Main;
import main.Type;

public class King extends Piece {

    public King(int col, int row, int color) {
        super(col, row, color);

        type = Type.KING;
        if (color == GamePanel.WHITE) {
            image = getImage("src/main/resources/w-king.png");
        } else {
            image = getImage("src/main/resources/b-king.png");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if (isWithinBoard(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 || Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
                if (isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
            // code for the castling for the king
            if (moved == false) {

                // casting on the KING side
                if (targetCol == preCol + 2 && targetRow == preRow && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                    for(Piece piece : GamePanel.simPieces){
                        if(piece.col == preCol +3 && piece.row == preRow && piece.moved == false){
                            GamePanel.castlingP = piece;
                            return true;
                        }
                    }
                }
                // casting on the QUEEN side
                if (targetCol == preCol - 2 && targetRow == preRow && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                    Piece p[]  = new Piece[2];
                    for (Piece piece :GamePanel.simPieces){
                        if(piece.col == preCol - 3 && piece.row == targetRow){
                            p[0] = piece;
                        }
                        if(piece.col == preCol - 4 && piece.row == targetRow){
                            p[1] = piece;
                        }
                        if(p[0] == null && p[1] != null &&p[1].moved == false){
                            GamePanel.castlingP = p[1];
                            return true;
                        }
                    }

                }
            }

        }
        return false;
    }
}
