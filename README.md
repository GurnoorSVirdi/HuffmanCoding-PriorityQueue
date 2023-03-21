# Homework 4 Programming

Please remember that to submit the assignment you will need to the Education drop down menu and select 
assignment complete.

## Problem 1 - K-Best Values - 30 points

Find the k-best (i.e. largest) values in a set of data. Assume you are given a sequence of values, one value at a time. We do not know how many elements there are in this sequence. In fact, there could be infinitely many. Implement the class
```KBestCounter<T extends Comparable<? super T>> implements KBest<T>``` that keeps track of the k-largest elements seen so far in a sequence of data. The class should have two methods and a constructor:

* ```public KBestCounter(int k)``` - This is your constructor.  It should take in an int k that indicates the amount of largest elements you want to return. The constructor isn't explicitly specified in the KBest interface, but you must have it exactly like this for your program to be graded.

* ```public void count(T x)``` - process the next element in the set of data. This operation must run in at worst `O(log k)` time.

* ```public List<T> kbest()``` - return a sorted (smallest to largest) list of the k-largest elements. This must run in at worst `O(k log k)` time. The method must not clobber the state of your class. This means that if you run this method twice in a row, it should return the same values.

Your `KBestCounter.java` class must implement the provided interface `KBest.java`.

Use a priority queue to implement this functionality. We suggest using the built-in `java.util.PriorityQueue`.

As always, feel free to implement your own tester file to ensure proper functionality.

## Problem 2 - Huffman Coding - 34 points

Place the main method for your program in the file Huffman.java.  The program should take as a single command line argument the name of a file which contains some text.  You may upload a file of your choice for testing purposes (we will test with our own examples as well).

The program should then compute the frequencies of the characters in that text and internally build the Huffman tree.  You will likely need to build additional classes to facilitate construction of the tree.  You should then print out in the console window a table of characters along with the corresponding Huffman codes.

The program should then prompt the user to enter a code of 0's and ones. When you press enter the program should decode your input based on the Huffman tree that you constructed from the original input file. If there is an error in the code, print error, rather than the decoded message.

Finally, the program should prompt the user for a series of characters. When the user presses enter, those characters should be converted into the corresponding Huffman code based on the Huffman tree built from the original file input.

