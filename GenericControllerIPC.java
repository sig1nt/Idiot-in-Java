public class GenericControllerIPC {
    public static void main(String[] args) {
    }

    public GenericControllerIPC() {
        String message;
    }

    public String toString() {
        return message;
    }
}

// Extensions: AddsToHand, RequestPlayFromHand, RequestPlayFromFaceups, PlaysFacedown, GameOver, Initialize
// AddsToHand: "pickup:" + "|card.getShortName()|" for card in cards e.g. "pickup:|3s|2d|Kh|"
// RequestPlayFromHand: "play:hand"
// RequestPlayFromFaceups: "play:faceups"
// PlaysFacedown: "play:facedown:|card.getShortName()|" e.g. "play:facedown:|10c|"
// GameOver: "winner" + "player.name" e.g. "winner:Max"
// Initialize: "init:" + TODO
