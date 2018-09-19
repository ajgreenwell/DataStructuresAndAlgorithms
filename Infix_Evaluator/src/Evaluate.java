import edu.princeton.cs.algs4.StdIn;
import java.util.HashMap;
/*
The following is an application that utilizes the ResizingArrayStack
implementation. This app takes mathematical infix expressions as input
at the command line and evaluates them using a variation of Dijkstra's
two-stack algorithm for expression evaluation.
*/
public class Evaluate {

  // takes two operators as input
  // returns true if op1 has higher/equal precedence as op2, else returns false
  private static boolean hasPrecedence(String op1, String op2) {
    HashMap<String, Integer> precedenceMap = new HashMap<String, Integer>();
    precedenceMap.put(")", 0);
    precedenceMap.put("^", 1);
    precedenceMap.put("*", 2);
    precedenceMap.put("/", 2);
    precedenceMap.put("+", 3);
    precedenceMap.put("-", 3);
    precedenceMap.put("(", 4);
    if (precedenceMap.get(op1) <= precedenceMap.get(op2)) {
      return true;
    }
    return false;
  }

  // returns false if the number cannot be converted to double, true if it can
  private static boolean isNumber(String str) {
    try {
      double d = Double.parseDouble(str);
    } catch(NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  // returns true if input string is a valid operator, else returns false
  private static boolean isOperator(String str) {
    if (str.equals("+") || str.equals("-") || str.equals("*") ||
        str.equals("/") || str.equals("^")) {
      return true;
    }
    return false;
  }
  /*
  takes one operator and two values as input
  evaluates the operator being applied to these two values, in correct order
  if the operator is invalid, an ArithmeticException is thrown
  */
  private static double eval(String oper, double value1, double value2) {
    if (oper.equals("+"))         return value1 + value2;
    else if (oper.equals("-"))    return value1 - value2;
    else if (oper.equals("*"))    return value1 * value2;
    else if (oper.equals("/"))    return value1 / value2;
    else if (oper.equals("^"))    return Math.pow(value1, value2);
    else throw new ArithmeticException();
  }

  // main function begins, containing the variation of Dijkstra's algorithm
  public static void main(String[] args) {
    // create two stacks, one for operators and one for values
    Stack<String> ops  = new ResizingArrayStack<String>();
    Stack<Double> vals = new ResizingArrayStack<Double>();

      while (!StdIn.isEmpty()) {
        // read token
        String s = StdIn.readString();
        // now test token for various conditions...
        if (isNumber(s))           vals.push(Double.parseDouble(s));
        else if (s.equals("("))    ops.push(s);
        // if it's a ")", we need to evaluate the expression
        else if (s.equals(")")) {
          // pop two vals, pop one op, evaluate, and push result
          while (!ops.peek().equals("(")) {
            String op = ops.pop();
            double v = vals.pop();
            double result = eval(op, vals.pop(), v);
            vals.push(result);
          }
          // discard the left paren that began this expression
          ops.pop();
        }
        // if it's an operator, we might need to evaluate an expression, so...
        else if (isOperator(s)) {
          // if ops stack is not empty, and if top op is not a "(", and
          // if top op has precedence over current token: pop, eval, push
          while (!ops.isEmpty() && !ops.peek().equals("(") &&
                 hasPrecedence(ops.peek(), s)) {
            String op = ops.pop();
            double v = vals.pop();
            double result = eval(op, vals.pop(), v);
            vals.push(result);
          }
          // if any of the while conditions failed, we need to push this op
          ops.push(s);
        }
        // else if the input string meets none of the above conditions, it must
        // not be a number, space, or valid operator, so throw an exception
        else throw new ArithmeticException();
      }
      System.out.format("%s%n", vals.pop());
   }
}
