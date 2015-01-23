//creates a Pile class to us with played cards

import java.util.*;

public class Pile{
    private ArrayList<Card> p;
    
    public Pile(){
        p = new ArrayList<Card>();
    }
    
    public void add(Card c){
       p.add(c.clone());
    }
    
    public ArrayList<Card> seeThree(){
        if(p.size() <= 3){
            return p;
        }else{
            return (ArrayList<Card>)p.subList(p.size() - 4, p.size() - 1);
        }
    }
    
    public void clear(){
        p = new ArrayList<Card>();
    }
    
    public ArrayList<Card> pickup(){
        ArrayList<Card> temp = p;
        clear();
        return temp;
    }
    
    public Card checkTop(){
        return p.get(p.size() - 1);
    }
    
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
    
    public String toString(){
        String s = "";
        for(int i = 0; i < p.size(); i++){
            s += p.get(i).getShortName() + "\n";
        }
        return s;
    }
}
