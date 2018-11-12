# CSCI 1102 Computer Science 2

### Fall 2018

------

## Problem Set 2 : ADTs in Java

### 10 Points

### Due Wednesday September 12, 2018 Midnight

Do the following variation of [problem 16 section 1.2](https://algs4.cs.princeton.edu/12oop/) of SW. Your ADT for immutable rational numbers should implement the following Java specification:

```java
public interface Rational extends Comparable<Rational> {
  int getNumerator();
  int getDenominator();
  Rational plus(Rational b);
  Rational subtract(Rational b);
  Rational multiply(Rational b);
  Rational divide(Rational b);
  boolean equal(Rational other);
  String toString();
}
```

The notation `extends Comparable<Rational>` means that an implementation of the `Rational` interface requires everything listed explicitly between the braces `{...}` but **also** whatever is specified in the `Comparable` interface. The `Comparable` interface is an important built-in interface from `java.lang`:

```java
public interface Comparable<T> {
  int compareTo(T other);
}
```

Here `T` is a *type variable* and the notation `Comparable<Rational>` should be understood as plugging in the type `Rational` for the type variable `T` in the above. Since the result of that substitution is

```java
int compareTo(Rational other);
```

your implementation

```java
public class RationalC implements Rational {
  ...
}
```

will need the `compareTo` operation too (in addition to the obvious constructor).

Your solution should have unit tests with (at least) 2 unit tests for each operation specified in the interface. Obviously it should look terrific too.

When you're all done, please submit your work with the message "Final":

```
> git add RationalC.java
> git commit -m "Final"
> git push
```

The TAs will review your code, they'll execute your unit tests and they might try a few of their own!

Start soon!
