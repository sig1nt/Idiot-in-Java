//creates a Pile class to us with played cards

public class Pile{
    private Card [] p;
    private int index;
    
    public Pile(){
        p = new Card[52];
        index = 0;
    }
    
    public Pile(int i){
        index = i;
    }
    
    public void add(Card c){
        p[index] = c.clone();
        index++;
    }
    
    public Card[] seeThree(){
        Card[] topThree = new Card[3];
        for(int i = index; i > index - 3; i--){
            topThree[index - i] = p[i];
        }
        return topThree;
    }
    
    public void clear(){
        p = new Card[52];
        index = 0;
    }
    
    public Card[] pickup(){
        Card[] temp = p.clone();
        clear();
        return temp;
    }
    
    public boolean validMove(Card c){
        if(c.value == 2 || c.value == 10){
            return true;
        }else if(p[index].value == 7 && c.value <= 7){
            return true;
        }else if(c.value >= p[index].value){
            return true;
        }else{
            return false;
        }
    }
}