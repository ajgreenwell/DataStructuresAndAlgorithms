/*
A public Node implementation used to implement the LinkedDeque and LinkedQueue
Submitted by: Austin St. Onge, Andrew Greenwell
*/

// These nodes will hold the client's data in 'info' and have properties that
// point to the nodes on either side of them in the Deque
  public class NodeC<T> implements Node<T> {

    T info;
    Node<T> left, right;

// Constructs a new Node with an 'info' property that holds the user's data
// 'left' & 'right' act as pointers to other linked Nodes
    public NodeC(T info, Node<T> left, Node<T> right) {
      this.info = info;
      this.left = left;
      this.right = right;
    }

// getters, setters and a toString for each of the Node's properties

    public T getInfo() {return info;}
    public void setInfo(T item) {info = item;}

    public Node<T> getLeft() {return left;}
    public void setLeft(Node<T> newLeft) {left = newLeft;}

    public Node<T> getRight() {return right;}
    public void setRight(Node<T> newRight) {right = newRight;}


    public String toString() {
      return String.format("info: %s, left: %s, " +
                            "right: %s%n", info, left, right);
    }

// some unit testing
    public static void main(String[] args) {
      Node<Integer> myNode = new NodeC<Integer>(5, null, null);
      System.out.format("%s%n", myNode);
    }
  }
