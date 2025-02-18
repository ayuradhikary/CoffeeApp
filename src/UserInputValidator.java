public class UserInputValidator {
    MenuManager menuManager;
    public UserInputValidator(){
        menuManager = new MenuManager();
    }

    boolean checkEquals(String response){
        if(response == null){
            return false;
        }
        else if(response.equalsIgnoreCase("q")){
            return false;
        }
        else return menuManager.checkCoffee(response);
    }
}
