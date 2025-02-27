import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static boolean writeColumnNamesAndValues(List<?> columnList, HashMap<?, ?> values, String dbName) {
        Path databaseFolder = Paths.get("databases");
        Path path = databaseFolder.resolve(dbName+".txt");
        File fileName = path.toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            if (fileName.length() == 0 || !hasColumnNames(fileName, columnList)) {
                for (Object column : columnList) {
                    String columnName = (String) column;
                    writer.write(columnName);
                    writer.write("\t");
                }
                writer.newLine();
            }

            for (Object column : columnList) {
                String columnName = (String) column;
                Object columnValue = values.get(columnName);
                writer.write(columnValue.toString());
                writer.write("\t");
            }
            writer.newLine();
            return true;
        } catch (IOException e) {
            Utility.logMessagePrompt("Could not add columns and their respective values to the database");
        }
        return false;
    }

    private static boolean hasColumnNames(File dbFile, List<?> columnList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile))) {
            String line = reader.readLine();
            if (line != null) {
                String[] existingColumns = line.split("\t");
                if (existingColumns.length == columnList.size()) {
                    for (int i = 0; i < columnList.size(); i++) {
                        if (!existingColumns[i].equals(columnList.get(i))) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        } catch (IOException e) {
            Utility.logMessageWithArgument("Error reading file to check columns: ", e.getMessage());
        }
        return false;
    }
}
