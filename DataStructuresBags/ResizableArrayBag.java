
/**
 * ResizableArrayBag.java - an array-based implementation of our Bag ADT that can be resized
 */
import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T>
{
    //declare our data properties
    private T[] bag;        //the array holding our entries
    private int numEntries; //how many entries currently in the Bag
    private final static int DEFAULT_CAPACITY = 25;
    private final static int MAX_CAPACITY = 1000;       //maximum allowable size of a Bag
    private boolean integrityOK;        //flag value to signal data is not corrupted
    
    //define constructors
    
    //default constructor - creates empty bag with DEFAULT_CAPACITY
    public ResizableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }
    
    //parameterized constructor - creates empty bag with given capacity (if allowed)
    public ResizableArrayBag(int capacity)
    {
        integrityOK = false;        //set to false at start of constructor
        
        //check if capacity is too small
        if (capacity <= 0)
        {
            bag = (T[]) new Object[DEFAULT_CAPACITY];    //use default capacity instead
        } else {
            checkCapacity(capacity);            //check to make sure not too big
            bag = (T[]) new Object[capacity];   //capacity is okay
        } 
        
        numEntries = 0;     // initially bag is empty
        integrityOK = true; // made it through -- set integrity flag to true
    }
    
    //define Bag ADT methods
    
    //checkIntegrity() - helper method to ensure bag is okay to work with
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("Data is corrupt.");
    }
    
    //isArrayFull() - helper method for add
    private boolean isArrayFull()
    {
        return (numEntries >= bag.length);
    }
    
    //checkCapacity() - private helper method to ensure we are not attempting to create bag too big
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempted to create a bag whose capacity exceeds max allowed value.");
    }
    
    //doubleCapacity() - private helper method to double capcity of array when full
    private void doubleCapacity()
    {
        int newCapacity = bag.length * 2;   
        checkCapacity(newCapacity);     //helper method to make sure new capacity is ok
        
        bag = Arrays.copyOf(bag, newCapacity);
    }
    
    //Adds a new entry to this bag
    // @param newEntry - the object to be added as a new entry
    // @return TRUE if add was successful, FALSE otherwise
    public boolean add(T newEntry)
    {
        checkIntegrity();
        
        //check if bag is full
        if(isArrayFull())
            doubleCapacity();       //can't add, so resize the array
        
        //if room, add to next slot and increment numEntries
        integrityOK = false;
        bag[numEntries] = newEntry;
        numEntries++;  
        integrityOK = true;
        return true;
    }
    
    //Retrieves all entries that are in this bag
    // @return A newly allocated array of all the entries in this bag
    //  NOTE:  If the bag is empty, the returned array is empty
    public T[] toArray()
    {
        checkIntegrity();
        
        //create a new array of size numEntries
        T[] resultArray = (T[]) new Object[numEntries];
        
        //copy entries from bag into result array
        for (int i = 0; i < numEntries; i++)
        {
            resultArray[i] = bag[i];
        }
        
        return resultArray;
    }
    
    //Gets the current number of entries in this bag
    // @return The integer number of entries currently in the bag
    public int getCurrentSize()
    {
        return numEntries;  
    }
    
    //Sees whether the bag is empty
    // @return TRUE if the bag is empty, FALSE otherwise
    public boolean isEmpty()
    {
        return (numEntries == 0); 
    }
    
    //getIndexOf() - helper method, returns the index of first instance of given entry
    //  @param anEntry - the entry to look for
    //  @reutrns the index of entry if found, -1 if not found
    private int getIndexOf(T anEntry)
    {
        boolean found = false;
        int i = 0;
        int result = -1;
        while (!found && i < numEntries)
        {
            if (bag[i].equals(anEntry))     //remember to use equals() for objects
            {
                result = i;                 //found it, so save the index
                found = true;
            }
            i++;
        }
        
        return result;
    }
    
    //removeEntry() - helper method for removal
    //  PRE-CONDITION:  index < numEntries
    //  @param index - the index of the element to be removed
    //  @return the element that was removed from index
    private T removeEntry(int index)
    {
        if (index < 0)
            return null;
            
        integrityOK = false;
        
        //save element to remove at index
        T result = bag[index];
        
        //copy the last element into index
        bag[index] = bag[numEntries-1];
        
        //delete the last element
        bag[numEntries - 1] = null;
        
        numEntries--;
        
        integrityOK = true;
        return result;
    }
    
    
    //Remove (general, unspecified version) an item
    //Removes one unspecified entry from this bag, if possible
    // @return either the removed entry (if successful), or NULL
    public T remove()
    {
        return removeEntry(numEntries - 1);
    }
    
    //Remove (specific version)
    //Removes one occurrence of a given entry from the bag, if possible
    // @param anEntry - the entry to be removed
    // @return TRUE if successful, FALSE otherwise
    public boolean remove(T anEntry)
    {
        int index = getIndexOf(anEntry);    //grab the index of the entry
        T result = removeEntry(index);      //remove the entry at that index
        
        if (result == null)
            return false;
        else
            return true;
        
    }
    
    //Removes all entries from this bag
    public void clear()
    {
        while(!isEmpty())
            remove();
        
    }
    
    //Counts the number of times a given entry appears in this bag
    // @param anEntry - the entry to be counted
    // @return the number of times anEntry appears in the bag
    public int getFrequencyOf(T anEntry)
    {
        int i = 0;
        int result = 0;     //tally of occurrences
        while (i < numEntries)
        {
            if (bag[i].equals(anEntry))     //remember to use equals() for objects
            {
                result++;                 //found it, so add to our tally
            }
            i++;
        }
        
        return result;
    }
    
    //Tests whether this bag contains a given entry
    // @param anEntry - the entry to find
    // @return TRUE if the bag contains anEntry, FALSE otherwise
    public boolean contains(T anEntry)
    {
        //option 1:  use getFrequencyOf
        return (getFrequencyOf(anEntry) > 0);
        
        //option 2:  use getIndexOf
        //return (getIndexOf(anEntry) != -1);
    }
    
    
}
