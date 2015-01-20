public class Card implements Cloneable{
    protected String suit, faces[] = {"Jack", "Queen", "King", "Ace"};
    protected int value;
  
    public Card(){
        value = "";
    }
  
    public Card(String s){
        value = s;
    }
  
    public void setValue(String s){
        value = s;
    }
  
    public Card clone(){
        return new Card(value);
    }
  
    public String toString(){
        String s = "";
        if(value > 10){
            for(itn i = 0; i < 5; i++){
                if(i == value - 11){
                    s = faces[i];
                }
            }
        }else{
            s += value
        }
        return s + " of " + suit;
    }
}
