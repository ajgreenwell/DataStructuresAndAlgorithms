/*
This is an implementation for symbol table values. These values will be related
to characters from an input file using a SymbolTable. Each STValue
has two class variables that can be accessed via getters and setters. One value
is an int, representing the frequency with which the related character appears
in the input file. The second value is a BitString (another ADT) representing
the encoded bits for the associated character, according to the relevant
Huffman coding tree.

Written by: Megan Lesha, Andrew Greenwell
*/

public class STValueC implements STValue {

  private int freq;           // frequency of the STValue's corresponding key (character) in the input text
  private BitString bitStr;   // a private ADT representing the bit pattern for each character in the input text

  private class BitString {
    int bits;
    int N;    // number of relevant bits

    BitString(int bits, int N) {
      this.bits = bits;
      this.N = N;
    }
  }

  STValueC(int freq, int bits, int N) {
    this.freq = freq;
    this.bitStr = new BitString(bits, N);
  }

  public int getBits() { return bitStr.bits; }
  public int getNumBits() { return bitStr.N; }
  public void setBits(int bits, int N) {
    this.bitStr.bits = bits;
    this.bitStr.N = N;
  }

  public int getFrequency() { return freq; }
  public void setFrequency(int freq) { this.freq = freq; }

  public String toString() {
    return String.format("Frequency: %s, Bits: 0x%08x, "
                       + "N: %s", freq, bitStr.bits, bitStr.N);
  }

// unit testing
  public static void main(String[] args) {
    STValue val = new STValueC(1, 12, 5);
    System.out.format(val.toString() + "%n");
    val.setBits(10, 3);
    System.out.format(val.toString() + "%n");
    val.setFrequency(val.getFrequency() + 1);
    System.out.format(val.toString() + "%n");
  }

}
