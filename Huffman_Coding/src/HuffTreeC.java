/*
This is an implementation for Huffman trees; see HuffTree.java for interface

Written by: Megan Lesha, Andrew Greenwell
*/

public class HuffTreeC implements HuffTree {

  private int N;       // size of the HuffTree -- does not get used in Huff.java
  private Node root;

  public HuffTreeC(Integer symbol, int weight, HuffTree left, HuffTree right) {
    if (left == null && right == null) this.N = 1;
    else this.N = left.getSize() + right.getSize();
    this.root = new Node(symbol, weight, left, right);
  }

  private class Node {
    private Integer symbol;
    private int weight;
    private HuffTree left;
    private HuffTree right;

    private Node(Integer symbol, int weight, HuffTree left, HuffTree right) {
      this.symbol = symbol;
      this.weight = weight;
      this.left = left;
      this.right = right;
    }

// recrusively visits each node in the tree until it reaches a leaf node
    public String toString() {
      if (this.left == null && this.right == null)
        return String.format("%c", (char) (int) this.symbol); // converts the symbol to its ASCII character
      else {
        String weight = Integer.toString(this.weight);
        String leftString = this.left.toString();         // recursively find toString() of each branch
        String rightString = this.right.toString();       //
        return String.format("%s(%s, %s)", weight, leftString, rightString);
      }
    }
  }

  public int getSize() { return N; }
  public int getWeight() { return this.root.weight; }
  public Integer getSymbol() { return this.root.symbol; }

  public HuffTree getLeft() { return this.root.left; }
  public HuffTree getRight() { return this.root.right; }

// compares HuffTrees based off of their weights
// overrides the arbitrary tie-breaking policy of PriorityQueues by never returning 0
  public int compareTo(Object other) {
    HuffTree otherTree = (HuffTree) other;
    if (this.getWeight() <= otherTree.getWeight()) return -1;
    return 1;
  }

  public String toString() {
    return this.root.toString();
  }

  // unit testing
  public static void main(String[] args) {
    HuffTree t1 = new HuffTreeC(83, 2, null, null);
    HuffTree t2 = new HuffTreeC(84, 1, null, null);
    HuffTree t3 = new HuffTreeC(null, t1.getWeight() + t2.getWeight(), t1, t2);
    HuffTree t4 = new HuffTreeC(85, 5, null, null);
    HuffTree t5 = new HuffTreeC(null, t3.getWeight() + t4.getWeight(), t3, t4);
    System.out.format(t5 + "%n");
  }
}
