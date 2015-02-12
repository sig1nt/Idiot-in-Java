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
        sort(hand);
        sort(faceup);
    }
    
    public int move(Pile currP, Deck d){
        cio.type(hand);
        if(canMove(currP)){
            Card c = ai.logical(currP, facedown.size());
            int index, numPlayable = 1;
            if(!(hand.get(0) == null) || !(faceup.get(0) == null)){
                if(!(hand.get(0) == null)){
                    index = getIndex(c, hand);
                    int currVal = c.value;
                    if(c.value != 2 && c.value != 10){
                        for(int i = index + 1; i < index + 3; i++){
                            if(i != hand.size() && hand.get(i).value == currVal){
                                numPlayable++;
                            }else{
                                break;
                            }
                        }
                    }
                    for(int i = 0; i < numPlayable; i++){
                        currP.add(hand.get(index));
                        hand.remove(index);
                    }
                    while(hand.size() < 3 && !d.empty()){
                        hand.add(d.draw());
                    }
                    sort(hand);
                }else{
                    index = getIndex(c, faceup);
                    if(c.value != 2 && c.value != 10){
                        for(Card currC: faceup){
                            if(currC.value == c.value){
                                numPlayable++;
                            }
                        }
                    }
                    for(int i = 0; i < numPlayable; i++){
                        currP.add(faceup.get(index));
                        faceup.remove(0);
                    }
                    cio.typeln(currP);
                }
            }else{
                if(currP.validMove(facedown.get(0))){
                    currP.add(facedown.get(0));
                    facedown.remove(0);
                    if(hand.isEmpty() && facedown.isEmpty()){
                        return 2;
                    }
                }else{
                    currP.add(facedown.get(0));
                    facedown.remove(0);
                    hand.addAll(currP.pickup());
                    cio.typeln(name + " picked up pile");
                    sort(hand);
                    return 0;
                }
            }
            cio.typeln("Played " + numPlayable + " " + c.value + "s");
            if(hand.isEmpty() && facedown.isEmpty()){
                return 2;
            }
            if(currP.checkTop().value != 10 && !currP.fourKind()){
                return 0;
            }else{
                currP.clear();
                return 1;
            }
        }else{
            hand.addAll(currP.pickup());
            cio.typeln(name + " picked up pile");
            sort(hand);
            return 0;
        }
    }
    
    public int getIndex(Card c, ArrayList<Card> options){
        int index = 0;
        for(Card hCard: options){
            if(hCard.equals(c)){
                return index;
            }
            index++;
        }
        return -1;
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
