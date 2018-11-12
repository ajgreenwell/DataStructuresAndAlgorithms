# CSCI 1102 Computer Science 2

### Fall 2018

------

## Problem Set 5 : Linked Binary Heaps

### 10 Points

### Due Wednesday October 17, 2018 Midnight

This is a pair problem set. Find one partner to work with. If you don't know anyone yet, you can use the partner finding tool on Piazza or you can ask a staffer to help form your team. **Please identify both team members in the comments at the top of the file.**


## SW 2.4.24 Priority queue with explicit links

As we have seen, *complete binary trees* can be efficiently represented in a sequential array by allocating the elements of the tree in level-order in the array. Binary trees that are not complete are usually represented with linked data structures.  For example, a binary tree might be represented with a linked structure along the lines:


```java
class Node {
                                         +--------------+
  private T info;                        |     info     |
  private Node left;                     +------+-------+
  private Node right;                    | left | right |
  ...                                    +------+-------+
}
```

Implement a priority queue using a heap-ordered binary tree, but use a triply linked structure instead of an array. You will need three links per node: two to traverse down the tree and one to traverse up the tree to the node's parent. Your implementation should guarantee logarithmic running time per operation, even if no maximum priority-queue size is known ahead of time.

Please use the following API for your ADT.

```java
public interface MaxPQ<T extends Comparable<T>> {
  T delMax();
  void insert(T key);
  boolean isEmpty();
  int size();
  String toString();
}
```

Note that the specification of the generic type variable `T` in:

```java
T extends Comparable<T>
```

means that the type variable `Key` can be replaced by any type that includes *at least* an `int compareTo(T other)` function. 

As with the sequential implementation, the `insert` operation must find the tree node to which a new entry is to be attached and the `delMax` operation must find the node containing the last entry in the complete binary tree. In the sequential implementation these tree locations were easy to find using the size of the tree to compute the appropriate array index. With this linked implementation, a little more work is required. But consider using the size of the tree to recursively compute a path from the root to the desired node. Division by 2 will come in handy as will remainder when divided by 2.

As usual, write a few unit tests in order to increase confidence that the code is working correctly.