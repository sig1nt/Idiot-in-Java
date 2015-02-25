import java.util.ArrayList;

public class ClientMessage{
    ArrayList<Card> cArr;
    
    public ClientMessage(ArrayList<Card> cards){
        cArr = cards;
    }
    
    public String toString(){
        s = "";
        for(Card c: cArr){
            s += c;
        }
        return s;
    }
}