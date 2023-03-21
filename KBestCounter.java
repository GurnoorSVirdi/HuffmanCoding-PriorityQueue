/*
 * 
 * K-best Counter class 
 * Gurnoor Singh Virdi 
 * April 5th, 2020
*/

import java.util.PriorityQueue;
import java.util.List; 
import java.util.ArrayList;
public class KBestCounter<T extends Comparable<? super T>> implements KBest<T>
{
    //this integer will be a global var to rep the number of largest values
    private int kLargestValues; 
    //using a PriorityQueue to implement a min-heap object for the values
    private PriorityQueue<T> maxHeap;

    //constuctor
    public KBestCounter(int k)
    {
        this.kLargestValues = k;
        maxHeap = new PriorityQueue<>(k);
        
    }//end constructor
    
    
    //count method impelem 
    public void count(T x)
    {
        //initial check to make sure the Heap is not more than K as specified in ReadMe
        if(maxHeap.size() < kLargestValues)
        {
            //if true then add the element
            maxHeap.add(x);
        }
        //if the heap is full then do the following: 
        // first create peek into the Heap to see if element is larger than the min item
        //if it is then replace the item with the element
        else
        {
            T minItem = (T)maxHeap.element();
            if(minItem.compareTo(x) <  0)
            {
                maxHeap.remove();
                maxHeap.add(x);
            }//end if 
        }//end else
    }//end count
    
    //the kBest Method impelementation
    public List<T> kbest()
    {
        // the list to return of k best elements
        ArrayList<T> listKBest = new ArrayList<>();
        
        // int to rememebr the size of original heap
        int HeapSize = maxHeap.size(); 
        
        //create a copy of the Heap for reseting the original heap 
        PriorityQueue<T> Heap2  = new PriorityQueue<>(maxHeap);
        
        for(int i = 0; i < HeapSize; i ++)
        {
            listKBest.add(i, maxHeap.poll());
        }// end for loop
        
        //reset to OG heap
        maxHeap = Heap2;
        
        return listKBest;
    }//end kbest
    
}//end class