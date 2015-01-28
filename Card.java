public class Card{
    protected String suit, faces[] = {"Jack", "Queen", "King", "Ace"};
    protected int value;
    
    //Default constructor - DO NOT USE
    public Card(){
        suit = "";
    }
    
    /*USE THIS NOT DEFAULT
      creates a Card with a suit and a value
    */
    public Card(String s, int v){
        suit = s;
        value = v;
    }
    
    //Sets the suit of the card
    public void setSuit(String s){
        suit = s;
    }
    
    //returns a 3 char name of the card where the first two are the card's number value or the first letter of the face and the last char is the first letter ofd the suit
    public String getShortName(){
        String s = "";
        if(value < 10){
            s += "0" + value;
        }else if(value == 10){
            s += value;
        }else{
            s += " " + faces[value - 11].substring(0, 1);
        }
        s += suit.substring(0, 1);
        return s;
    }
    
    //returns a copy of the card in question
    public Card clone(){
        return new Card(suit, value);
    }
    
    public boolean equals(Object other){
        if(other == this) return true;
        if(!(other instanceof Card) return false;
        Card oCard = (Card)other;
        if(oCard.value != value) return false;
        if(!oCard.suit.equals(suit) return false;
        return true;
    }
    
    //returns a string in the form "__value__ of __suit__"
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