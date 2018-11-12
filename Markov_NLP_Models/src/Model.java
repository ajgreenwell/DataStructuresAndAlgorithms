/* file: Model.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   The API for the Model ADT, a part of an implementation
   of C. Shannon's n-gram algorithm for modeling English text.
*/

public interface Model {

  String generateOutput();
  String showOutput();
  String showModel();
 
}
