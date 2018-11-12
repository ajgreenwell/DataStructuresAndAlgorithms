/*
Interface for Deques
Submitted by: Austin St. Onge, Andrew Greenwell
*/
public interface Deque<T> {

  void pushLeft(T item);
  T popLeft();

  void pushRight(T item);
  T popRight();

  T peekLeft();
  T peekRight();

  int size();
  boolean isEmpty();
  String toString();
}
