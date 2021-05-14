package battleship;

import java.io.IOException;

public class Field {

    private static final char[] COLUMN_NAMES ={'A','B','C','D','E','F','G','H','I','J'};
    private static final int FIELD_SIZE = 10;
    FieldState[][] field;
    private int aliveShipPieces;

    public Field() {
        field = new FieldState[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = FieldState.FOG_OF_WAR;
            }
        }
        aliveShipPieces = 0;
    }

    private int convertCoordinateByLetter(char c) throws Exception {
        for (int i = 0; i < FIELD_SIZE; i++)
            if (COLUMN_NAMES[i] == c) { return i; }
        throw new Exception("Error! You entered the wrong coordinates!");
    }

    private int validateXCoordinate(int x) throws Exception {
        if ((x < 0) || (x >= FIELD_SIZE)) {
            throw new Exception("Error! You entered the wrong coordinates!");
        }
        return x;
    }

    private boolean setShip(Ship ship, int x1, int y1, int x2, int y2) throws Exception {
        if (y1==y2) {
            if (Math.abs(x2-x1) != ship.length-1) {
                throw new Exception(String.format("Error! Wrong length of the %s!", ship.name));
            }
            checkArea(x1-1, x2+1, y1-1, y1+1);
            putShipToField(x1, y1, x2, y2);
        } else if (x1==x2) {
            if (Math.abs(y2-y1) != ship.length-1) {
                throw new Exception(String.format("Error! Wrong length of the %s!", ship.name));
            }
            checkArea(x1-1, x1+1, y1-1, y2+1);
            putShipToField(x1, y1, x2, y2);
        } else {
            throw new Exception("Error! Wrong ship location!");
        }
        return true;
    }

    private void putShipToField(int x1, int y1, int x2, int y2) {
        for (int i = ((x1 < 0) ? 0 : x1); i <= x2; i++ ){
            for (int j = ((y1 < 0) ? 0 : y1); j <= y2; j++ ) {
                field[i][j]=FieldState.MY_SHIP;
            }
        }
    }

    private void checkArea(int x1, int x2, int y1, int y2) throws Exception {
        for (int i = ((x1 < 0) ? 0 : x1); i <= ((x2 > (FIELD_SIZE - 1)) ? (FIELD_SIZE - 1) : x2); i++ ){
            for (int j = ((y1 < 0) ? 0 : y1); j <= ((y2 > (FIELD_SIZE - 1)) ? (FIELD_SIZE - 1) : y2); j++ ) {
                if (!checkCellIsAvailable(i, j)) {
                    throw new Exception("Error! You placed it too close to another one.");
                }
            }
        }
    }

    private boolean checkCellIsAvailable(int x, int y) {
        return field[x][y] == FieldState.FOG_OF_WAR;
    }

    private boolean isThisShipAlive(int x, int y) {
        return((field[((x-1) < 0) ? 0 : (x-1)][y] == FieldState.MY_SHIP)
                || (field[((x+1) > (FIELD_SIZE - 1)) ? (FIELD_SIZE - 1) : (x+1)][y] == FieldState.MY_SHIP)
                || (field[x][((y+1) > (FIELD_SIZE - 1)) ? (FIELD_SIZE - 1) : (y+1)] == FieldState.MY_SHIP)
                || (field[x][((y-1) < 0) ? 0 : (y-1)] == FieldState.MY_SHIP));
    }

    public void print(boolean hideShips) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int j = 0; j < FIELD_SIZE; j++) {
            System.out.print(COLUMN_NAMES[j]);
            for (int i = 0; i < FIELD_SIZE; i++) {

                System.out.print(" " +
                        ((hideShips && field[i][j] == FieldState.MY_SHIP)
                                ? FieldState.FOG_OF_WAR.symbol
                                : field[i][j].symbol));
            }
            System.out.println();
        }
    }

    public void setShip(String[] coors, Ship ship) throws Exception {
        int x1 = 0,y1 = 0,x2 = 0,y2 = 0;
        try {
            y1 = convertCoordinateByLetter(coors[0].charAt(0));
            x1 = validateXCoordinate(Integer.parseInt(coors[0].substring(1)) - 1);
            y2 = convertCoordinateByLetter(coors[1].charAt(0));
            x2 = validateXCoordinate(Integer.parseInt(coors[1].substring(1)) - 1);
        } catch ( IOException ex) {
            System.out.println("Something went wrong in scanning new line");
        }
                        catch (Exception e) {
            throw new Exception("Error in coordinates!");
        }
        if ((x1 < x2) || (y1 < y2)) {
            setShip(ship, x1, y1, x2, y2);
        } else {
            setShip(ship, x2, y2, x1, y1);
        }
        aliveShipPieces += ship.length;
    }

    public void checkShoot(String str) throws Exception {
        int y = convertCoordinateByLetter(str.charAt(0));
        int x = validateXCoordinate(Integer.parseInt(str.substring(1)) - 1);
        if (field[x][y] == FieldState.MY_SHIP) {
            field[x][y] = FieldState.HIT_SHIP;
            if (isThisShipAlive(x,y)) {
                System.out.println("You hit a ship!");
            } else {
                System.out.println("You sank a ship! Specify a new target in next round");
            }
            aliveShipPieces -= 1;
        } else if (field[x][y] == FieldState.HIT_SHIP) {
            if (isThisShipAlive(x,y)) {
                System.out.println("You hit a ship! Try again:");
            } else {
                System.out.println("You sank a ship! Specify a new target:");
            }
        } else {
            field[x][y] = FieldState.MISSED_SHOT;
            System.out.println("You missed!");
        }
    }

    public int getAliveShipPieces() {
        return aliveShipPieces;
    }


}
