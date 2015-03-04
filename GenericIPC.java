public class GenericIPC {
    public GenericIPC() {
        String message;
    }

    public static void main(String[] args) {
    }

    public String toString() {
        return message;
    }
}
// IPC messages specification
//
// CLIENT
// Extensions: SetName, PlaysCard, InitSwap, Abort
// SetName: "name" e.g. "Max"
// PlaysCard: "index" + "count" e.g. "02"
// InitSwap: "indexOneToPutInFaceups" + "indexTwoToPutInFaceups" + "indexThreeToPutInFaceups" e.g. "513"
// Abort: "abort"
//
// CONTROLLER
// Extensions: AddsToHand, RequestPlayFromHand, RequestPlayFromFaceups, PlaysFacedown, GameOver, Initialize
// AddsToHand: "pickup:" + "|card.getShortName()|" for card in cards e.g. "pickup:|3s|2d|Kh|"
// RequestPlayFromHand: "play:hand"
// RequestPlayFromFaceups: "play:faceups"
// PlaysFacedown: "play:facedown:|card.getShortName()|" e.g. "play:facedown:|10c|"
// GameOver: "winner" + "player.name" e.g. "winner:Max"
// Initialize: "init:" + TODO
