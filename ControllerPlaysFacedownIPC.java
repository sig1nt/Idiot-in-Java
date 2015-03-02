public class ControllerPlaysFacedownIPC extends GenericControllerIPC {
    public static void main(String[] args) {
    }

    public ControllerPlaysFacedownIPC(Card card) {
        message += "play:facedown:";
        message += card.getShortName();
    }
}
