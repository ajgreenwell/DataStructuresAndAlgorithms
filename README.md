# CSII -- Data Structures and Algorithms

This is a collection of all the programs I have written throughout _Computer Science II_, a java-based course focused on data structures and algorithms. Some projects are applications that utilize Abstract Data Types (ADT) to solve some problem. Others involve implementing those ADTs myself. Many projects are a combination of both. Here is a brief overview of all the projects I have completed thus far:

## Deque ADTS

This project involved implementing two different forms of a Deque, which is a double-sided Queue. The first is a linked implementation that utilizes a Node ADT to create a doubly-linked list. The second uses a circular resizing array as its underlying data structure.

## Huffman Coding Algorithm

The goal of this project was to implement a version of Huffman's coding algorithm for the compression and decompression of input files. The bulk of these algorithms are contained within the "main" methods of Huff.java and Puff.java. All other java files contain either interfaces for or implementations of various ADTs used to facilitate the compression and decompression processes. Among these include ADTs for: Huffman Coding Trees, Symbol Tables, Bit Strings, and more. To perform a compression, try passing any of the sample .txt files as command line inputs to Huff.java, like so:

``` 
javac Huff.java
java Huff ../samples/mississippi.txt
```

If you'd like to perform a decompression, try passing in the resulting .zip file from the previous commands, like so:

```
javac Puff.java
java Puff ../samples/mississippi.zip
```

