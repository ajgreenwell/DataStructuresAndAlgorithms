//testing
//file: FileIOC.java
// author: Bob Muller
// revised: March 3, 2018
//
// The FileIO class provides functions that can be used for File I/O in
// a Huffman coding/decoding application.
//
// The program that performs compression (in our case, Huff.java) can use
// the function openInputFile to open the source file. The openInputFile
// function will record the file name. If the file name was foo.txt, then
// a subsequent call to openBinaryOutputFile will create a new file foo.zip
// for output.
//
// The program that performs decompression (in our case, Puff.java) can use
// the function openBinaryInputFile to open the zip file. The openBinaryInputFile
// function will record the file name. If the file name was foo.zip, then
// a subsequent call to openOutputFile will create a new file foo.txt for
// output.
//
// NB: The FileReader and FileWriter classes are from the JRE. The BinaryIn and
//     BinaryOut classes are from the SW stdlib.jar.
//
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

public class FileIOC implements FileIO {

  private String textInFileName;
  private String binaryInFileName;

  public FileReader openInputFile(String fname) {
    FileReader fr = null;
    this.textInFileName = fname;

	  try {
       fr = new FileReader(fname);
     }
     catch (FileNotFoundException e) {
	    System.out.format("FileNotFoundException %s\n", e);
    }
    return fr;
  }

  public BinaryOut openBinaryOutputFile() {
    String binaryOutFileName = this.textInFileName.replace(".txt", ".zip");

	  return new BinaryOut(binaryOutFileName);
  }

  public BinaryIn openBinaryInputFile(String fname) {
    this.binaryInFileName = fname;
    return new BinaryIn(fname);
  }

  public FileWriter openOutputFile() {
    FileWriter fw = null;
    String textOutFileName = this.binaryInFileName.replace(".zip", ".txt");
	  try {
      fw = new FileWriter(textOutFileName);
    }
    catch (IOException e) {
      System.out.format("openOutFile: FileNotFoundException: %s\n", e);
    }
    return fw;
  }
}
