import java.util.ArrayList;
import java.util.List;

public class WordCount extends FileProcessor<List<Integer>> {
    protected List<Integer> fileStats = new ArrayList<Integer>();

    @Override
    protected void startFile() {
        for (int i = 0; i < 3; i++) {
            fileStats.add(i,0);
        }
    }

    @Override
    protected void processLine(String line) {
        // characters
        fileStats.set(0, fileStats.get(0) + line.length());
        // words
        fileStats.set(1, fileStats.get(1) + countWords(line));
        // lines
        fileStats.set(2, fileStats.get(2) + 1);
    }

    @Override
    protected List<Integer> endFile() {
        return fileStats;
    }

    private static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        int wordCount = 0;
        boolean inWord = false;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (!Character.isWhitespace(currentChar)) {
                if (!inWord) {
                    inWord = true;
                    wordCount++;
                }
            } else {
                inWord = false;
            }
        }
        return wordCount;
    }

}
