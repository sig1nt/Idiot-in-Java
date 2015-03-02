public class ClientPlaysCardIPC extends GenericClientIPC {
    public static void main(String[] args) {
    }

    public ClientPlaysCardIPC(String source, int index, int count) {
            message = source + (String) index + (String) count;
    }
}
