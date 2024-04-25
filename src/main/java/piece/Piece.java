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

    public Piece(int col, int row, int color) {
        this.col = col;
        this.row = row;
        this.color = color;
        x = getX(col);
        y = getY(row);
        preCol =  col;
        preRow = row;
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

    //Method related with the Graphics w.r.t to the pieces
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
    public void draw(Graphics2D g2) {
        g2.drawImage(image,x,y,Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
    }


    //Methods for updating position once the piece has been moved
    public void updatePosition() {
        x = getX(col);
        y = getY(row);

        //System.out.println("position updated x = " + x +" col = " + col + " preCol= " + preCol);
        //System.out.println("position updated y = " + y +" row = " + row + " preRow= " + preRow);
        preCol = getCol(x);
        preRow = getRow(y);
    }
    public void resetPosition() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    //Method for checking the legal moves
    public boolean canMove(int targetCol,int targetRow){
        return false;
    }
    public boolean isWithinBoard(int targetCol,int targetRow){
        if(targetRow >= 0&& targetRow <=7 && targetCol <= 7 && targetCol >= 0){
            return true;
        }
        return false;
    }


}

