package piece;

import main.Board;
import main.GamePanel;
import main.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece {

    public Type type;
    public BufferedImage image;
    public int x,y ;
    public int col, row, preCol, preRow;
    public int color;
    public Piece hittingP;
    public boolean moved , twoStepped;

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
    public int getIndex(){
        for (int index = 0; index < GamePanel.simPieces.size(); index++) {
            if(GamePanel.simPieces.get(index) == this){
                return index;
            }
        }
        return 0;
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

        if(type == Type.PAWN){
            if(Math.abs(row - preRow) == 2){
                twoStepped = true;
            }
        }
        x = getX(col);
        y = getY(row);

        //System.out.println("position updated x = " + x +" col = " + col + " preCol= " + preCol);
        //System.out.println("position updated y = " + y +" row = " + row + " preRow= " + preRow);
        preCol = getCol(x);
        preRow = getRow(y);
        moved = true;
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
    public boolean isSameSquare(int targetCol,int targetRow){
        if(targetCol == preCol && targetRow == preRow){
            return true;
        }
        return false;
    }

    //method for checking any obstacle before the movement
    public Piece getHittingP(int targetCol, int targetRow){
        for(Piece piece :GamePanel.simPieces){
            if(piece.col == targetCol && piece.row == targetRow && piece != this){
                return piece;
            }
        }
        return null;
    }
    public boolean isValidSquare(int targetCol, int targetRow){

        hittingP = getHittingP(targetCol,targetRow);
        if(hittingP == null) {
            return true;
        }
        else{
            if(hittingP.color != this.color){
                return true;
            }
            else hittingP = null;
        }
        return false;
    }
    public boolean pieceIsOnDiagonal(int targetCol,int targetRow) {
        if (targetRow < preRow) {
            // up left
            for (int c = preCol - 1; c > targetCol; c--) {
                int diff = Math.abs(c - preCol);
                for (Piece p : GamePanel.simPieces) {
                    if (p.col == c && p.row == preRow - diff) {
                        hittingP = p;
                        return true;
                    }
                }

            }
            //up left
            for (int c = preCol + 1; c < targetCol; c++) {
                int diff = Math.abs(c - preCol);
                for (Piece p : GamePanel.simPieces) {
                    if (p.col == c && p.row == preRow - diff) {
                        hittingP = p;
                        return true;
                    }
                }

            }
        }
        if (targetRow > preRow) {
            //down left
            for (int c = preCol - 1; c > targetCol; c--) {
                int diff = Math.abs(c - preCol);
                for (Piece p : GamePanel.simPieces) {
                    if (p.col == c && p.row == preRow + diff) {
                        hittingP = p;
                        return true;
                    }
                }
            }
            //down right
            for (int c = preCol + 1; c < targetCol; c++) {
                int diff = Math.abs(c - preCol);
                for (Piece p : GamePanel.simPieces) {
                    if (p.col == c && p.row == preRow + diff) {
                        hittingP = p;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean pieceIsOnStraightLine(int targetCol ,int targetRow){

        // when the piece is moving to the left
        for (int c = preCol-1; c > targetCol; c--) {
            for(Piece piece :GamePanel.simPieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }
        // when the piece is moving the right
        for (int c = preCol+1; c < targetCol; c++) {
            for(Piece piece :GamePanel.simPieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }
        // when the piece is moving up
        for (int r = preRow - 1; r > targetRow; r--) {
            for(Piece piece :GamePanel.simPieces){
                if(piece.col == targetCol && piece.row == r){
                    hittingP = piece;
                    return true;
                }
            }
        }
        // when the piece is moving down
        for (int r = preRow + 1; r < targetRow; r++) {
            for(Piece piece :GamePanel.simPieces){
                if(piece.col == targetCol && piece.row == r){
                    hittingP = piece;
                    return true;
                }
            }
        }
        return false;
    }

}

