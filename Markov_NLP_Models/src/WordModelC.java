/* file: WordModelC.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   This is an implementation of the Model ADT, a part of an
   implementation of C. Shannon's word-based algorithm for
   modeling English text. This model is built using words
   instead of individual characters. 
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.lang.Math;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class WordModelC implements Model {

    private String input, output;
    private int degree;
    private Map<String, List<String>> map;
    private Words words;

    private class Words {
        private String[] array;
        Words(String input) {
            this.array = input.split(" ");
        }
    }

    public WordModelC(String input, int degree) {
        this.input = input;
        this.degree = degree;
        this.output = "";
        this.words = new Words(input);
        this.map = generateMap(input, degree);
    }

/*
Generates the model itself, which is being represented as a HashMap.
*/
    private Map generateMap(String input, int degree) {
      if (words.array.length <= degree)
        output = "*** Error: degree too large for given input ***";
      else if (degree <= 0)
        output = "*** Error: degree must be > 0 ***";
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i <= words.array.length - degree; ++i) {
            StringBuilder key = new StringBuilder(words.array[i]);
            for (int j = i + 1; j < i + degree; ++j) {
                key.append(" ").append(words.array[j]);
            }
            String value = (i + degree < words.array.length) ? words.array[i + degree] : "";
            List<String> valueList;
            if (!map.containsKey(key.toString()))
              valueList = new ArrayList<String>();
            else
              valueList = map.get(key.toString());
            valueList.add(value);
            if (i == words.array.length - degree)
              valueList.add(Main.SENTINEL.toString());
            map.put(key.toString(), valueList);
        }
        return map;
    }

/*
Samples the model being stored in the class variable "map".
Seeds the sampling by starting with the first degree words as the first key.
If the "output" class variable is not initially empty, this means we have
manually "thrown" an error message to the user via the output.
*/
    public String generateOutput() {
        if (output != "") return output;
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < degree; i++) {
            if (i == 0)  key.append(words.array[i]);
            else         key.append(" ").append(words.array[i]);
        }
        output += key.toString();
        while (map.containsKey(key.toString())) {
            final int LIMIT = (int) Math.round(words.array.length * 1.5);
            List<String> valueList = map.get(key.toString());
            int randIndex = (int) Math.floor(Math.random() * valueList.size());
            String randWord = valueList.get(randIndex);
            if (randWord.equals(Main.SENTINEL.toString())) break;
            output += String.format(" %s", randWord);
            key = new StringBuilder();
            String temp = output;
            String[] tempWords = temp.split(" ");
            for (int j = tempWords.length - degree; j < tempWords.length; j++) {
                if (j == tempWords.length - degree) key.append(tempWords[j]);
                else key.append(" ").append(tempWords[j]);
            }
        }
        return output;
    }

    public String showOutput() { return String.format("%s", output); }

// returns a string of all keys from the model with their corresponding values
    public String showModel() {
      System.out.format("%n");
      Object[] keyArray = map.keySet().toArray();
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < keyArray.length; i++)
        str.append(String.format("%s: %s%n", keyArray[i], map.get(keyArray[i])));
      str.append("%n");
      return str.toString();
    }

// unit testing
    public static void main(String[] args) {
      String inputText = "Hi my name is Andy. I like to write code. "
                       + "This program is written in java.";
        Model model = new WordModelC(inputText, 1);
        System.out.format(model.showModel());
        System.out.format("%s%n%n", model.generateOutput());
    }

}
