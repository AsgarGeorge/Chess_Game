package piece;

import main.GamePanel;

public class Queen extends Piece{
    public Queen(int col, int row, int color) {
        super(col, row, color);
        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-queen.png");
        }
        else{
            image = getImage("src/main/resources/b-queen.png");
        }
    }
}
