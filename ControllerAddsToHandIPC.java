import java.util.ArrayList;

public class ControllerAddsToHandIPC extends GenericIPC {
    public static void main(String[] args) {
    }

    public ControllerAddsToHandIPC(ArrayList<Card> cards) {
        message = "pickup:|";
        for (card : cards) {
            message += card.getShortName();
            message += "|";
        }
    }
}
