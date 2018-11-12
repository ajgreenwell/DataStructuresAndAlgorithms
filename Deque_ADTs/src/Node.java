/*
Interface for Nodes with left and right pointers
Submitted by: Austin St. Onge, Andrew Greenwell
*/
public interface Node<T> {

  T getInfo();
  void setInfo(T item);

  Node<T> getLeft();
  Node<T> getRight();

  void setLeft(Node<T> newLeft);
  void setRight(Node<T> newRight);

  String toString();

}
