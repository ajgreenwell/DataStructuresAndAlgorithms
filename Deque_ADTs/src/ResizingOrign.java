/*
Implementation of a ResizingArrayDeque
Submitted by: Austin St. Onge, Andrew Greenwell
*/
import java.util.*;

public class ResizingArrayDeque<T> implements Deque<T> {

  private T[] a;         // array of items
  private int left, right;   // spot of left-most and right-most items
  private int n;         // number of elements in Deque


  // initializes empty deque
  public ResizingArrayDeque() {
      a = (T[]) new Object[2];
      n = 0;
      left = 0;
      right = 0;
  }

  // is this deque empty?
  public boolean isEmpty() {return n == 0;}

  // returns the number of items in the deque
  public int size() {return n;}

  // resize the underlying array holding the elements
  private void resize(int capacity) {
    assert capacity >= n;

    // textbook implementation
    T[] temp = (T[]) new Object[capacity];
    for (int i = 0; i < n; i++) {temp[i] = a[i];}
    a = temp;
  }

  // output string containing components of deque
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < this.n; i++)
      sb.append(this.a[this.left + i % a.length]);
    return sb.toString();
  }

  public T peekLeft() {
    if(isEmpty())
      throw new NoSuchElementException("Empty Queue");
    return a[0];
  }

  public T peekRight() {
    if(isEmpty())
      throw new NoSuchElementException("Empty Queue");
    return a[n];
  }

  // Adds the item to left of deque
  public void pushLeft(T item) {
    if (n == a.length - 1) resize(2*a.length);    // double size of array if necessary
    if (isEmpty()) a[0] = item;
    else {
      for (int i = n-1; i >= 0 ; i--) a[i+1] = a[i];  //move every element one spot to the right
      a[0] = item;        // add item to left
    }
    n++;
  }

  // Removes item from left of deque
  public T popLeft() {
    if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    T item = a[0];                // get item at beginning of line
    a[0] = null;                 // make free space null
    if (n > 1)
      for (int i = 0; i <= n; i++) a[i] = a[i + 1];   // shift every item one spot to left
    else a[0] = a[1];
    n--;
    if (n > 0 && n == a.length/4) resize(a.length/2);       // shrink size of array if necessary
    return item;
  }

  // Adds the item to right of deque
  public void pushRight(T item) {
    if (n == a.length - 1) resize(2*a.length);    // double size of array if necessary
    a[n++] = item;        // add item to right
  }

  public T popRight() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      T item = a[n-1];                            // get item at end of line
      a[n-1] = null;                              // make free space null
      n--;
      if (n > 0 && n == a.length/4) resize(a.length/2);     // shrink size of array if necessary
      return item;
  }

  // testing
  public static void main(String[] args) {
    Deque<String> d = new ResizingArrayDeque<String>();
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
    System.out.format("popLeft: %s%n", d.popLeft());
    d.popLeft(); // should raise a NoSuchElementException
  }
}
