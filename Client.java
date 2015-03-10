public class Client {
    
    public static void main(String[] args) {
    }
    
    public Client() {
    }

    public getConfig() {
        int h = getHumanCount();
        int c = getCPUCount();
        String m = h;
        m += c;
        return new ClientConfigIPC(m);
    }
}
