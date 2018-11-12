/*
This is an application that implements Huffman's compression algorithm
to generate a zip file of the provided input file.

Written by: Megan Lesha, Andrew Greenwell
*/
import java.io.FileReader;
import java.io.IOException;
import edu.princeton.cs.algs4.BinaryOut;
import java.util.*;

@SuppressWarnings("unchecked")

public class Huff {

// builds a SymbolTable of characters (Integer representation) and their
// frequencies according to the input text
  public static void buildTable(SymbolTable table, FileIO io, String fname) {
    try {
      FileReader file = io.openInputFile(fname);
      int character = file.read(); // can throw IOException or NullPointerException
      while (character != -1) {    // reading each character from input
        if (!table.containsKey(character)) {
          table.put(character, new STValueC(1, 0, 0)); // see STValueC.java for detials
          table.setSize(table.getSize() + 1);
        }
        else {
          STValue value = (STValue) table.get(character);
          int freq = value.getFrequency();
          value.setFrequency(freq + 1);  // if this char was already in the table, increment its frequency
          table.put(character, value);
        }
        character = file.read();  // can throw IOException or NullPointerException
      }
      file.close();
    }
    catch (IOException | NullPointerException e) {
      System.err.format("Invalid File Name %s\n", e);
      System.exit(1);    // input file object will be null, so we must exit w/ code 1
    }
  }

// updates STValue with encoded bit pattern for each character (Integer) in the SymbolTable
// recursively walks each branch until it reaches a leaf node (base case)
// N tracks the number of relevant bits for each character's bit pattern
  public static void loadBitPaths(SymbolTable table, HuffTree tree, int bits, int N) {
    if (tree.getLeft() == null && tree.getRight() == null) {
      Integer key = tree.getSymbol();
      STValue val = (STValue) table.get(key);
      val.setBits(bits, N);
      table.put(key, val);
    }
    else {
      loadBitPaths(table, tree.getLeft(), bits << 1, N + 1);       // shifting bits left by 1, tacking on a 0
      loadBitPaths(table, tree.getRight(), bits << 1 | 1, N + 1);  // shifting bits left by 1, tacking on a 1
    }
  }

// writes all necessary bits to the output (zip) file
  public static void writeBits(SymbolTable table, FileIO io, String fname) {
    BinaryOut outputFile = io.openBinaryOutputFile();
    int signature = 0x0BC0;
    outputFile.write(signature, 16);                       // 16 bit signature code denoting an encoded file
    outputFile.write(table.getSize(), 32);                 // 32 bits denoting size of the frequency table
    Set<Integer> STKeys = table.keySet();
    for (Integer key : STKeys) {
      STValue val = (STValue) table.get(key);
      outputFile.write(key, 8);                            // writing the character as a byte
      outputFile.write(val.getFrequency(), 32);            // writing that character's frequency as an int
    }
    try {
      FileReader inputFile = io.openInputFile(fname);
      int character = inputFile.read();                      // can throw IOException or NullPointerException
      while (character != -1) {                              // reading each character from input
        STValue val = (STValue) table.get(character);
        outputFile.write(val.getBits(), val.getNumBits());   // write that character's bit pattern
        character = inputFile.read();                        // can throw IOException or NullPointerException
      }
      inputFile.close();
      outputFile.close();
    }
    catch (IOException | NullPointerException e) {
      System.err.format("Invalid File Name %s\n", e);
      System.exit(1);    // input file object will be null, so we must exit w/ code 1
    }
  }

/*
An implementation of the Huffman coding algorithm:
  1. Builds a SymbolTable from input file mapping characters to frequencies
  2. Builds a HuffTree from this SymbolTable containing each character and it's weight (frequency)
  3. Uses HuffTree to find bit patterns for each character; updates SymbolTable with these patterns
  4. Uses updated SymbolTable to write all necessary bits to the output (zip) file
*/
  public static void main(String[] args) {
    SymbolTable<Integer, STValue> table = new SymbolTableC<Integer, STValue>();
    FileIO io = new FileIOC();
    buildTable(table, io, args[0]);  // build SymbolTable from input file
    PriorityQueue<HuffTree> pq = new PriorityQueue<HuffTree>();  // PQ to facilitate creation of the final HuffTree
    Set<Integer> STKeys = table.keySet();
    for (Integer key : STKeys) {
      int weight = table.get(key).getFrequency();
      HuffTree tree = new HuffTreeC(key, weight, null, null);
      pq.add(tree);
    }
    while (pq.size() > 1) {
      HuffTree t1 = pq.remove();
      HuffTree t2 = pq.remove();
      int totalWeight = t1.getWeight() + t2.getWeight();
      HuffTree t3 = new HuffTreeC(null, totalWeight, t1, t2);  // creates new HuffTree with t1 and t2 as its children
      pq.add(t3);
    }
    HuffTree finalTree = pq.remove();        // this is the complete Huffman Coding Tree
    loadBitPaths(table, finalTree, 0, 0);    // update SymbolTable with bit patterns from final HuffTree
    writeBits(table, io, args[0]);           // write out the necessary bits
  }

}
