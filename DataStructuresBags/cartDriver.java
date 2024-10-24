public class cartDriver
{
    public static void main(String[] args){
        shoppingCart cart = new shoppingCart();
        Item[] items = {new Item("Coca Cola", 500),
                new Item("Chips Ahoy", 900),
                new Item("Bananas", 760),
                new Item("Milk", 1000)};
        for(int i = 0; i < items.length; i++){
            Item nextItem = items[i];
            cart.add(nextItem);
        }
        
        Item water = new Item("Water",1000);
        
        //shows that addMultiple works
        cart.addMultiple(water,3,cart);
        cart.checkout(cart);
        
        //shows that the call to remove a specific item works
        cart.remove(water);
        cart.checkout(cart);
        
        //test that the budget functions work properly, 
        //also shows that items can be removed at random
        cart.setBudget(1500);
        cart.checkBudget(cart);
        cart.checkout(cart);
        
        //remove another item at random for fun
        cart.remove();
        cart.checkout(cart);
        
    }
}
