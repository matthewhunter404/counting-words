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
    Integer freqency = 0;
    String targetWord = word.toLowerCase();
    String[] words = text.toLowerCase().split("\\P{Alpha}+");
    for (String textWord : words) {
      if (textWord.equals(targetWord)) {
        freqency+=1;
      }
    }
    return freqency;
  };

  public WordFrequency[] calculateMostFrequentNWords(String text, int n) {
    WordFrequency wordFrequency = new DefaultWordFrequency("tommy", 2);
    WordFrequency[] wordFrequencies = { wordFrequency };
    return wordFrequencies;
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
  }
}