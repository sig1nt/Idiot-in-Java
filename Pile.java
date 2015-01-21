//creates a Pile class to us with played cards

public class Pile{
    Card [] p;
    int index;
    
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
}