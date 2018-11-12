/*
LinkedBinaryHeap that implements a MaxPQ
Submitted by: Andrew Greenwell, Austin St. Onge
*/

import java.util.*;

public class LinkedBinaryHeap<Key extends Comparable<Key>> implements MaxPQ<Key> {

  private Node root, lastParent; // lastParent will be useful when inserting/removing the max
  private int N;

  public LinkedBinaryHeap() {
    this.root = null;
    this.lastParent = null;
    this.N = 0;
  }

  private class Node {
    Key info;
    Node parent, left, right;

    Node(Key info, Node parent, Node left, Node right) {
      this.info = info;
      this.parent = parent;
      this.left = left;
      this.right = right;
    }
  }

  private boolean less(Node a , Node b) {
    return a.info.compareTo(b.info) < 0;
  }

// swaps the info fields of two nodes
  private void exchange(Node a, Node b){
    Key temp = a.info;
    a.info = b.info;
    b.info = temp;
  }

// swims a node's info up the heap to restore heap order after an insertion
  private void swim(Node node) {
    while (node.parent != null && less(node.parent, node)) {
      exchange(node.parent, node);
      node = node.parent;
    }
  }

// sinks a node's info down the heap to restore heap order after a removal
  private void sink(Node node) {
    while (node.left != null && node.right != null) {
      Node child = node.left;
      if (node.right != null && less(node.left, node.right))
        child = node.right;
      if (less(child, node)) break;
      exchange(child, node);
      node = child;
    }
  }

// recursively returns the node at position 'n'
// used in both delMax() and insert() to locate points of insertion and removal
  private Node findNodeAt(int n) {
    if (n == 1) return root;
    else {
      Node p = findNodeAt(n / 2);
      if (n % 2 == 0)
        return p.left;
      else
        return p.right;
    }
  }

// removes the max node from the heap by swapping the info fields of root and
// last node, then dissociating the last node and sinking the new root
  public Key delMax() {
    if (isEmpty())
      throw new NoSuchElementException("Priority Queue Underflow");
    else if (N == 1) {
      Key maxInfo = root.info;
      root = null;
      lastParent = null;
      N--;
      return maxInfo;
    }
    Key maxInfo = root.info;
    Node last = findNodeAt(N);
    exchange(last, root);
    if (last.parent.right == null) last.parent.left = null;
    else last.parent.right = null;
    // resets lastParent incase the removal changed the next insertion point
    lastParent = findNodeAt(N / 2);
    N--;
    sink(root);
    return maxInfo;
  }

// inserts a new node with the provided info field
// uses the recursive findNodeAt() method to locate the point of insertion
// swims the new node's info field up the heap to restore heap order
  public void insert(Key key) {
    Node newNode = new Node(key, lastParent, null, null);
    if (isEmpty()) {
      root = newNode;
      lastParent = newNode;
      N++;
      return;
    }
    N++;
    if (lastParent.left == null) lastParent.left = newNode;
    else {
      lastParent.right = newNode;
      // resets lastParent when its right branch gets filled
      lastParent = findNodeAt((N + 1) / 2);
    }
    swim(newNode);
  }

  public boolean isEmpty() { return N == 0; }

  public int size() { return N; }

// recursively returns a string representation of the heap
  private String toStringPrivate(Node node) {
    if (isEmpty()) return "empty";
    if (node.right == null && node.left == null)
      return String.format("%s", node.info);
    else if (node.right == null) {
      String left = toStringPrivate(node.left);
      return String.format("%s(%s)", node.info, left);
    } else {
      String left = toStringPrivate(node.left);
      String right = toStringPrivate(node.right);
      return String.format("%s(%s,%s)", node.info, left, right);
    }
  }

// public toString() method available to clients
// returns the return value of the private toString() method above
  public String toString() {
    return toStringPrivate(this.root);
  }

// some unit testing...
  public static void main(String[] args) {
    MaxPQ<Integer> pq = new LinkedBinaryHeap<Integer>();
    pq.insert(4);
    System.out.format(pq.toString() + "%n");
    System.out.format("Max deleted: %s%n", pq.delMax());
    System.out.format(pq.toString() + "%n");
    pq.insert(3);
    System.out.format(pq.toString() + "%n");
    pq.insert(2);
    System.out.format(pq.toString() + "%n");
    pq.insert(1);
    System.out.format(pq.toString() + "%n");
    pq.insert(0);
    System.out.format(pq.toString() + "%n");
    pq.insert(5);
    System.out.format(pq.toString() + "%n");
    System.out.format("Max deleted: %s%n", pq.delMax());
    System.out.format(pq.toString() + "%n");
    pq.insert(7);
    System.out.format(pq.toString() + "%n");
    pq.insert(-1);
    System.out.format(pq.toString() + "%n");
    pq.insert(-1);
    System.out.format(pq.toString() + "%n");
    pq.insert(-2);
    System.out.format(pq.toString() + "%n");
    System.out.format("Max deleted: %s%n", pq.delMax());
    System.out.format(pq.toString() + "%n");
    System.out.format("Max deleted: %s%n", pq.delMax());
    System.out.format(pq.toString() + "%n");
    pq.insert(-5);
    System.out.format(pq.toString() + "%n");
    pq.insert(-5);
    System.out.format(pq.toString() + "%n");
    System.out.format("comparing two successive delMax() calls: %s%n",
                      pq.delMax().compareTo(pq.delMax()));
  }
}
