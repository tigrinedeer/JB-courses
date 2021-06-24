package readability;

public class AutomatedReadabilityIndexCounter extends IndexCounter{

    private  final double COOF_1 = 4.71, COOF_2 = 0.5, COOF_3 = 21.43;

    public AutomatedReadabilityIndexCounter(TextCounter textCounter) {
        super(textCounter);
        this.name = "Automated Readability Index";
    }

    @Override
    public double countScore() {
        score = COOF_1 * (textCounter.characters / textCounter.words)
                + COOF_2 * (textCounter.words / textCounter.sentences) - COOF_3;
        return score;
    }
}
