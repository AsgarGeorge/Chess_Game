package main;

import piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    //pieces management
    public ArrayList<Piece> pieces = new ArrayList<>();
    public ArrayList<Piece> simPieces = new ArrayList<>();

    public void setPieces(){

        //setting up the white pieces

        pieces.add(new Pawn(0,6,WHITE));
        pieces.add(new Pawn(1,6,WHITE));
        pieces.add(new Pawn(2,6,WHITE));
        pieces.add(new Pawn(3,6,WHITE));
        pieces.add(new Pawn(4,6,WHITE));
        pieces.add(new Pawn(5,6,WHITE));
        pieces.add(new Pawn(6,6,WHITE));
        pieces.add(new Pawn(7,6,WHITE));
        pieces.add(new Knight(1,7,WHITE));
        pieces.add(new Knight(6,7,WHITE));
        pieces.add(new Rook(7,7,WHITE));
        pieces.add(new Rook(0,7,WHITE));
        pieces.add(new Bishop(2,7,WHITE));
        pieces.add(new Bishop(5,7,WHITE));
        pieces.add(new Queen(3,7,WHITE));
        pieces.add(new King(4,7,WHITE));

        //setting up the black pieces
        pieces.add(new Pawn(0,1,BLACK));
        pieces.add(new Pawn(1,1,BLACK));
        pieces.add(new Pawn(2,1,BLACK));
        pieces.add(new Pawn(3,1,BLACK));
        pieces.add(new Pawn(4,1,BLACK));
        pieces.add(new Pawn(5,1,BLACK));
        pieces.add(new Pawn(6,1,BLACK));
        pieces.add(new Pawn(7,1,BLACK));
        pieces.add(new Knight(1,0,BLACK));
        pieces.add(new Knight(6,0,BLACK));
        pieces.add(new Rook(7,0,BLACK));
        pieces.add(new Rook(0,0,BLACK));
        pieces.add(new Bishop(2,0,BLACK));
        pieces.add(new Bishop(5,0,BLACK));
        pieces.add(new Queen(3,0,BLACK));
        pieces.add(new King(4,0,BLACK));
    }
    public void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target){
        target.clear();
        for (int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
    }


    //color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        setPieces();
        copyPieces(pieces,simPieces);
    }
    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;

            }
        }

    }

    private void update(){
        System.out.println("update");

    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D) graphics;
        board.draw(g2);
        for(Piece p : simPieces){
            p.draw(g2);
        }


    }


}
