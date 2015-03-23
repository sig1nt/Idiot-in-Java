import processing.core.*;
import java.util.ArrayList;

public class pTest extends PApplet{

    // TODO(): Do it real
    public static Thread theThread; 

    GameSingleton daGame;
    ArrayList<DrawableCard> myCards;
    ArrayList<DrawableCard> yourCards;
    DrawablePile pile;
    
    public static void main(String[] args)  {
        PApplet.main(new String[] { "--present", "pTest" });
    }
    
    public void setup(){
        daGame = new GameSingleton();
        Thread b = new Thread(new GameLogic());

        // TEST
        theThread = Thread.currentThread();

	// TEST
	System.err.println("Graphics, before start");

        b.start();

	// TEST
	System.err.println("Graphics, after start");

	// TEST
        // waiting(b);
	// waiting(theThread);
/*
	try {
            wait();
	} catch (Exception ex) {
            ex.printStackTrace();
        }	
*/
	// TEST
	System.err.println("Graphics, after wait");

        myCards = new ArrayList<DrawableCard>();
        yourCards = new ArrayList<DrawableCard>();
        for(int i = 0; i < 3; i++){
            yourCards.add(new DrawableCard(this,100+(225*i),50,new Card("Spades", 12),false));
        }
        pile = new DrawablePile(this,300,300,daGame.getGame().pile);
        noStroke();
        size(800,800);
        background(25,77,30);
        fill(250,164,43);
        rect(25,25,750,725);
    }
    
    public void draw(){
	if(daGame.getGame().players != null && daGame.getGame().players.size() != 0 && daGame.getGame().players.get(0).hand.size() != 0){
            System.out.println("Printing stuff");
	    myCards = converter(daGame.getGame().players.get(0).hand, 525,true);
	    fill(25,77,30);
	    rect(50,50,700,675);
	    stroke(1);
	    fill(255);
	    pile.draw();

	    for(DrawableCard c : myCards){
		c.draw();
	    }

	    for(DrawableCard c : yourCards){
		c.draw();
	    }
	}
    }

    public void waiting(Object w) {
	System.out.println("Waiting");
	try{
	    while(true){
		w.wait();
		// TEST
		System.err.println("Post Wait, try");
	    }
	} catch(InterruptedException e) {
	    // TEST
	    System.err.println("Catch");
	}
    }



    public ArrayList<DrawableCard> converter(ArrayList<Card> input, int location, boolean mine){
        ArrayList<DrawableCard> d = new ArrayList<DrawableCard>();
        int cardWidth = 600/input.size();
        for(int i = 0; i < input.size(); i++){
            d.add(new DrawableCard(this,100+(cardWidth*i),location,input.get(i),mine));
        }
        return d;
    }
    
    class GameLogic implements Runnable{
        public void run(){
            daGame.getGame().play();
        }
    }
}
