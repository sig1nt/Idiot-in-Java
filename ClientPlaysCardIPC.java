public class ClientPlaysCardIPC extends GenericIPC {
    public static void main(String[] args) {
    }

    public ClientPlaysCardIPC(int index, int count) {
        message = index + count;
    }
}