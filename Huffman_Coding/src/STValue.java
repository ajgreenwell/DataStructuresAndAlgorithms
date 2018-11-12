/*
This is an interface for symbol table values. These values will be related to
characters from an input file using a symbol table (HashMap).

Written by: Megan Lesha, Andrew Greenwell
*/

public interface STValue {

  public int getBits();
  public int getNumBits();
  public void setBits(int bits, int N);

  public int getFrequency();
  public void setFrequency(int freq);

  public String toString();

}
