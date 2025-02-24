public class Utility {

    public static void logMessagePrompt(String message){
        System.out.println(message);
    }

    public static void logMessageWithArgument(String message,Object...args){
        String formattedMessage = String.format(message, args);
        System.out.println(formattedMessage);
    }


}
