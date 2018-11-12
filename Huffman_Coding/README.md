# CSCI 1102 Computer Science 2

### Fall 2018

##### Instructor R. Muller

Boston College

---

## Problem Set 8 : Huffman Decoding

### 10 Points

### Due Sunday November 11, 2018 Midnight

This is a pair problem set. Work with the same partner you worked with on the previous problem set. If you don't have a partner in mind, you can use the partner finding tool on Piazza or you can ask a staffer to help form your team. **Please identify both team members in the comments at the top of your files.**

---

In the previous problem set you teamed up with one other person to design and develop a program `Huff.java` that performed Huffman encoding of text files. If you didn't get that program working, let a staffer know, we'll give you a copy of it.

Huffman coding is a lossless compression algorithm that achieves a respectable rate of compression. In this problem set you are to design and develop the inverse program Puff. Your team's pair of programs should have the property that for every text file `f.txt`

```
> cp f.txt original.txt 
> java Huff f.txt
> java Puff f.zip
> diff f.txt original
>                       # no message means no differences
```

Refer to the statement of the previous problem set for a description of the data structures required for Huffman coding.

## Puff : the Huffman Decoding Algorithm

The Huffman algorithm has a coding part and a decoding part. These are sometimes called *codec*s. The basic ingredients needed for decoding have all been described in the statement of the Huff problem. The algorithm works as follows.

1. Open the zip file provided as a command-line argument. Confirm that the file was produced by the associated Huff program by reading two bytes and confirming that they have the integer value `0x0BC0`. If not, print an error message and exit.

2. Read one 4-byte integer from the zip file. For the purposes of this discussion, let's call it N. The integer N specifies the size of the character frequency table in the zip file. Each entry has a 1-byte character code followed by a 4-byte integer representing the frequency of the character in the input text.

3. Create a new symbol table. For each of the N entries in the table in the zip file, read character `c` and frequency `f`. Enter `c` and `f` in the symbol table.

4. Use the symbol table to construct the Huffman coding tree. (See the description of the Huff algorithm above.) For the purposes of this discussion let's call it `hct`.

5. Open the output text file.

6. For each coded letter, do the following:

  A. Set temporary variable `t` to point to the root of the Huffman coding tree `hct`. 

  B. Until `t` is pointing to a leaf node, do the following:

   + Read one bit `b` from the zip file.

   + Advance `t` to either the left or the right child depending on `b` and the convention that you adopted. (I.e., left = 1, right = 0 or vice-versa).

  C. Write the character found at the leaf node to the output file.

7. Close the output file.

You can test your Puff program to see if it is working correctly by trying to decompress the test file in the repo.


You're done! Give your code a once over to make sure that it looks great, then push it to the GitHub master repo.