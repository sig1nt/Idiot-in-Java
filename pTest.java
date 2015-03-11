import processing.core.*;

public class pTest extends PApplet{

    int onSelect = 0;
    GameSingleton daGame;
    DrawableCard[] myCards = new DrawableCard[3];
    DrawableCard[] yourCards = new DrawableCard[3];
    DrawablePile pile;
    
    public static void main(String[] args)  {
        PApplet.main(new String[] { "--present", "pTest" });
    }
    
    public void setup(){
        daGame = new GameSingleton();
        new Thread(new GameLogic()).start();
        for(int i = 0; i < 3; i++){
            myCards[i]= new DrawableCard(this,100+(225*i),525,"Spades",12,true);
        }
        for(int i = 0; i < 3; i++){
            yourCards[i]= new DrawableCard(this,100+(225*i),50,"Spades",12,false);
        }
        pile = new DrawablePile(this,300,300);
        noStroke();
        size(800,800);
        background(25,77,30);
        fill(250,164,43);
        rect(25,25,750,725);
    }
    
    public void draw(){
        fill(25,77,30);
        rect(50,50,700,675);
        onSelect = (mouseX>100 && mouseX < 250 && mouseY >525 && mouseY < 725)?-20:0;
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
    
    class GameLogic implements Runnable{
        public void run(){
            daGame.getGame().play();
        }
    }
}