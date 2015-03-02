public class ClientInitSwapIPC extends GenericIPC {
    public static void main(String[] args) {
    }

    public ClientInitSwapIPC(int indexOne, int indexTwo, int indexThree) {
        message = (String) indexOne + (String) indexTwo + (String) indexThree;
    }
}
