/*
 * Gurnoor Singh Virdi 
 * This is my implementation to Problem two - Huffman coding 
 * Uni: gsv2110 
 * Date: April 5th, 2021 
 * 
 * The point of this class is to interact with the User through a scanner 
 * develop an array that reads a file through a bufferred reader and passes it
 * to another class - HuffmanTree Generator
 * Interects with the Huffman Tree Geneator class, which will have the Huffman code 
 * Also will question the reader with a prompt as to input symbols to deliver the proper HUffman code 
 * Will also take in binary bits to deliver the correct characters:
 * 
*/

import java.util.*;
import java.io.*;
//main Huffman Class that implements the main method
public class Huffman{
    
    //main method that will implement a buffered reader: 
    //takes in a single command line argument which will be the file name
    public static void main(String[] args)
    {
        try{
            String FileName =  args[0];
            BufferedReader fileReader = new BufferedReader(new FileReader(FileName));
            // setting up the next line 
            String nextLine;
            HashMap<Character, Integer> frequencyMap = new HashMap< Character, Integer>();
            
            //fill hashmap with all frequencies. 
            while((nextLine = fileReader.readLine()) != null)
            {
                for(int i = 0; i < nextLine.length(); i++)
                {
                    char charString = nextLine.charAt(i);
                    //if the map already has the specific character already
                    if(frequencyMap.containsKey(charString))
                    {
                        int currValue = frequencyMap.get(charString);
                        currValue++;
                        frequencyMap.replace(charString, currValue);
                    }
                    else
                    {
                        //add a key value pair
                        frequencyMap.put(charString, 1);
                        
                    }//end else
                }//end for loop
            }//end while loop 
        
            //call the HuffmanCode Class
            HuffmanCode HuffmanTree = new HuffmanCode();
            //set the root of the huffman tree equal to the root of the generated tree
            HuffmanTree.root = HuffmanTree.generateHuffmanTree(frequencyMap);
            
            //create a hashmap of char and string to get the codes 
            StringBuilder str = new StringBuilder();
            HashMap<Character, String> codeTable = HuffmanTree.generateCode(HuffmanTree.root, str);
            //print the codetable
            for(Character c : codeTable.keySet())
            {
                System.out.println("Character: " + c +
                               "| HuffmanCode: " + codeTable.get(c));
            
            }//end for loop 
            
            
            
            // here ask user for input but firt test 
            Scanner scan = new Scanner(System.in);
            System.out.println("input a binary bit series to determine the characters."+
                              "Only enter zeros and ones, and " +
                              "please enter sequences of the code provided."+
                              " Please do nott indlcude any other characters besides 0 and 1");
            String testCodeToChars = scan.nextLine();
            String outputChars = HuffmanTree.codeToChar(testCodeToChars, HuffmanTree.root);
            System.out.println("Here is the output: " + outputChars);
            
            
            //ask user to input a string of chars 
            System.out.println("Please enter a set of characters that were previously" + 
                              "in the file, and the program will tell you the Huffman"
                              + "Code for that sequence:");
            String charactersToCodeInput = scan.nextLine();
            
            //get output and print out code:
            String outputCode = HuffmanTree.inputToCode(codeTable, charactersToCodeInput);
            
            System.out.println("The huffman code for these characters is: " + outputCode);
        }// end try 
        catch(IOException e)
        {
            System.out.println("issue reading the file. ");
        }
        catch(ArrayIndexOutOfBoundsException f){
            System.out.println("no file or too many files provided.");
        }
        catch(NumberFormatException g)
        {
            System.out.println("you did not enter a binary sequence.");
        }
        
        
    }
}