import java.util.*;

public class Table {
    private String tableName;
    private String[] columnNames;
    private HashMap<String,Object> values;
    private List<String> tables;
    ConcreteDatabase concreteDatabase;

    public Table(ConcreteDatabase concreteDatabase) {
        this.columnNames = new String[0];
        this.values = new HashMap<>();
        this.concreteDatabase = concreteDatabase;
    }

    public void createTable(String tableName, String...columnName){
        this.tableName = tableName;
        //check if database exists
        if(!DatabaseUtility.checkDatabaseExists(concreteDatabase.databaseName)){
            Utility.logMessagePrompt("the database does not exists!");
            return;
        }
        //check if table exists
        if(DatabaseUtility.checkTableExists(concreteDatabase.databaseName, tableName)){
            Utility.logMessagePrompt("table already exists");
            return;
        }
        //write table name
        if(!DatabaseUtility.writeTableName(concreteDatabase.databaseName, tableName, columnName)){
            Utility.logMessagePrompt("cannot create table, some problem exists");
            return;
        }
        this.columnNames = columnName;
        Utility.logMessageWithArgument("Table created",tableName);
    }

    public boolean insert(String tableName, String[] columnNames, Object... inputValues) {

        if (columnNames.length != inputValues.length) {
            Utility.logMessagePrompt("Values and columns do not match.");
            return false;
        }

        values.clear();

        for (int i = 0; i < columnNames.length; i++) {
            values.put(columnNames[i], inputValues[i]);
        }

        if (!addToDatabase(tableName, columnNames)) {
            Utility.logMessagePrompt("Cannot insert the data.");
            return false;
        }

        Utility.logMessageWithArgument("Inserted values into table %s: %s", tableName, values.toString());
        return true;
    }

    private boolean addToDatabase(String tableName, String[] columnNames){
        return DatabaseUtility.writeColumnNamesAndValues(tableName,concreteDatabase.databaseName,columnNames,values);
    }

}
