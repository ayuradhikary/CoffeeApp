public class MenuManager {
    ReadMenu readMenu;

    public MenuManager(){
        readMenu = new ReadMenu();
    }

    public void displayMenu(){
        readMenu.displayMenu();
    }

    public void addItem(String item, double price){
       AddToMenuDTO addToMenuDTO = new AddToMenuDTO(item,price);
       AddToMenu addToMenu = new AddToMenu();
       addToMenu.add(addToMenuDTO);
    }

    public void removeItem(String item, double price){
        Utility.logMessagePrompt("removes from the menu");
    }

    public double getPrice(String coffeeName){
        double coffeePrice = readMenu.getPrice(coffeeName);
        if (coffeePrice == -1.0) {
            Utility.logMessagePrompt("No coffee found");
        } else {
            return coffeePrice;
        }
        return coffeePrice;
    }

    public boolean checkCoffee(String coffeeName){
        return readMenu.checkCoffeeExists(coffeeName);
    }

}
