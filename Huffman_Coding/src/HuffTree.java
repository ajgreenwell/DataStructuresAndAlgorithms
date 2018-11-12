/*
This is an interface for Huffman trees; implemented by HuffTreeC.java

Written by: Megan Lesha, Andrew Greenwell
*/

public interface HuffTree extends Comparable {

  public int getSize();
  public int getWeight();

  public Integer getSymbol();

  public HuffTree getLeft();
  public HuffTree getRight();

  public int compareTo(Object other);
  public String toString();

}
