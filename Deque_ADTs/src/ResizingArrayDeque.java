/*
Implementation of a circular ResizingArrayDeque
Submitted by: Austin St. Onge, Andrew Greenwell
*/
import java.util.*;

@SuppressWarnings("unchecked")

public class ResizingArrayDeque<T> implements Deque<T> {

  private T[] a;             // array of items
  private int left, right;   // point to nearest empty indices on either end
  private int n;             // number of items in Deque


// constructs empty deque
  public ResizingArrayDeque() {
      a = (T[]) new Object[2];
      n = 0;
      left = 0;
      right = 1;
  }

// is this deque empty?
  public boolean isEmpty() {return n == 0;}

// returns the number of items in the deque
  public int size() {return n;}

// resize the underlying array holding the elements
  private void resize(int capacity) {
    T[] temp = (T[]) new Object[capacity];
    for (int i = 0; i < n; i++)
      temp[i] = a[(left + i + 1) % a.length];  // copy items over to temp array
    a = temp;
    left = a.length - 1; // point left at the end, since a[0] is leftmost element
    right = n;
  }

// output string containing components of deque
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < this.n; i++)
      sb.append(String.format("%s ", this.a[(this.left + i + 1) % a.length]));
    return sb.toString();
  }

// adds the item to left of deque
  public void pushLeft(T item) {
    if (n == a.length) resize(2 * n);    // double size of array if necessary
    a[left--] = item;
    if (left < 0) left = a.length - 1;   // if left is negative, set it to the last element
    n++;
  }

// removes item from left of deque and returns it
  public T popLeft() {
    if (isEmpty()) throw new NoSuchElementException("Queue Underflow");
    // if left points at the end, point it at the beginning
    if (left == a.length - 1) left = -1;  // a[0] will be the leftmost element
    T item = a[left+1];
    a[left+1] = null;  // empty out the popped index
    left++;
    n--;
    // if less than 1/4 space of the array is being used, halve it
    if (n < a.length / 4) resize(a.length / 2);
    return item;
  }

// adds the item to right of deque
  public void pushRight(T item) {
    if (n == a.length) resize(2 * n);     // double size of array if necessary
    a[right++] = item;
    if (right > a.length - 1) right = 0;  // if right is now off the end, set it back to 0
    n++;
  }

// removes item from right of deque and returns it
  public T popRight() {
    if (isEmpty()) throw new NoSuchElementException("Queue Underflow");
    // if right is pointing to 0, point it at the end
    if (right == 0) right = a.length;  // a[a.length-1] will be rightmost element
    T item = a[right-1];
    a[right-1] = null;  // empty out the popped index
    right--;
    n--;
    // if less than 1/4 space of the array is being used, halve it
    if (n < a.length / 4) resize(a.length / 2);
    return item;
  }

// returns, but does not remove, the leftmost element
  public T peekLeft() {
    if (isEmpty()) throw new NoSuchElementException("Queue Underflow");
    else if (left == a.length - 1) return a[0];
    return a[left+1];
  }

// returns, but does not remove, the rightmost element
  public T peekRight() {
    if (isEmpty()) throw new NoSuchElementException("Queue Underflow");
    else if (right == 0) return a[a.length-1];
    else return a[right-1];
  }

// some unit testing
  public static void main(String[] args) {
    Deque<Integer> d = new ResizingArrayDeque<Integer>();
    System.out.format("%s%n", d);
    System.out.format("pushLeft: 1%n");
    d.pushLeft(1);
    System.out.format("%s%n", d);
    System.out.format("pushLeft: 2%n");
    d.pushLeft(2);
    System.out.format("%s%n", d);
    System.out.format("popLeft: %s%n", d.popLeft());
    System.out.format("%s%n", d);
    System.out.format("pushRight: 3%n");
    d.pushRight(3);
    System.out.format("%s%n", d);
    System.out.format("pushRight: 4%n");
    d.pushRight(4);
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    System.out.format("popLeft: %s%n", d.popLeft());
    System.out.format("%s%n", d);
    System.out.format("pushLeft: 5%n");
    d.pushLeft(5);
    System.out.format("%s%n", d);
    System.out.format("pushLeft: 6%n");
    d.pushLeft(6);
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    System.out.format("pushLeft: 7%n");
    d.pushLeft(7);
    System.out.format("%s%n", d);
    System.out.format("popRight: %s%n", d.popRight());
    System.out.format("%s%n", d);
    d.popLeft();  // should cause queue underflow
  }
}
