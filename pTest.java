import processing.core.*;

public class pTest extends PApplet{
    public static void main(String[] args)  {
        PApplet.main(new String[] { "--present", "pTest" });
    }
    
    public void setup(){
        size(400,400);
        new Thread(new GameLogic()).run();
    }
    
    public void draw(){
    }
    
    class GameLogic implements Runnable{
        void run(){
            new Game().play();
        }
    }
}
