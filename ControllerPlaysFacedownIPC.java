public class ControllerPlaysFacedownIPC extends GenericIPC {
    public static void main(String[] args) {
    }

    public ControllerPlaysFacedownIPC(Card card) {
        message = "play:facedown:|";
        message += card.getShortName();
        message += "|";
    }
}
