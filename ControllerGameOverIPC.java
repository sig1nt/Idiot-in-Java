public class ControllerGameOverIPC extends GenericIPC {
    public static void main(String[] args) {
    }

    public ControllerGameOverIPC(String winner) {
        message = "winner:";
        message += winner.name;
    }
}
