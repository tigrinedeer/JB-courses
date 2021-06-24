package readability;

public class SMOGIndexCounter extends IndexCounter{

    private final double COOF_1 = 1.043, COOF_2 = 30, COOF_3 = 3.1291;

    public SMOGIndexCounter(TextCounter textCounter) {
       super(textCounter);
       this.name = "Simple Measure of Gobbledygook";
    }

    public double countScore() {
        score = COOF_1 * Math.pow((textCounter.polysyllables * (COOF_2 / textCounter.sentences)), 0.5)
                + COOF_3;
        return score;
    }

}
