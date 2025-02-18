import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddToMenu implements Addable{
    private Map<String,Double> coffeeMenu = new HashMap<>();
    private final String file = "menu.txt";

    @Override
    public void add(AddToMenuDTO addToMenuDTO) {
        coffeeMenu.put(addToMenuDTO.getCoffeeName(),addToMenuDTO.getCoffeePrice());

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){

            for (Map.Entry<String, Double> entry : coffeeMenu.entrySet()) {
                String formattedMessage = String.format("\"%s\", %.2f", entry.getKey(), entry.getValue());
                writer.write(formattedMessage);
                writer.newLine();
                System.out.println(formattedMessage);
            }

        }catch (IOException e){
            System.out.println("could not write to the file");
            e.printStackTrace();
        }
    }
}
