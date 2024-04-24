package piece;

import main.GamePanel;

public class King extends Piece{

    public King(int col, int row, int color) {
        super(col, row, color);

        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-king.png");
        }
        else{
            image = getImage("src/main/resources/b-king.png");
        }
    }
}
