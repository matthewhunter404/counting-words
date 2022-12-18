import java.util.*;

interface WordFrequency {
  String getWord();

  int getFrequency();
}

class DefaultWordFrequency implements WordFrequency {
  private String objectWord = "";
  private int objectFrequency = 0;

  public DefaultWordFrequency(String word, int frequency) {
    objectWord = word;
    objectFrequency = frequency;
  }

  public String getWord() {
    return objectWord;
  }

  public int getFrequency() {
    return objectFrequency;
  }
}

class Sort implements Comparator<WordFrequency> {
  // Used for sorting in ascending order of
  // roll number
  public int compare(WordFrequency a, WordFrequency b)
  {
      if (a.getFrequency() != b.getFrequency()) {
        return b.getFrequency() - a.getFrequency();
      };
      return a.getWord().compareTo(b.getWord());
  }
}

interface WordFrequencyAnalyzer {
  int calculateHighestFrequency(String text);

  int calculateFrequencyForWord(String text, String word);

  WordFrequency[] calculateMostFrequentNWords(String text, int n);
}

class DefaultWordFrequencyAnalyzer implements WordFrequencyAnalyzer {
  public DefaultWordFrequencyAnalyzer() {
  }

  public int calculateHighestFrequency(String text) {
    Integer highestFreq = 0;
    Map<String, Integer> wordFrequency = new HashMap<String, Integer>();
    String[] words = text.toLowerCase().split("\\P{Alpha}+");
    for (String word : words) {
      if (wordFrequency.containsKey(word)) {
        Integer newFrequency = wordFrequency.get(word) + 1;
        wordFrequency.put(word, newFrequency);
        if (newFrequency > highestFreq) {
          highestFreq = newFrequency;
        }
      } else {
        wordFrequency.put(word, 1);
      }
    }

    return highestFreq;
  };

  public int calculateFrequencyForWord(String text, String word) {
    Integer frequency = 0;
    String targetWord = word.toLowerCase();
    String[] words = text.toLowerCase().split("\\P{Alpha}+");
    for (String textWord : words) {
      if (textWord.equals(targetWord)) {
        frequency += 1;
      }
    }
    return frequency;
  };

  public WordFrequency[] calculateMostFrequentNWords(String text, int n) {

    Map<String, Integer> wordFrequencyMap = new HashMap<String, Integer>();
    String[] words = text.toLowerCase().split("\\P{Alpha}+");
    for (String word : words) {
      if (wordFrequencyMap.containsKey(word)) {
        Integer newFrequency = wordFrequencyMap.get(word) + 1;
        wordFrequencyMap.put(word, newFrequency);
      } else {
        wordFrequencyMap.put(word, 1);
      }
    }

    WordFrequency[] wordFrequencies = new WordFrequency[wordFrequencyMap.size()];
    int j = 0;
    for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
      wordFrequencies[j] = new DefaultWordFrequency(entry.getKey(), entry.getValue());
      j++;
    }
    //Sort WordFrequencies
    Arrays.sort(wordFrequencies, new Sort());
    WordFrequency[] wordFrequenciesAnswer = Arrays.copyOfRange(wordFrequencies, 0, n);

    for (int i = 0; i < wordFrequenciesAnswer.length; i++) {
      System.out.println(wordFrequenciesAnswer[i].getWord());
    }

    return wordFrequenciesAnswer;
  };
}

public class Main {

  WordFrequencyAnalyzer frequencyAnalyzer = new DefaultWordFrequencyAnalyzer();

  public static void main(String[] args) {
    WordFrequencyAnalyzer frequencyAnalyzer = new DefaultWordFrequencyAnalyzer();
    String testText = "Hello World, hello me, hello everyone, 123, I'm here today to speak to you about counting words";
    int highestFreq = frequencyAnalyzer.calculateHighestFrequency(testText);
    System.out.printf("Frequency: %d%n", highestFreq);
    int freqOfWord = frequencyAnalyzer.calculateFrequencyForWord(testText, "to");
    System.out.printf("FreqOfWord: %d%n", freqOfWord);
    WordFrequency[] freqOfNWords = frequencyAnalyzer.calculateMostFrequentNWords(testText, 5);

    for (int i = 0; i < freqOfNWords.length; i++) {
      System.out.printf("freqOfNWords: %s%n", freqOfNWords[i].getWord());
    }
  }
}