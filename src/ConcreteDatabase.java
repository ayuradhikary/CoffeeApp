
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.io.IOException;

public class ConcreteDatabase implements Database{
    String databaseName;
    private static List<String> databases;
    private final String file = databaseName+".txt";

    public ConcreteDatabase(){
        databases = new ArrayList<>();
        loadDatabases();
    }

    @Override
    public void createDatabase(String databaseName) {
        this.databaseName = databaseName;
        Path databaseFolder = Paths.get("databases"); // results databaseFolder = /databases
        Path path = databaseFolder.resolve(this.databaseName + ".txt"); //this will result /databases/databaseName.txt
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                databases.add(databaseName);
                Utility.logMessageWithArgument("Database successfully created with Name: " + databaseName);
            } catch (IOException e) {
                Utility.logMessageWithArgument("Exception occurred while creating database: " + e.getMessage());
            }
        } else {
            Utility.logMessageWithArgument("Database already exists: " + databaseName);
        }
    }

    @Override
    public void showDatabase() {
        loadDatabases();
        if (databases.isEmpty()) {
            System.out.println("No databases created yet.");
        } else {
            System.out.println("Databases created so far:");
            for (String dbName : databases) {
                System.out.println(dbName);
            }
        }
    }

    private static void loadDatabases(){
        if (databases == null) {
            databases = new ArrayList<>();
        }
        Path directoryPath = Paths.get("databases");
        try(DirectoryStream<Path> databaseFileStream = Files.newDirectoryStream(directoryPath,"*.txt")){
            for(Path file: databaseFileStream){
                databases.add(file.getFileName().toString().replace(".txt", ""));
            }
        }catch(IOException e){
            Utility.logMessageWithArgument("error occured while reading file: "+e.getMessage());
        }
    }

    public static ConcreteDatabase getDatabase(String dbName) {
        loadDatabases();

        if (databases.contains(dbName)) {
            ConcreteDatabase db = new ConcreteDatabase();
            db.databaseName = dbName;
            return db;
        } else {
            ConcreteDatabase database = new ConcreteDatabase();
            database.createDatabase(dbName);
            database.databaseName = dbName;
            return database;
        }
    }



}
