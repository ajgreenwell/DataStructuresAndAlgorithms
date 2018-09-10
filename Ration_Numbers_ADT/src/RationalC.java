/* file: Rational.java
   author: CS2 Staff

   CSCI 1102 Computer Science 2

   An implementation of a simple ADT of rational numbers.
   See Sedgewick & Wayne problem 16 Section 1.2.
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
    int common_denominator = this.getDenominator() * b.getDenominator();
    int new_this_numerator = this.getNumerator() * b.getDenominator();
    int new_b_numerator = b.getNumerator() * this.getDenominator();
    int new_numerator = new_this_numerator + new_b_numerator;
    int gcd = gcd(new_numerator, common_denominator);
    return new RationalC(new_numerator/gcd, common_denominator/gcd);
  }

  public Rational subtract(Rational b) {
    int common_denominator = this.getDenominator() * b.getDenominator();
    int new_this_numerator = this.getNumerator() * b.getDenominator();
    int new_b_numerator = b.getNumerator() * this.getDenominator();
    int new_numerator = new_this_numerator - new_b_numerator;
    int gcd = gcd(new_numerator, common_denominator);
    return new RationalC(new_numerator/gcd, common_denominator/gcd);
  }

  public Rational multiply(Rational b) {
    int new_denominator = this.getDenominator() * b.getDenominator();
    int new_numerator = this.getNumerator() * b.getNumerator();
    int gcd = gcd(new_numerator, new_denominator);
    return new RationalC(new_numerator/gcd, new_denominator/gcd);
  }

  public Rational divide(Rational b) {
    int new_denominator = this.getDenominator() * b.getNumerator();
    int new_numerator = this.getNumerator() * b.getDenominator();
    int gcd = gcd(new_numerator, new_denominator);
    return new RationalC(new_numerator/gcd, new_denominator/gcd);
  }

  public boolean equal(Rational b) {
    if (this.getNumerator() == b.getNumerator() &&
        this.getDenominator() == b.getDenominator()) {
          return true;
    }
    return false;
  }

  public int compareTo(Rational b) {
    int common_denominator = this.getDenominator() * b.getDenominator();
    int new_this_numerator = this.getNumerator() * b.getDenominator();
    int new_b_numerator = b.getNumerator() * this.getDenominator();

    if (new_this_numerator < new_b_numerator) {
      return -1;
    } else if (new_this_numerator > new_b_numerator) {
      return 1;
    }
    return 0;
  }

  public String toString() {
    return String.format("%s/%s", this.getNumerator(), this.getDenominator());
  }

  // Some unit testing.
  //
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
