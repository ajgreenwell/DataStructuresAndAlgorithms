/*
This is an application that implements Huffman's decoding algorithm
to generate a txt file of the provided zip file

Written by: Megan Lesha, Andrew Greenwell
*/
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import edu.princeton.cs.algs4.BinaryIn;
import java.util.*;

@SuppressWarnings("unchecked")

public class Puff {

  // checks if a zip file was produced by the Huff program by reading 2 bytes
  // and returning true if first 2 bytes has integer value 0x0BC0
  public static boolean isHuff(BinaryIn zipFile) {
    int signature = 0x0BC0;
    int fsignature = zipFile.readInt(16); // reads the next (1st in this case) 2 bytes from binary input stream and return as a 2-byte int
    if (signature == fsignature) return true;
    else return false;
  }

  public static void writeOutput(HuffTree tree, BinaryIn inputFile, FileWriter outputFile) throws IOException {
    if (tree.getLeft() == null && tree.getRight() == null) {
      Integer character = tree.getSymbol();
      outputFile.write((char) (int) character);   // DOUBLE CHECK THIS FUNCTION
    }
    else {
      int direction = inputFile.readInt(1);
      if (direction == 1 ) writeOutput(tree.getRight(), inputFile, outputFile);
      else                 writeOutput(tree.getLeft(), inputFile, outputFile);
    }
  }


  public static void main(String[] args) {
    FileIO io = new FileIOC();
    BinaryIn inputFile = io.openBinaryInputFile(args[0]);
    if (!isHuff(inputFile)) {
      System.err.format("zip file not produced by Huff.java");
      System.exit(1);
    }
    int N = inputFile.readInt(32); //N is the 4-byte integer from the zip file specifying the size of the character frequency table
    SymbolTable<Integer, STValue> table = new SymbolTableC<Integer, STValue>();
    int i = 1;
    while (i <= N) {
      int c = inputFile.readInt(8); // charac is the 1-byte character (integer) code
      int f = inputFile.readInt(32); // freq is the 4-byte integer representing the ()
      table.put(c, new STValueC(f,0,0)); // adds c and f into the table
      i++;
    }

    PriorityQueue<HuffTree> pq = new PriorityQueue<HuffTree>(); // PQ to facilitate creation of the final HuffTree
    Set<Integer> STKeys = table.keySet();
    int numCharacters = 0;    // determines how many bits we need to read in for the bit string
    for (Integer key : STKeys) {
      int weight = table.get(key).getFrequency();
      numCharacters += weight;
      HuffTree tree = new HuffTreeC(key, weight, null, null);
      pq.add(tree);
    }

    while(pq.size() > 1) {
      HuffTree t1 = pq.remove();
      HuffTree t2 = pq.remove();
      int totalWeight = t1.getWeight() + t2.getWeight();
      HuffTree t3 = new HuffTreeC(null, totalWeight, t1, t2);
      pq.add(t3);
    }
    HuffTree hct = pq.remove();
    try {
      FileWriter outputFile = io.openOutputFile();
      for (int j = 1; j <= numCharacters; j++)
        writeOutput(hct, inputFile, outputFile);
      outputFile.close();
    }
    catch (IOException | NullPointerException e) {
      System.err.format("Invalid File Name %s", e);
      System.exit(1);
    }
  }

}
