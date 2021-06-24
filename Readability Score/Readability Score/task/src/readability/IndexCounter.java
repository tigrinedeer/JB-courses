package readability;

public abstract class IndexCounter {

    TextCounter textCounter;
    String name = "";
    double score = 0;
    int age1 = 0, age2 = 0;


    IndexCounter(TextCounter textCounter) {
        this.textCounter = textCounter;
    }

    public abstract double countScore();

    public void calculateAge() {
        switch ((int) Math.ceil(score)) {
            case 1: { age1 = 5; age2 = 6; break; }
            case 2: { age1 = 6; age2 = 7; break; }
            case 3: { age1 = 7; age2 = 9; break; }
            case 4: { age1 = 9; age2 = 10; break; }
            case 5: { age1 = 10; age2 = 11; break; }
            case 6: { age1 = 11; age2 = 12; break; }
            case 7: { age1 = 12; age2 = 13; break; }
            case 8: { age1 = 13; age2 = 14; break; }
            case 9: { age1 = 14; age2 = 15; break; }
            case 10: { age1 = 15; age2 = 16; break; }
            case 11: { age1 = 16; age2 = 17; break; }
            case 12: { age1 = 17; age2 = 18; break; }
            case 13: { age1 = 18; age2 = 24; break; }
            case 14: { age1 = 24; age2 = 100; break; }
            default: {break;}
        }
    }

    public IndexCounter generateResult() {
        countScore();
        calculateAge();
        printResult();
        return this;
    }

    public void printResult() {
        System.out.println(
                String.format("%s: %.2f (about %d-year-olds)", name,score, age1));

    }
}
