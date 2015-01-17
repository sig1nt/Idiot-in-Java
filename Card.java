public class Card implements Cloneable{
  protected String value;
  
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
    return value;
  }
}
