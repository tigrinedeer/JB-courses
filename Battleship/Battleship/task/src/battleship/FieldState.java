package battleship;

public enum FieldState {
    FOG_OF_WAR("~",0),
    MY_SHIP("O",1),
    HIT_SHIP("X", 2),
    MISSED_SHOT("M",3);

    String symbol;
    int value;

    FieldState(String smbl, int value) {
        symbol = smbl;
        this.value = value;
    }



}
