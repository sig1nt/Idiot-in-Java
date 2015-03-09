public class ClientConfigIPC extends GenericIPC {
    public static void main(String[] args) {
    }

    public ClientConfigIPC(int cpuCount, int humanCount) {
        message = cpuCount + humanCount;
    }
}   
