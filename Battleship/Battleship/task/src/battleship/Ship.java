package battleship;

public enum Ship {
    AIRCRAFT_CARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUSIER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    int length;
    String name;

    Ship(int length, String name) {
        this.length = length;
        this.name = name;
    }

}
