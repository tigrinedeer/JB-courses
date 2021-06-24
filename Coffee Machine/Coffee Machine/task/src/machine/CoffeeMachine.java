package machine;
import java.util.*;

public class CoffeeMachine {

    int milk;
    int water;
    int coffee;
    int cups;
    int money;
    State state;
    int fillState;

    enum State {
        CHOOSE_ACTION,
        FILL_MASHINE,
        CHOOSE_COFFEE_TYPE;
    }

    public CoffeeMachine() {
        milk = 540;
        water = 400;
        coffee = 120;
        cups = 9;
        money = 550;
        changeStateChooseAction();
    }

    private void changeStateChooseAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        state = State.CHOOSE_ACTION;
    }

    private void changeStateChooseCoffeeType(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        state = State.CHOOSE_COFFEE_TYPE;
    }

    private void changeStateFillMachine() {
        state = State.FILL_MASHINE;
        fillState = 0;
        System.out.println("Write how many ml of water do you want to add: ");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        String action;
        do {
            action = scanner.nextLine();
            coffeeMachine.input(action);
        } while (!action.equals("exit") );
    }


    public void input(String message) {
        if (state == State.CHOOSE_ACTION) {
            choose_action(message);
        } else if (state == State.CHOOSE_COFFEE_TYPE) {
            buy(message);
        } else if (state == State.FILL_MASHINE) {
            fill(message);
        }
    }

    private void choose_action(String action) {
        switch (action) {
            case "buy": {
                changeStateChooseCoffeeType();
                break;
            }
            case "fill": {
                changeStateFillMachine();
                break;
            }
            case "take": {
                takeMoney();
                break;
            }
            case "remaining": {
                showInfo();
                break;
            }
            default: {
                break;
            }
        }
    }

    private void buy(String coffeeType) {
        switch (coffeeType) {
            case "1": {
                makeCoffee(250, 0, 16, 4);
                break;
            }
            case "2": {
                makeCoffee(350, 75, 20, 7);
                break;
            }
            case "3": {
                makeCoffee(200, 100, 12, 6);
                break;
            }
            case "back": {
                break;
            }
            default: {
                System.out.println("Not available type :(");
                break;
            }
        }
        changeStateChooseAction();
    }

    private void makeCoffee(int waterPerCup, int milkPerCup, int coffeePerCup, int moneyPerCup) {
        if (water < waterPerCup) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < milkPerCup) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffee < coffeePerCup) {
            System.out.println("Sorry, not enough coffee!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterPerCup;
            milk -= milkPerCup;
            coffee -= coffeePerCup;
            money += moneyPerCup;
            cups -= 1;
        }
    }

    private void showInfo() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffee + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");
    }

    private void takeMoney() {
        System.out.println("I gave you $" + money);
        money -= money;
    }

    private void fill(String fillAmount) {
        int amount = Integer.valueOf(fillAmount);
        state = State.FILL_MASHINE;
        switch (fillState) {
            case 0: {
                water += amount;
                System.out.println("Write how many ml of milk do you want to add: ");
                break;
            }
            case 1: {
                milk += amount;
                System.out.println("Write how many grams of coffee beans do you want to add: ");
                break;
            }
            case 2: {
                coffee += amount;
                System.out.println("Write how many disposable cups of coffee do you want to add: ");
                break;
            }
            case 3: {
                cups += amount;
                changeStateChooseAction();
                break;
            }
            default: {
                changeStateChooseAction();
                break;
            }
        }
        fillState += 1;
    }

}
