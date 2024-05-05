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

    //instantiate the components
    Board board = new Board();
    Mouse mouse = new Mouse();


    //pieces management
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    public static ArrayList<Piece> promoPieces = new ArrayList<>();


    Piece activeP ;
    public static Piece castlingP;


    public void test(){
        pieces.add(new Pawn(0,1,WHITE));
        pieces.add(new Pawn(4,1,BLACK));
        pieces.add(new King(3,7,WHITE));
        pieces.add(new King(0,3,BLACK));
        pieces.add(new Bishop(1,4,BLACK));
        pieces.add(new Queen(4,5,BLACK));

    }
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

    //Booleans
    boolean canMove;
    boolean validateSquare;
    boolean promotion;

    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);


        //adding mouse listeners
        addMouseMotionListener(mouse);
        addMouseListener(mouse);


        setPieces();
        //test();
        copyPieces(pieces,simPieces);
    }
    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // game thread ->> controls the flow of the game
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
        if(promotion){
            promoting();
        }
        else {

            if (mouse.pressed) {
                if (activeP == null) {
                    for (Piece piece : simPieces) {
                        if (piece.color == currentColor && piece.col == mouse.x / board.SQUARE_SIZE && piece.row == mouse.y / board.SQUARE_SIZE) {
                            activeP = piece;
                        }
                    }
                } else {
                    // if a player is holding the piece , we need to simulate
                    simulate();
                }
            }
            if (mouse.pressed == false) {
                if (activeP != null) {

                    if (validateSquare) {
                        // the move which the player had has been confirmed

                        copyPieces(simPieces, pieces);
                        activeP.updatePosition();
                        if (castlingP != null) {
                            castlingP.updatePosition();
                        }

                        if (canPromote()) {
                            promotion = true;
                        } else {
                            changePlayer();
                        }
                    } else {
                        // the move is not confirmed

                        copyPieces(pieces, simPieces);
                        activeP.resetPosition()
                        ;
                        activeP = null;
                    }

                }

            }
        }
    }

    public void simulate(){

        canMove = false;
        validateSquare = false;
        //Reset the simPieces in every loop
        copyPieces(pieces,simPieces);

        if(castlingP != null){
            castlingP.col = castlingP.preCol;
            castlingP.x = castlingP.getX(castlingP.col);
            castlingP = null;
        }





        // if a piece is held , update its position
        activeP.x = (mouse.x - Board.HALF_SQUARE_SIZE);
        activeP.y = (mouse.y - Board.HALF_SQUARE_SIZE);
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);

        // check if the piece is hovering over a reachable square
        if(activeP.canMove(activeP.col,activeP.row)){
            canMove = true;
            if(activeP.hittingP != null){
                simPieces.remove(activeP.hittingP.getIndex());
            }
            checkCastling();
            if(isIllegal(activeP) == false){
                validateSquare = true;
            }
            validateSquare = true;
        }


    }

    private boolean isIllegal(Piece king) {
        if(king.type == Type.KING){
            for(Piece piece : simPieces){
                if(piece.type != Type.KING && king.color != piece.color && piece.canMove(king.col, king.row)){
                    return true;
                }
            }
        }
        return false;
    }

    private void checkCastling(){
        if(castlingP != null){
            if(castlingP.col == 0){
                castlingP.col = castlingP.col +3;
            }
            else if (castlingP.col == 7){
                castlingP.col = castlingP.col - 2;
            }
            castlingP.x = castlingP.getX(castlingP.col);
        }
    }
    private void changePlayer(){
        if(currentColor == WHITE){
            currentColor = BLACK;
            for(Piece p : simPieces){
                if(p.color == BLACK){
                p.twoStepped = false;
                }
            }
        }
        else {
            currentColor = WHITE;
            for(Piece p : simPieces){
                if(p.color == WHITE){
                    p.twoStepped = false;
                }
            }
        }
        activeP = null;
    }

    public boolean canPromote(){
        if(activeP.type == Type.PAWN){
            if(currentColor == WHITE && activeP.row == 0 || currentColor == BLACK && activeP.row == 7){
                promoPieces.clear();
                promoPieces.add(new Rook(9,2,currentColor));
                promoPieces.add(new Bishop(9,3,currentColor));
                promoPieces.add(new Knight(9,4,currentColor));
                promoPieces.add(new Queen(9,5,currentColor));
                //System.out.println("adding pieces");
                return true;
            }
        }
        return false;

    }
    private void promoting(){
        if(mouse.pressed){
            for(Piece piece : promoPieces){
                if(piece.col == mouse.x/Board.SQUARE_SIZE && piece.row == mouse.y/Board.SQUARE_SIZE){
                    switch (piece.type){
                        case ROOK: simPieces.add(new Rook(activeP.col,activeP.row,currentColor));
                        break;
                        case BISHOP:simPieces.add(new Bishop(activeP.col,activeP.row,currentColor));
                        break;
                        case KNIGHT:simPieces.add(new Knight(activeP.col,activeP.row,currentColor));
                        break;
                        case QUEEN:simPieces.add(new Queen(activeP.col,activeP.row,currentColor));
                        break;
                        default:break;
                    }
                    simPieces.remove(activeP.getIndex());
                    copyPieces(simPieces,pieces);
                    activeP = null;
                    promotion = false;
                    changePlayer();
                }
            }
        }

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D) graphics;
        board.draw(g2);
        for (Piece p : simPieces) {
            p.draw(g2);
        }

        if (activeP != null) {
            if (canMove) {
                if (isIllegal(activeP)) {
                    g2.setColor(Color.YELLOW);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                    g2.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                } else {
                    g2.setColor(Color.white);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                    g2.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }
            }
            activeP.draw(g2);

        }




        //g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Hack Nerd Font", Font.PLAIN,30));
        g2.setColor(Color.white);



        if(promotion){
            g2.drawString("Promote to :",840,150);

            for (Piece p :promoPieces){
                //g2.drawImage(p.image,p.getX(p.col),p.getY(p.row),Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
                //System.out.println("drawing pieces");

                p.draw(g2);
                //System.out.println("after");
            }
//
        }else{
            if(currentColor == WHITE){
                g2.drawString("White's Turn",840,550);
            }else {
                g2.drawString("Black's Turn", 840 , 250);
            }
        }

    }

}
