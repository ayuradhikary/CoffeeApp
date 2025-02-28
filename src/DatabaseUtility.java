import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public static boolean writeTableName(String databaseName, String tableName, String... columnName) {
        Path databaseFolder = Paths.get("databases");
        Path path = databaseFolder.resolve(databaseName + ".txt");
        File fileName = path.toFile();

        if (columnName.length == 0) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Check if the table is already created, avoid writing column names again
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                Utility.logMessageWithArgument("Error reading file: " + e.getMessage());
            }

            boolean tableExists = false;
            for (String line : lines) {
                if (line.startsWith("Table: " + tableName)) {
                    tableExists = true;
                    break;
                }
            }

            // If the table doesn't exist, write the table and column names
            if (!tableExists) {
                writer.write("Table: " + tableName);
                writer.newLine();

                // Write column names horizontally, separated by " | "
                writer.write(String.join(" | ", columnName));
                writer.newLine();

                writer.write("------------------------------------------------");
                writer.newLine();
            }

            return true;
        } catch (IOException e) {
            Utility.logMessageWithArgument("Error creating table: " + e.getMessage());
        }
        return false;
    }



    public static boolean writeColumnNamesAndValues(String tableName, String dbName, String[] columnNames, HashMap<String,Object> values) {
        Path databaseFolder = Paths.get("databases");
        Path path = databaseFolder.resolve(dbName + ".txt");
        File fileName = path.toFile();

        List<String> lines = new ArrayList<>();
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            while ((line = reader.readLine()) != null){
                lines.add(line);
            }

        }catch (IOException e){
            Utility.logMessageWithArgument("cannot insert data: "+e.getMessage());
        }

        boolean tableFound = false;
        int tableStart = -1;
        int columnEnd = -1;

        for(int i = 0; i< lines.size(); i++){
           if(lines.get(i).startsWith("Table: "+tableName)){
               tableFound = true;
               tableStart = i;
               break;
           }
        }

        if(tableFound){
            columnEnd = tableStart + 1;

            while (columnEnd < lines.size() && !lines.get(columnEnd).startsWith("-")){
                columnEnd++;
            }
            StringBuilder newRow = new StringBuilder();
            for (int i = 0; i < columnNames.length; i++) {
                // Insert corresponding value or NULL if value is missing
                Object value = values.get(columnNames[i]);
                if (value != null) {
                    newRow.append(value).append(" | ");
                } else {
                    newRow.append("NULL").append(" | ");
                }
            }
            // Remove trailing " | " and add newline for the row
            if (!newRow.isEmpty()) {
                newRow.setLength(newRow.length() - 3);  // Remove the last " | "
            }
            newRow.append("\n");

            // Add the new row under the column header
            //passing index and new row value
            lines.add(columnEnd + 1, newRow.toString());

            // Write the updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
                return false;
            }

            return true;
        }else {
            System.out.println("Table " + tableName + " not found.");
            return false;
        }
    }
}
