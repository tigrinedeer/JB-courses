package readability;

public class ColemanLiauIndexCounter extends IndexCounter{

    private final double COOF_1 = 0.0588, COOF_2 = 0.296, COOF_3 = 15.8;

    public ColemanLiauIndexCounter(TextCounter textCounter) {
        super(textCounter);
        this.name = "Coleman-Liau index";
    }

    public double countScore() {
        double L = textCounter.characters / textCounter.words * 100;
        double S = textCounter.sentences / textCounter.words * 100;
        score = COOF_1 * L - COOF_2 * S - COOF_3;
        calculateAge();
        return score;
    }
}
