package readability;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class TextCounter {

    private String text;
    public double characters = 0,
            words = 0,
            sentences = 0,
            syllables = 0,
            polysyllables = 0;

    public TextCounter(String text) {
        this.text = text;
    }

    private int countCharacters() {
        for (int i = 0; i < text.length(); i++) {
            if (text.substring(i,i+1).matches("[^ \n\t]"))
                characters += 1;
        }
        return (int) characters;
    }

    private int countWords() {
        int syll = 0;

        for (Object word: text.split("[ ]")) {
            words += 1;
            syll = countSyllables((String) word);
            syllables += syll;
            if (syll > 2)
                polysyllables += 1;
        }
    //    words =  scanner.findAll("([\\w\\d,]+)").count();
        return (int) words;

    }

    private int countSentences() {
        sentences = text.split("[?!.\n]").length;
        return (int) sentences;
    }

    private int countSyllables(String word) {

        word = word.replaceAll("[aoueyiAOUEYI]{2}", "a").replaceAll("[?!.\n]","")
                .replaceAll("e$","" )
                .replaceAll("/d","a" );
        Scanner scanner = new Scanner(word);
        long syllables = scanner.findAll("[aoueyiAOUEYI]").count();
        return (int) (syllables !=0 ? syllables : 1);
    }

    public void countText() {
          countCharacters();
          countSentences();
          countWords();
    }

    public void printResults() {
        countText();
        System.out.println(String.format("Words: %.0f",words));
        System.out.println(String.format("Sentences: %.0f",sentences));
        System.out.println(String.format("Characters: %.0f",characters));
        System.out.println(String.format("Syllables: %.0f",syllables));
        System.out.println(String.format("Polysyllables: %.0f",polysyllables));
    }

    public void calculateScore() {
        System.out.println(String.format("Words: %.0f",words));
        System.out.println(String.format("Sentences: %.0f",sentences));
        System.out.println(String.format("Characters: %.0f",characters));
        System.out.println(String.format("Syllables: %.0f",syllables));
        System.out.println(String.format("Polysyllables: %.0f",polysyllables));
    }

}
