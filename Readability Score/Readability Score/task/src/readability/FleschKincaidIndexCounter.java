package readability;

public class FleschKincaidIndexCounter extends IndexCounter{

    private static final double COOF_1 = 0.39, COOF_2 = 11.8, COOF_3 = 15.59;

    public FleschKincaidIndexCounter(TextCounter textCounter) {
        super(textCounter);
        name = "Flesch-Kincaid readability tests";
    }

    @Override
    public double countScore() {
        score = COOF_1 * (textCounter.words / textCounter.sentences)
                + COOF_2 * (textCounter.syllables / textCounter.words) - COOF_3;
        return score;
    }

}
