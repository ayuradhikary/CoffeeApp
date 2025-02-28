import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class DatabaseUtility {

    public static boolean checkDatabaseExists(String databaseName){
        Path databaseFolder = Paths.get("databases");
        Path path = databaseFolder.resolve(databaseName + ".txt");
        return Files.exists(path);
    }

    public static boolean checkTableExists(String databaseName, String tableName){
        Path databaseFolder = Paths.get("databases");
        Path path = databaseFolder.resolve(databaseName+".txt");
        File fileName = path.toFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().equals("Table: " + tableName)) {
                return true;
            }
        }
    } catch (IOException e) {
            Utility.logMessageWithArgument("Error checking if table exists: "+e.getMessage());
    }
        return false;
    }

    public static boolean writeTableName(String databaseName,String tableName){
        Path databaseFolder = Paths.get("databases");
        Path path = databaseFolder.resolve(databaseName+".txt");
        File fileName = path.toFile();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))){
            writer.write("Table: " + tableName);
            writer.newLine();
            return true;
        }catch (IOException e){
            Utility.logMessageWithArgument("Error creating table: "+e.getMessage());
        }
        return false;
    }

    public static boolean writeColumnNamesAndValues(String tableName,List<?> columnList, HashMap<?, ?> values, String dbName) {
        Path databaseFolder = Paths.get("databases");
        Path path = databaseFolder.resolve(dbName + ".txt");
        File fileName = path.toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
           // if(!checkTableExists(dbName,tableName)){
                for (Object column : columnList) {
                    String columnName = (String) column;
                    writer.write(columnName);
                    writer.write("\t");
                }
                writer.newLine();
            //}
            for (Object column : columnList) {
                String columnName = (String) column;
                Object columnValue = values.get(columnName);

                if (columnValue == null) {
                    writer.write("NULL");
                } else {
                    writer.write(columnValue.toString());
                }

                writer.write("\t");
            }

            writer.newLine();
            writer.write("---------------------------------------------------------------------------------");
            writer.newLine();
            return true;
        } catch (IOException e) {
            Utility.logMessagePrompt("Could not add columns and their respective values to the database");
        }
        return false;
    }

}
