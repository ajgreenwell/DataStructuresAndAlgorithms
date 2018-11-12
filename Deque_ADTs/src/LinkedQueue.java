/*
Implementation of a LinkedQueue
Submitted by: Austin St. Onge, Andrew Greenwell
*/
public class LinkedQueue<T> implements Queue<T> {

  private Deque<T> deque;

  public LinkedQueue() {
    this.deque = new LinkedDeque<T>();
  }

  public void enqueue(T item) {deque.pushRight(item);}

  public T dequeue() {return deque.popLeft();}

  public T peek() {return deque.peekLeft();}

  public boolean isEmpty() {return deque.isEmpty();}

  public int size() {return deque.size();}

  public String toString() {return deque.toString();}

// some unit testing
  public static void main(String[] args) {
    Queue<Integer> q = new LinkedQueue<Integer>();
    System.out.format("enqueue: 1%n");
    q.enqueue(1);
    System.out.format("%s%n", q);
    System.out.format("enqueue: 2%n");
    q.enqueue(2);
    System.out.format("%s%n", q);
    System.out.format("enqueue: 3%n");
    q.enqueue(3);
    System.out.format("%s%n", q);
    System.out.format("peek: %s%n", q.peek());
    System.out.format("dequeue: %s%n", q.dequeue());
    System.out.format("%s%n", q);
    System.out.format("peek: %s%n", q.peek());
    System.out.format("dequeue: %s%n", q.dequeue());
    System.out.format("%s%n", q);
    System.out.format("dequeue: %s%n", q.dequeue());
    System.out.format("dequeue: ?%n");
    System.out.format("peek: %s%n", q.peek()); // should raise a NoSuchElementException
  }
}
