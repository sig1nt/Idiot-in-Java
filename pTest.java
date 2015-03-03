import processing.core.*;

public class pTest extends PApplet{

    int onSelect = 0;

    public static void main(String[] args)  {
        new Thread(new GameLogic()).start();
        PApplet.main(new String[] { "--present", "pTest" });
    }
    
    public void setup(){
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
	      rect(100,525+onSelect,150,200);
        rect(550,525,150,200);
        rect(325,525,150,200);

        rect(100,50,150,200);
        rect(550,50,150,200);
        rect(325,50,150,200);

        rect(300,300,200,150);
    }
    
    static class GameLogic implements Runnable{
        public void run(){
            new Game().play();
        }
    }
}
