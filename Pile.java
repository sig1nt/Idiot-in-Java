//creates a Pile class to us with played cards

import java.util.*;

public class Pile{
    private ArrayList<Card> p;
    
    //Creates a new Pile with ArrayList p
    public Pile(){
        p = new ArrayList<Card>();
    }
    
    //Adds a card to the top of the Pile
    public void add(Card c){
       p.add(c.clone());
    }
    
    //returns the top 3 cards on the pile
    public ArrayList<Card> seeThree(){
        if(p.size() <= 3){
            return p;
        }else{
            return (ArrayList<Card>)p.subList(p.size() - 4, p.size() - 1);
        }
    }
    
    //empties the pile
    public void clear(){
        p = new ArrayList<Card>();
    }
    
    // returns all the cards in the pile and clears the pile
    public ArrayList<Card> pickup(){
        ArrayList<Card> temp = p;
        clear();
        return temp;
    }
    
    //returns top card of the pile
    public Card checkTop(){
        return p.get(p.size() - 1);
    }
    
    //returns true if the top 4 cards are of the same kind and false if they aren't
    public boolean fourKind(){
        if(p.size() < 4){
            return false;
        }else{
            int tVal = checkTop().value;
            for(int i = p.size() - 2; i > p.size() - 5; i--){
                if(tVal != p.get(i).value){
                    return false;
                }
            }
            return true;
        }
    }
    
    //returns true if the selected move is legal and false if it is illegal
    public boolean validMove(Card c){
        if(c.value == 2 || c.value == 10){
            return true;
        }else if(p.get(p.size() - 1).value == 7 && c.value <= 7){
            return true;
        }else if(c.value >= p.get(p.size() - 1).value){
            return true;
        }else{
            return false;
        }
    }
    
    boolean isEmpty(){
        return p.isEmpty();
    }
    
    //Returns a string with the short names of the cards in the pile from top to bottom
    public String toString(){
        String s = "";
        for(int i = 0; i < p.size(); i++){
            s += p.get(i).getShortName() + "\n";
        }
        return s;
    }
}
