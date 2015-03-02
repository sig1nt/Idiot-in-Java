public class GenericClientIPC {
    public GenericClientIPC() {
        String message;
    }

    public static void main(String[] args) {
    }

    public String toString() {
        return message;
    }
}

// Extensions: SetName, PlaysCard, InitSwap, Abort
// SetName: "name" e.g. "Max"
// PlaysCard: "index" + "count" e.g. "02"
// InitSwap: "indexOneToPutInFaceups" + "indexTwoToPutInFaceups" + "indexThreeToPutInFaceups" e.g. "513"
// Abort: "abort"
