public class UserInputValidator {
    MenuManager menuManager;
    public UserInputValidator(MenuManager menuManager){
        this.menuManager = menuManager;
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
