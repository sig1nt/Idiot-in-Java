import java.util.*;

public class Deck{
  private Stack<Card> sDeck;
  protected Card oDeck[];
  
  //creates a standard deck of cards
  public Deck(){
    oDeck = new Card[52];
    int cardsInDeck = 0;
    String types[] = {"Clubs", "Diamonds", "Hearts", "Spades"};
    int vals[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    for(int val: vals){
      for(String type: types){
        oDeck[cardsInDeck] = new Card(type, val);
        cardsInDeck++;
      }
    }
    shuffle();
  }
  
  //Pseudorandomly assigns positions to the cards and places them on the stack
  public void shuffle(){
    Card tDeck[] = oDeck.clone();
    sDeck = new Stack<Card>();
    for(int i = 0; i < 3; i++){
      Random r = new Random();
      for(int j = 0; j < oDeck.length; j++){
        int index = r.nextInt(52);
        Card c = tDeck[j].clone();
        tDeck[j] = tDeck[index].clone();
        tDeck[index] = c;
      }
    }
    for(Card c: tDeck){
      sDeck.push(c);
    }
  }
  
  //returns a new card from the stack and removes it from the stack
  public Card draw(){
    if(!sDeck.empty()){
      return sDeck.pop();
    }else{
      return null;
    }
  }
  
  //returns true if the stack is empty and false if it is not
  public boolean empty(){
    return sDeck.empty();
  }
  
  //for testing only
  public static void main(String[]args){
    Deck myDeck = new Deck();
    do{
      System.out.println(myDeck.draw());
    }while(!myDeck.empty());
  }
}