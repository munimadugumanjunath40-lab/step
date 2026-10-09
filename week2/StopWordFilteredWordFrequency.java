import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StopWordFilteredWordFrequency {
    static void printFilteredWordFrequency(String feedback) {
        Set<String> stopWords = Set.of("the", "was", "and", "a", "is", "of", "in");

        String cleaned = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", "")
                .replace(":", "");

        String trimmed = cleaned.trim();
        Map<String, Integer> frequency = new HashMap<>();

        if (!trimmed.isEmpty()) {
            String[] words = trimmed.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty() && !stopWords.contains(word)) {
                    frequency.put(word, frequency.getOrDefault(word, 0) + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter feedback paragraph:");
        String feedback = sc.nextLine();
        printFilteredWordFrequency(feedback);
        sc.close();
    }
}
