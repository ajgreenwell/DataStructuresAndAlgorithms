/*
Interface for Queues
Submitted by: Austin St. Onge, Andrew Greenwell
*/
public interface Queue<T> {

  void enqueue(T item);
  T dequeue();
  T peek();

  boolean isEmpty();
  int size();
  String toString();
}
