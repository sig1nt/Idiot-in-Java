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
    
    public String getShortName(){
        String s = "";
        if(value < 10){
            s += "0" + value;
        }else if(value == 10){
            s += value;
        }else{
            s += faces[value - 11];
        }
        s += suit.substring(0, 1);
        return s;
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
