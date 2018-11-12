# CSII –– Data Structures and Algorithms

This is a collection of all the programs I have written throughout _Computer Science II_, a java-based course focused on data structures and algorithms. Some projects are applications that utilize Abstract Data Types (ADT) to solve some problem. Others involve implementing those ADTs myself. Many projects are a combination of both. Here is a brief overview of all the projects I have completed thus far:

## Deque ADTs

This project involved implementing two different forms of a Deque, which is a double-sided Queue. The first is a linked implementation that utilizes a Node ADT to create a doubly-linked list. The second uses a circular resizing array as its underlying data structure.

## Huffman Coding Algorithm

This application implements a version of Huffman's coding algorithm for the compression and decompression of input files. The bulk of these algorithms are contained within the "main" methods of Huff.java and Puff.java. All other java files contain either interfaces for or implementations of various ADTs used to facilitate the compression and decompression processes. Among these include ADTs for: Huffman Coding Trees, Symbol Tables, Bit Strings, and more. To perform a compression, try passing any of the sample txt files as command line inputs to Huff.java, like so:

``` 
javac Huff.java
java Huff ../samples/mississippi.txt
```

If you'd like to perform a decompression, try passing in the resulting .zip file from the previous commands, like so:

```
javac Puff.java
java Puff ../samples/mississippi.zip
```

## Infix Expression Evaluator

This application accepts infix arithmetic expressions as command-line inputs and prints out the resulting evaluations. The algorithm is based on Djikstra's two stack algorithm, and all of its code is contained within Evaluate.java. To simplify the code, this implementation assumes that input expressions containing operators are enclosed in outer parentheses, and that all symbols have spaces on either side of them.

To evaluate an infix expression, try entering these commands:

```
javac Evaluate.java
java Evaluate
( 5 * 3 + 2 )
```

NOTE: be sure to hit (ctrl-d) on your keyboard after entering the above commands. Otherwise, your expression may not be evaluated.

## Markov Natural Language Processing Models

This application implements a character-based Markov model to generate randomized text outputs based off sample text inputs. This application includes a user interface, which has been implemented within Main.java and Shannon.java. I have additionally implemented a word-based model for more accurate output generation, which is contained within WordModelC.java. For more details about Markov models their probablistic theory, see the underlying README.md file for this directory.

## Priority Queue ADT

This is an implementation of a max priority queue using a linked binary heap. These data structures are often implemented in practice using a resizing array, rather than a linked structure. In order to traverse the heap and find the necessary nodes for insertion and removal, this implementation utilizes a private, recursive method called findNodeAt().

## Rational Numbers ADT

This is an implementation of an ADT for representing rational numbers, such as `5/3`. The related interface includes methods for creating, comparing, and performing basic arithmetic operations on these objects. In order to always store the most simplified version of a rational number, this implementation utilizes Euclid's algorithm for finding the greatest common divisor of two numbers.



