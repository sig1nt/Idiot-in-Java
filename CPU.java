//implementation of the CPU class

import java.util.ArrayList;

public class CPU extends Player{
    private AI ai;
    
    public CPU(String n, Deck d, ArrayList<Player> players){
        human = false;
        numCPUs++;
        ai = new AI(hand, faceup, players);
        name = n;
        for(int i = 0; i < 3; i++){
            facedown.add(d.draw());
            faceup.add(d.draw());
            hand.add(d.draw());
        }
        sortHand();
    }
    
    public int move(Pile currP, Deck d){
        if(canMove(currP)){
            int value = ai.logical(currP, facedown.size()), index;
            if(value == 100){
                index = hand.indexOf(currP.checkTop());
            }else{
                index = hand.indexOf(value);
            }
            int numPlayable = 0;
            if(value != 2 && value != 10 && value != 100){
                for(int i = index; i < index + 3; i++){
                    if(hand.get(i).value == hand.get(index).value){
                        numPlayable++;
                    }else{
                        break;
                    }
                }
            }
            for(int i = 0; i < numPlayable; i++){
                currP.add(hand.get(index - 1));
            }
            for(int i = 0; i < numPlayable; i++){
                hand.remove(index - 1);
            }while(hand.size() < 3){
                hand.add(d.draw());
            }
            sortHand();
            cio.type("Played" + numPlayable + " " + value + "s");
            if(hand.isEmpty() && facedown.isEmpty()){
                return 2;
            }
            if(currP.checkTop().value != 10 && !currP.fourKind()){
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
    
    public static void main(String[]args){
        Deck d = new Deck();
        ArrayList<Player> playas = new ArrayList<Player>();
        playas.add(new Player(true, "Kent", d));
        CPU comp = new CPU("CPU-Test", d, playas);
        playas.add(comp);
        Pile p = new Pile();
        p.add(d.draw());
        while(!d.empty()){
            comp.move(p, d);
            p.add(d.draw());
        }
    }
}
