
/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
    private String name;
    private int age;
    
    public Person (String n, int a)
    {
        name = n;
        age = a;
    }
    
    public String toString()
    {
        return name + ", " + age;
    }
    
    public boolean equals(Object other)
    {
        //make sure not null and classes match
        if (other == null || getClass() != other.getClass())
            return false;
        
        //cast as Person object    
        Person op = (Person) other;
        return (this.name.equals(op.name) && this.age == op.age);
    }
}
