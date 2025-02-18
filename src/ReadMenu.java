import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ReadMenu {
    private  Map<String, Double> coffeeMenu;
    private final String filePath = "menu.txt";

    public ReadMenu(){
        coffeeMenu = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String coffeeName = parts[0].replace("\"", "");
                    double price = Double.parseDouble(parts[1]);
                    coffeeMenu.put(coffeeName, price);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void displayMenu(){
        coffeeMenu.forEach((coffeeName, price) -> System.out.println(coffeeName + " - " + price));
    }

    public double getPrice(String coffeeName) {
        if (coffeeName == null) {
            return -1.0;
        }
        for (String key : coffeeMenu.keySet()) {
            if (key.equalsIgnoreCase(coffeeName)) {
                return coffeeMenu.get(key);
            }
        }
        return -1.0;
    }

    public boolean checkCoffeeExists(String coffeeName){
        if (coffeeName == null) {
            return false;
        }

        for (String key : coffeeMenu.keySet()) {
            if (key.equalsIgnoreCase(coffeeName)) {
                return true;
            }
        }
        return false;
    }

}

