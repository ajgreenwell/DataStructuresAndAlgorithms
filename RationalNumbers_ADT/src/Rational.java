/* file: Rational.java
   author: CS2 Staff

   CSCI 1102 Computer Science 2

   An API for a simple ADT of rational numbers.
   See Sedgewick & Wayne problem 16 Section 1.2.
*/
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
