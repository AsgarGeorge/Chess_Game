package piece;

import main.GamePanel;

public class King extends Piece{

    public King(int col, int row, int color) {
        super(col, row, color);

        if(color == GamePanel.WHITE){
            image = getImage("piece/w-king");
        }
        else{
            image = getImage("piece/b-king");
        }
    }
}
