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
}
