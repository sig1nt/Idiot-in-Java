//implementation of the CPU class

public class CPU extends Player{
    private AI ai;
    
    public CPU(String n, Deck d){
        isHuman = false;
        numCPUs++;
        ai = new AI();
        name = n;
        for(int i = 0; i < 3; i++){
            facedown.add(d.draw());
            faceup.add(d.draw());
            hand.add(d.draw());
        }
        sortHand();
    }
    
    public int move(Pile currP, Deck d, ){
        if(canMove()){
            int value = ai.logical(currP, facedown.size()), index;
            if(value == 100){
                index = hand.indexOf(currP.checkTop());
            }else{
                index = hand.indexOf(value);
            }
            numPlayable = 0;
            for(int i = index; i < index + 3; i++){
                if((int)hand.get(i) == (int)hand.get(index)){
                    numPlayable++;
                }else{
                    break;
                }
            }
            for(int i = 0; i < numPlay; i++){
                currP.add(hand.get(index - 1));
            }
            for(int i = 0; i < numPlay; i++){
                hand.remove(index - 1);
            }while(hand.size() < 3){
                hand.add(d.draw());
            }
            sortHand();
            cio.type("Played");
            if(hand.isEmpty() && facedown.isEmpty()){
                return 2;
            }
            if(c.value != 10 && !currP.fourKind()){
                return 0;
            }else{
                return 1;
            }
        }else{
            hand.addAll(currP.pickup());
            cio.type("Picked up pile");
            sortHand();
            return 0;
        }
    }
}
