/*
Implementation of a LinkedDeque
Submitted by: Austin St. Onge, Andrew Greenwell
*/
import java.util.*;

public class LinkedDeque<T> implements Deque<T> {

  private Node<T> leftMost, rightMost;    // pointers that link to nearby Nodes
  private int N;                          // number of items in deque

// Constructs a new LinkedDeque
  public LinkedDeque() {
    this.leftMost = null;
    this.rightMost = null;
    this.N = 0;
  }

  public int size() {return N;}

  public boolean isEmpty() {return N == 0;}

// while the leftMost Node is not null, builds a string containing the info
// of each Node from left to right
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node<T> current = leftMost;
    while (current != null) {
      sb.append(String.format("%s ", current.getInfo()));
      current = current.getRight();
    }
    return sb.toString();
  }

/*
Adds a new Node containing info of type T to the left of the deque.
If N > 0, alters pointers of leftMost and newNode to link them together.
Shifts the Deque's leftMost pointer to point at newNode in the heap.
*/
  public void pushLeft(T item) {
    Node<T> newNode = new NodeC<T>(item, null, null);
    if (isEmpty()) rightMost = newNode;
    else {
      leftMost.setLeft(newNode);
      newNode.setRight(leftMost);
    }
    leftMost = newNode;
    N++;
  }

/*
If Deque is empty, throw a NoSuchElementException.
If size is 1, nullify the Deque's pointers to leftMost and rightMost.
Else, nullify all pointers to and from the itemToRemove and its 'info' property
*/
  public T popLeft() {
    if (isEmpty())
      throw new NoSuchElementException("Queue Underflow");
    Node<T> nodeToRemove = leftMost;
    T infoToReturn = nodeToRemove.getInfo();
    if (size() == 1) {
      leftMost = null;
      rightMost = null;
    } else {
      leftMost.getRight().setLeft(null);
      leftMost = leftMost.getRight();
      nodeToRemove.setRight(null);
      nodeToRemove.setInfo(null);
    }
    N--;
    return infoToReturn;
  }

// Same concepts as for pushLeft(), but flipped around
  public void pushRight(T item) {
    Node<T> newNode = new NodeC<T>(item, null, null);
    if (isEmpty()) rightMost = newNode;
    else {
      rightMost.setRight(newNode);
      newNode.setLeft(rightMost);
    }
    rightMost = newNode;
    N++;
  }

// Same concepts as for popLeft(), but flipped around
  public T popRight() {
    if (isEmpty())
      throw new NoSuchElementException("Queue Underflow");
    Node<T> nodeToRemove = rightMost;
    T infoToReturn = nodeToRemove.getInfo();
    if (size() == 1) {
      rightMost = null;
      leftMost = null;
    } else {
      rightMost.getLeft().setRight(null);
      rightMost = rightMost.getLeft();
      nodeToRemove.setLeft(null);
      nodeToRemove.setInfo(null);
    }
    N--;
    return infoToReturn;
  }

// returns info property from leftMost Node
  public T peekLeft() {
    if(isEmpty())
      throw new NoSuchElementException("Empty Queue");
    return leftMost.getInfo();
  }

// returns info property from rightMost Node
  public T peekRight() {
    if(isEmpty())
      throw new NoSuchElementException("Empty Queue");
    return rightMost.getInfo();
  }

// some unit testing
  public static void main(String[] args) {
    Deque<String> d = new LinkedDeque<String>();
    System.out.format("%s%n", d);
    System.out.format("pushLeft: A%n");
    d.pushLeft("A");
    System.out.format("%s%n", d);
    System.out.format("pushLeft: B%n");
    d.pushLeft("B");
    System.out.format("%s%n", d);
    System.out.format("popLeft: %s%n", d.popLeft());
    System.out.format("%s%n", d);
    System.out.format("pushRight: C%n");
    d.pushRight("C");
    System.out.format("%s%n", d);
    System.out.format("pushRight: D%n");
    d.pushRight("D");
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    System.out.format("popLeft: %s%n", d.popLeft());
    System.out.format("%s%n", d);
    System.out.format("pushLeft: A%n");
    d.pushLeft("A");
    System.out.format("%s%n", d);
    System.out.format("pushLeft: B%n");
    d.pushLeft("B");
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    System.out.format("popLeft: %s%n", d.popLeft());
    System.out.format("%s%n", d);
    System.out.format("pushLeft: A%n");
    d.pushLeft("A");
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    d.popLeft();  // should cause queue underflow
  }
}
