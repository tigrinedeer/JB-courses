package battleship;

import java.util.Scanner;

public class Gamer {

    Field field;
    Ship[] ships;
    String name;
    Scanner scanner;

    public Gamer(String name, Scanner scanner) {
      field = new Field();
      ships = Ship.values();
      this.scanner = scanner;
      this.name = name;
    }

    public void setShips() {
        System.out.println(name + ", place your ships on the game field");
        field.print(false);
        boolean retry;
        for (Ship ship:ships) {
            System.out.println(String.format("Enter the coordinates of the %s (%d cells):", ship.name, ship.length));
            do {
                retry = false;
                try {
                    String str = scanner.nextLine();
                    String[] coors = str.split(" ");
                    field.setShip(coors, ship);
                    field.print(false);
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " Try again:");
                    retry = true;
                }
            } while (retry);
        }
    }

    public void print(boolean hideShips) {
        field.print(hideShips);
    }

    public void getAHit() {
        boolean retry;
        do {
            retry = false;
            try {
                String str = scanner.nextLine();
                field.checkShoot(str);
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Try again:");
                retry = true;
            }
        } while (retry);
    }

    public boolean isAlive() {
        return (field.getAliveShipPieces() > 0);
    }
}
