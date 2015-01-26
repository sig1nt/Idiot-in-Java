//implementation of the CPU class

public class CPU extends Player{
    private AI ai;
    
    public CPU(){
        isHuman = false;
        numCPUs++;
        ai = new AI();
    }
    
    public int move(Pile currP, Deck d, ){
        if(canMove()){
            if(ai.canCompleteFour()){
                
            }else if(
        }
    }
}
