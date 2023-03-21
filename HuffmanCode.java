/*
 * This is my class to generate the Huffman code and return a hashMap of the codes for each char
 * takes in a hashmap of freqeuncies associated with characters
 * creates HUffman Node objects of all of them in heap order
 * constructs the tree then egernates the code
 * 
*/
import java.util.*;
public class HuffmanCode
{
    //instance variables for this class: 

    
    //root of tree 
    public static HuffmanNode root;
    //need a hashMap that will tell the code for each variables
    private HashMap< Character, String> codeMap = new HashMap<>();
    
   
    /*
     * This is the implementation for a HUffman Node
     * Each Node will have a character, a frequency, a left child and a right child
    */

    public static class HuffmanNode implements Comparable<HuffmanNode>
    {
        int frequency; // amount of times char is repeated
        char c; // 
    
        HuffmanNode left; 
        HuffmanNode right; 
    
        //two constructors:
        public HuffmanNode(char c, int f)
        {
            this(c, f, null, null);
        }
    
        public HuffmanNode(char c, int f, HuffmanNode left, HuffmanNode right)
        {
            this.c = c; 
            this.frequency = f;       
            this.left = left; 
            this.right = right; 
        }
        
        public int compareTo(HuffmanNode node){
            return this.frequency - node.frequency;
        }
    }//end class HuffmanNode
    
    
    // building the tree class which will take in the HashMap from Huffman.java
    //returns the root of the tree
    public HuffmanNode generateHuffmanTree(HashMap<Character, Integer> freqMap)
    {
        PriorityQueue<HuffmanNode> huffmanHeap = generateHeap(freqMap);
        
        //now we go through a loop to generate the code 
        if(huffmanHeap.size() > 0)
        {
            while(huffmanHeap.size() > 1)
            {
                HuffmanNode currMin = huffmanHeap.peek();
                huffmanHeap.remove();
                
                HuffmanNode penultimateMin = huffmanHeap.peek(); 
                huffmanHeap.remove();
                
                //sum the frequencies
                int sumFreq = currMin.frequency + penultimateMin.frequency;
                //create tmpNode
                HuffmanNode tmpNode = new HuffmanNode('-', sumFreq);
                //set children and assign to root
                tmpNode.left = currMin; 
                tmpNode.right = penultimateMin; 
                root = tmpNode; 
                //insert back into the heap
                huffmanHeap.offer(tmpNode);
            }//end while loop 
            
            // now there will only be one Node in heap so return that node
            return huffmanHeap.remove();
        }//end if
        //if not greater than 0 return null
        return null;
        
    }//end generateHuffmanTree
    
    private PriorityQueue<HuffmanNode> generateHeap(HashMap<Character, Integer> freqMap)
    {
        PriorityQueue<HuffmanNode> heap = new PriorityQueue<>();
        //use a for each loop to generate the heap
        for(Character c : freqMap.keySet())
        {
            //create a node 
            HuffmanNode node = new HuffmanNode(c, freqMap.get(c));
            //put node in heap 
            heap.offer(node);
        }//end for loop 
        
        return heap;
    }//end generateHeap
    
    //recrusive method that generates a code for each cahracter
    //stringBuilder will make it easier to build the codes for both sides
    //easily appendible
    
    
    public HashMap< Character, String> generateCode(HuffmanNode node, StringBuilder code)
    {
        //code map to return 
       
        //check if the node is null or note
        if(node != null)
        {
           //if the children of the node are null then add to the hashmap
           if(node.left == null && node.right == null)
           {
               
               String codeString = code.toString();
               codeMap.put(node.c, codeString);
           }
           else
           {
             
               //first traverse left then traverse to the right 
               //add 0 for going left
               code.append('0');
               generateCode(node.left, code);
               //take off this last bit because otherwise it keeps adding two bits
               code.deleteCharAt(code.length()-1);
               

               //right code 
               //add 1 for going right
               code.append('1'); 
               generateCode(node.right, code);
               //take off
               code.deleteCharAt(code.length()-1);
               
           }
        }
        return codeMap;
    }//end generate code 
    
    
    
    // next method allows for code to Char conversion:
    public String codeToChar(String input, HuffmanNode root) throws NumberFormatException
    {
        String chars = "";
        HuffmanNode tmpNode = root;
        
        for(int i = 0; i < input.length(); i++)
        {
            String currSubstring = input.substring(i,i+1);
            int currInt = Integer.parseInt(currSubstring);
           
            if(currInt == 0)
            {
                tmpNode = tmpNode.left;
                if(tmpNode.left == null && tmpNode.right == null)
                {
                    chars += Character.toString(tmpNode.c);
                    tmpNode = root;
                }
            }
            else 
            {
                if(currInt == 1)
                {
                    tmpNode = tmpNode.right;
                    if(tmpNode.left == null && tmpNode.right == null)
                    {
                        chars += Character.toString(tmpNode.c);
                        tmpNode = root;
                    }
                }// end if (currInt == 1)
                else 
                {
                    return("error in input");
                }//end else
                
            }//end else
        }//end for loop
        return chars;
    }//end codeToChar
    
    public String inputToCode(HashMap<Character, String> map, String input)
    {
        String output = "";
        for(int i = 0; i<input.length(); i++)
        {
            char currChar = input.charAt(i);
            if(map.containsKey(currChar))
            {
                //System.out.println("TRUE");
                output += map.get(currChar);
                
            }//end if containsKey
            else
            {
                return("Bad input, a character is not found through the text provided");
            }
        }
        return output;
    }
    
    
    
}