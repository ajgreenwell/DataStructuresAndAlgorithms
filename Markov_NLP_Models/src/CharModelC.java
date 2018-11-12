/* file: CharModelC.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   This is an implementation of the Model ADT, a part of an
   implementation of C. Shannon's n-gram algorithm for
   modeling English text.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.lang.Math;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class CharModelC implements Model {

  private String input, output;
  private int degree;
  private Map<String, List<Character>> map;

  public CharModelC(String input, int degree) {
    this.input = input;
    this.degree = degree;
    this.output = "";
    this.map = generateMap(input, degree);
  }

/*
Generates the model itself, which is being represented as a HashMap.
Looks at each character of input, and adds preceding degree of characters
as a key corresponding to a list of characters. That current character is
then appended to the corresponding list.
*/
  private Map generateMap(String input, int degree) {
    if (input.length() <= degree)
      output = "*** Error: degree too large for given input ***";
    else if (degree <= 0)
      output = "*** Error: degree must be > 0 ***";
    Map<String, List<Character>> map = new HashMap<String, List<Character>>();
    for (int i = 0; i <= input.length(); i++) {
      String key;
      if (i == 0)                    key = "";                                  // edge case at beginning of input
      else if (i < degree)           key = input.substring(0, i);               // edge case at beginning of input
      else                           key = input.substring(i - degree, i);
      List<Character> valueList;
      if (!map.containsKey(key))     valueList = new ArrayList<Character>();
      else                           valueList = map.get(key);
      if (i == input.length())       valueList.add(Main.SENTINEL);              // appends stopper flag to last key of input
      else                           valueList.add(input.charAt(i));
      map.put(key, valueList);
    }
    return map;
  }

/*
Samples the model being stored in the class variable "map".
Seeds the sampling by starting with the current output as the first key.
If the "output" class variable is not initially empty, this means we have
manually "thrown" an error message to the user via the output. 
*/
  public String generateOutput() {
    if (output != "") return output;
    String key = output;
    while (map.containsKey(key)) {
      List<Character> valueList = map.get(key);
      int randIndex = (int) Math.floor(Math.random() * valueList.size());       // randomly generate a character from the valueList
      Character randChar = valueList.get(randIndex);
      if (randChar.equals(Main.SENTINEL)) break;                                // if we found the stopper flag, break
      output += randChar.toString();
      if (output.length() - degree < 0)
        key = output.substring(0, output.length());                             // edge case at beginning of output generation
      else
        key = output.substring(output.length() - degree, output.length());
    }
    return output;
  }

  public String showOutput() { return String.format("%s", output); }

// returns a string of all keys from the model with their corresponding values
  public String showModel() {
    System.out.format("%n");
    Object[] keyArray = map.keySet().toArray();
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < keyArray.length; i++) {
      str.append(String.format("%s: %s%n", keyArray[i], map.get(keyArray[i])));
    }
    str.append("%n");
    return str.toString();
  }

// unit testing
  public static void main(String[] args) {
    String inputText = "Hi my name is Andy. I like to write code. "
                     + "This program is written in java.";
    Model model = new CharModelC(inputText, 2);
    System.out.format(model.showModel());
    System.out.format("%s%n%n", model.generateOutput());
  }

}
