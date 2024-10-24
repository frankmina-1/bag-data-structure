import java.util.Arrays;

/**
 * Write a description of class shoppingCart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class shoppingCart extends ResizableArrayBag
{
    private BagInterface<Item> cart;
    int budget;
    
    //constructor with default budget
    public shoppingCart(){
        cart = new ResizableArrayBag<Item>();
        budget = 1000;
    }
    
    /**
     * Method addMultiple uses the arraybag's add function but manipulates it to add multiples of the same item
     *
     * @param anItem - the item to be added
     * @param num - how many times the item should be added
     * @param theBag - the shopping cart the item should be added to
     */
    public void addMultiple(Item anItem, int num, BagInterface theBag){
        for (int i = 0; i < num; i ++){
            theBag.add(anItem);
        }
    }
    
    /**
     * Method checkout displays the contents of the shopping cart along with 
     * the total price of the items.
     *
     * @param theBag - the shopping cart
     */
    public void checkout(BagInterface theBag){
        int total = 0;
        Object[] cartArray = theBag.toArray();
        System.out.println("Scanning items...");
        for(int i = 0; i < cartArray.length; i++){
            System.out.println(cartArray[i]);
            Item item = (Item)cartArray[i];
            int price = item.getPrice();
            total += price;
        }

        System.out.println("The total is: " + total + " cents");
    }


    /**
     * Method setBudget sets the budget
     *
     * @param a - the amount of money to be set as the budget
     */
    public void setBudget(int a){
        budget = a;
    }

    /**
     * Method checkBudget cycles through the shopping cart, adding the prices 
     * together to get a total. if the total is greater than the budget that
     * was set, it will remove items until the shopper is within their budget. 
     *
     * @param theBag - the shopping cart
     */
    public void checkBudget (BagInterface theBag){
        int total = 0;
        boolean withinBudget = true;
        Object[] cartArray = theBag.toArray();
        for(int i = 0; i < cartArray.length; i ++){
            Item item = (Item)cartArray[i];
            int price = item.getPrice();
            total += price;
            if(total > budget){
                System.out.println("You are over budget! Removing an item...");
                theBag.remove();
            }
        }
        System.out.println("You are safely within your budget.");
        }
    }


