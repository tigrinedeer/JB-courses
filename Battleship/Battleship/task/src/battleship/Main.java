package battleship;

import java.util.Scanner;

public class Main {

    static Gamer activePlayer;
    static Gamer enemy;
    static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        activePlayer = new Gamer("Player 1", scanner);
        enemy = new Gamer("Player 2", scanner);

        activePlayer.setShips();
        SwapPlayers();
        activePlayer.setShips();

        System.out.println("The game starts!");

        do {
            SwapPlayers();
            enemy.print(true);
            System.out.println("--------------------");
            activePlayer.print(false);
            System.out.println(activePlayer.name + ", it's your turn!");
            enemy.getAHit();
        } while (activePlayer.isAlive() && enemy.isAlive());
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private static void SwapPlayers() {
        Gamer swap = activePlayer;
        activePlayer = enemy;
        enemy = swap;
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }


}
