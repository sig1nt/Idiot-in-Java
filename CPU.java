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
        if(canMove(currP)){
            Card c;
            int index, numPlayable = 1;
            if(!hand.isEmpty() || !faceup.isEmpty()){
                c = ai.logical(currP, facedown.size());
                if(!hand.isEmpty()){
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
                    while(hand.size() < 3 && !d.isEmpty()){
                        hand.add(d.draw());
                    }
                    sort(hand);
                }else{
                    index = getIndex(c, faceup);
                    int currVal = c.value;
                    if(c.value != 2 && c.value != 10){
                        for(int i = index + 1; i < index + 3; i++){
                            if(i != faceup.size() && faceup.get(i).value == currVal){
                                numPlayable++;
                            }else{
                                break;
                            }
                        }
                    }
                    for(int i = 0; i < numPlayable; i++){
                        currP.add(faceup.get(index));
                        faceup.remove(index);
                    }
                }
            }else{
                if(currP.validMove(facedown.get(0))){
                    c = facedown.get(0);
					System.out.println("Playing " + facedown.get(0) + " from facedowns");
                    currP.add(c);
                    facedown.remove(0);
                    if(hand.isEmpty() && facedown.isEmpty()){
                        return 2;
                    }
                }else{
                    currP.add(facedown.get(0));
					System.out.println("Playing " + facedown.get(0) + " from facedowns");
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
    
    public void swapHand(){
        ArrayList<Card> temp = ai.strategizeFaceups();
        hand.clear();
        faceup.clear();
        hand.ensureCapacity(3);
        faceup.ensureCapacity(3);
        hand.addAll(temp.subList(0, 3));
        faceup.addAll(temp.subList(3, 6));
    }
    
    public static void main(String[]args){
        Deck d = new Deck();
        ArrayList<Player> playas = new ArrayList<Player>();
        playas.add(new Player(true, "Kent", d));
        CPU comp = new CPU("CPU-Test", d, playas);
        playas.add(comp);
        Pile p = new Pile();
        p.add(d.draw());
        while(!d.isEmpty()){
            comp.move(p, d);
            p.add(d.draw());
        }
    }
}
