import java.util.Scanner;

public class Utility {
    static Scanner userInputScanner = new Scanner(System.in);

    public static void logMessagePrompt(String message){
        System.out.println(message);
    }

    public static void logMessageWithArgument(String message,Object...args){
        String formattedMessage = String.format(message, args);
        System.out.println(formattedMessage);
    }

    public static String getStringInput(){
        String useStringInput = userInputScanner.nextLine();
        return useStringInput;
    }


}
