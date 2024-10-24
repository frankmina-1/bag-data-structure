
/**
 * Driver.java - test program for our ArrayBag
 */
public class Driver
{
    public static void main(String[] args)
    {
        //create an ArrayBag
        BagInterface names = new ResizableArrayBag<String>(3);
        
        //add some items (should work)
        names.add("Doug");
        names.add("Tia");
        names.add("Seiji");  
        
        displayBag(names);
        
        //add more names (should double and work)
        names.add("Jazmin");
        names.add("Carlos");
        names.add("Sofia");
        
        displayBag(names);
        
        //add to a full bag (should double and work)
        names.add("Zachary");
        displayBag(names);
        
        //remove a name
        names.remove();     //should remove Sofia
        displayBag(names);
        
        //remove a specific name
        names.remove("Tia");
        displayBag(names);
        
        //try to add Zachary again (should work)
        names.add("Zachary");
        displayBag(names);
        
        //add second instance of Doug
        names.add("Doug");
        System.out.println("Doug appears " + names.getFrequencyOf("Doug") + " times.");
        
        boolean found = names.contains("Tia");
        if (found)
            System.out.println("Tia is in the bag.");
        else
            System.out.println("Tia is not in the bag.");
            
        displayBag(names);
        
        /*
        //create anotherArrayBag
        BagInterface numbers = new ResizableArrayBag<Double>();
        numbers.add(45.13);
        numbers.add(32.88);
        numbers.add(11.845);
        numbers.add(-134.8891);
        
        displayBag(numbers);
        
        //create another ArrayBag
        BagInterface people = new ResizableArrayBag<Person>(10);
        
        people.add(new Person("Alice", 19));
        people.add(new Person("Bob", 22));
        people.add(new Person("Carol", 20));
        people.add(new Person("David", 18));
        
        displayBag(people);
        
        people.remove(new Person("Bob", 22));   //flag for review
        displayBag(people);
        */
        

    }
    
    public static void displayBag(BagInterface theBag)
    {
        System.out.println("Displaying contents of the bag...");
        
        //use toArray() to get the contents
        Object[] data = theBag.toArray();
        
        //use for loop to print each entry
        for (int i = 0; i < data.length; i++)
        {
            System.out.println(data[i]);
        }
    }
}
