package piece;

import main.GamePanel;

public class Rook extends Piece{
    public Rook(int col, int row, int color) {
        super(col, row, color);
        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-rook.png");
        }
        else{
            image = getImage("src/main/resources/b-rook.png");
        }
    }
}
