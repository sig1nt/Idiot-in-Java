public class Card implements Cloneable{
    protected String suit, faces[] = {"Jack", "Queen", "King", "Ace"};
    protected int value;
  
    public Card(){
        suit = "";
    }
  
    public Card(String s){
        suit = s;
    }
    
    public Card(String s, int v){
        suit = s;
        value = v;
    }
  
    public void setValue(String s){
        suit = s;
    }
  
    public Card clone(){
        return new Card(suit, value);
    }
  
    public String toString(){
        String s = "";
        if(value > 10){
            for(int i = 0; i < 5; i++){
                if(i == value - 11){
                    s = faces[i];
                }
            }
        }else{
            s += value;
        }
        return s + " of " + suit;
    }
}
