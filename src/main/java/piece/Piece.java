package piece;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece {
    public BufferedImage image;
    public int x,y ;
    public int col, row, preCol, preRow;
    public int color;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piece(int col, int row, int color) {
        this.col = col;
        this.row = row;
        this.color = color;
        x = getX(col);
        y = getY(row);
        preCol =  col;
        preRow = row;
    }
    public BufferedImage getImage(String imagePath){
        BufferedImage bufferedImage = null;
        try {
            image = ImageIO.read(new File(imagePath));
            //image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col) {
        return col* Board.SQUARE_SIZE;
    }
    public int getY(int row){
        return row*Board.SQUARE_SIZE;
    }
    public int getCol(int x){
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    public int getRow(int y){
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image,x,y,Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
    }

    public void updatePosition() {
        x = getX(col);
        y = getY(row);
        //System.out.println("position updated x = " + x +" col = " + col + " preCol= " +preCol);
        //System.out.println("position updated y = " + y +" row = " + row +" preRow= " + preRow);
        preCol = getCol(x);
        preRow = getRow(y);

    }
}

