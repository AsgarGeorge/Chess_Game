package piece;

import main.GamePanel;

public class Knight extends Piece{

    public Knight(int col, int row, int color) {
        super(col, row, color);

        if(color == GamePanel.WHITE){
            image = getImage("src/main/resources/w-knight.png");
        }
        else{
            image = getImage("src/main/resources/b-knight.png");
        }
    }
}
