import java.util.ArrayList;

public class ControllerInitializeIPC extends GenericIPC {
    public static void main(String[] args) {
    }

    public ControllerInitializeIPC(ArrayList<Player> humans, ArrayList<Player> cpus, ArrayList<Card> hand, ArrayList<Card> faceups) {
        message = "init:";
        message += cpus.length();
        message += "|";
        for (cpu : cpus) {
            message += "|"
            for (card : cpu.faceup) {
                message += card.getShortName() + "|";
            }
        }
        message += "|";
        message += humans.length();
        message += "|";
        for (human : humans) {
            message += "|";
            for (card : human.faceup) {
                message += card.getShortName() + "|";
            }
        }
        message += "|";
        for (card : hand) {
            message += card.getShortName() + "|";
        }
        for (card : faceups) {
            message += card.getShortName() + "|";
        }
        message += "|";
    }
}
