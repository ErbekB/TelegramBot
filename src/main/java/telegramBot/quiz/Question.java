package telegramBot.quiz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question {
    private final String title;
    private final String text;
    private final List<String> options;
    private final String solution;
    private final String solutionAlphabetical;

    public Question(String title, String text, List<String> options, String solution,
                    String solutionAlphabetical) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.solution = solution;
        this.solutionAlphabetical = solutionAlphabetical;
    }

    public String getSolution() {
        return solution;
    }

    public String getSolutionAlphabetical() {
        return solutionAlphabetical;
    }

    public String toString() {
        String title = "\uD83D\uDCDA                 " + "<b>" +this.title + "</b>";
        StringBuilder format = new StringBuilder();

        String optionA = String.format("<b>%c)</b> %s       ", 'a', this.options.get(0));
        String optionB = String.format("<b>%c)</b> %s    \n", 'b', this.options.get(1));
        String optionC = String.format("<b>%c)</b> %s       ", 'c', this.options.get(2));
        String optionD = String.format("<b>%c)</b> %s", 'd', this.options.get(3));

        format.append(optionA).append(optionB).append(optionC).append(optionD);

        return title + "\n\n" + "<i>" + this.text + "</i>" + "\n\n" + format;
    }
}