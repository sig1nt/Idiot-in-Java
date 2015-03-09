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
// Extensions: SetName, Config, PlaysCard, InitSwap, Abort
// SetName: "name" e.g. "Max"
// Config: "cpuCount" + "humanCount" e.g. "12"
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
// GameOver: "winner:" + "player.name" e.g. "winner:Max"
// Initialize: "init:" + "cpuCount|" + "|cpuXfaceup1|cpuXfaceup2|cpuXfaceup3|" for X in range(cpuCount) + "|humanCount|" + "|humanXfaceup1|humanXfaceup2|humanXfaceup3|" for X in range(humanCount)
//  + "||hand1|hand2|hand3|faceup1|faceup2|faceup3||"
//  e.g. "init:2||3s|2h|10c||5d|7c|14h||3||6c|6s|12d||4h|5s|11h||2c|3c|13h||7d|8h|9s|8c|10s|11s||"
