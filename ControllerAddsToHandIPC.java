import java.util.ArrayList;

public class ControllerAddsToHandIPC extends GenericControllerIPC {
    public static void main(String[] args) {
    }

    public ControllerAddsToHandIPC(ArrayList<Card> cards) {
        for (card : cards) {
            message += card.getShortName();
            message += "|";
        }
    }
}
