public class ClientSetNameIPC extends GenericIPC {
    public static void main(String[] args) {
    }

    public ClientSetNameIPC(String name) {
        message = name;
    }
}
