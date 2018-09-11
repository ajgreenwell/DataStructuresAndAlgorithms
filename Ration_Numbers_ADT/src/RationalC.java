
/*
  An implementation of a simple ADT for rational numbers.
  Creates class methods that allow for the addition, subtraction,
  multiplication, division, and comparison of two rational numbers.
*/

public class RationalC implements Rational {

  private int numerator;
  private int denominator;

  private static int gcd(int p, int q) {
    if (q == 0) return p;
    int r = p % q;
    return gcd(q, r);
  }

  public RationalC(int numerator, int denominator) {
    int gcd = gcd(numerator, denominator);
    this.numerator = numerator/gcd;
    this.denominator = denominator/gcd;
  }

  public int getNumerator() {
    return this.numerator;
  }

  public int getDenominator() {
    return this.denominator;
  }

  public Rational plus(Rational b) {
    int commonDenominator = this.getDenominator() * b.getDenominator();
    int newThisNumerator = this.getNumerator() * b.getDenominator();
    int newBNumerator = b.getNumerator() * this.getDenominator();
    int newNumerator = newThisNumerator + newBNumerator;
    int gcd = gcd(newNumerator, commonDenominator);
    return new RationalC(newNumerator/gcd, commonDenominator/gcd);
  }

  public Rational subtract(Rational b) {
    int commonDenominator = this.getDenominator() * b.getDenominator();
    int newThisNumerator = this.getNumerator() * b.getDenominator();
    int newBNumerator = b.getNumerator() * this.getDenominator();
    int newNumerator = newThisNumerator - newBNumerator;
    int gcd = gcd(newNumerator, commonDenominator);
    return new RationalC(newNumerator/gcd, commonDenominator/gcd);
  }

  public Rational multiply(Rational b) {
    int newDenominator = this.getDenominator() * b.getDenominator();
    int newNumerator = this.getNumerator() * b.getNumerator();
    int gcd = gcd(newNumerator, newDenominator);
    return new RationalC(newNumerator/gcd, newDenominator/gcd);
  }

  public Rational divide(Rational b) {
    int newDenominator = this.getDenominator() * b.getNumerator();
    int newNumerator = this.getNumerator() * b.getDenominator();
    int gcd = gcd(newNumerator, newDenominator);
    return new RationalC(newNumerator/gcd, newDenominator/gcd);
  }

  public boolean equal(Rational b) {
    return (this.getNumerator() == b.getNumerator() &&
            this.getDenominator() == b.getDenominator());
  }

  public int compareTo(Rational b) {
    int newThisNumerator = this.getNumerator() * b.getDenominator();
    int newBNumerator = b.getNumerator() * this.getDenominator();

    if (newThisNumerator < newBNumerator) {
      return -1;
    } else if (newThisNumerator > newBNumerator) {
      return 1;
    }
    return 0;
  }

  public String toString() {
    return String.format("%s/%s", this.getNumerator(), this.getDenominator());
  }

  // Some unit testing.

  public static void main(String[] args) {
    Rational
      r1 = new RationalC(3, 9),
      r2 = new RationalC(2, 10),
      r3 = r1.plus(r2),
      r4 = r1.subtract(r2),
      r5 = r1.multiply(r2),
      r6 = r1.divide(r2);
    int r7 = r1.compareTo(r2);
    boolean isEqual = r1.equal(r2);

    System.out.format("%s + %s = %s%n", r1, r2, r3);             // 8/15
    System.out.format("%s - %s = %s%n", r1, r2, r4);             // 2/15
    System.out.format("%s * %s = %s%n", r1, r2, r5);             // 1/15
    System.out.format("%s / %s = %s%n", r1, r2, r6);             // 5/3
    System.out.format("%s.compareTo(%s) = %s%n", r1, r2, r7);    // 1
    System.out.format("%s%n", isEqual);                          // false
  }
}
