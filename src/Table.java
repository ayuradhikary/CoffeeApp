import java.util.*;

public class Table {
    private String tableName;
    private List<String> columnNames;
    private HashMap<String,Object> values;
    private List<String> tables;
    ConcreteDatabase concreteDatabase;

    public Table(ConcreteDatabase concreteDatabase) {
        this.columnNames = new ArrayList<>();
        this.values = new HashMap<>();
        this.concreteDatabase = concreteDatabase;
    }

    public void createTable(String tableName){
        this.tableName = tableName;
        //check if database exists
        if(!DatabaseUtility.checkDatabaseExists(concreteDatabase.databaseName)){
            Utility.logMessagePrompt("the database does not exists!");
        }
        //check if table exists
        if(DatabaseUtility.checkTableExists(concreteDatabase.databaseName, tableName)){
            Utility.logMessagePrompt("table already exists");
        }
        //write table name
        if(!DatabaseUtility.writeTableName(concreteDatabase.databaseName, tableName)){
            Utility.logMessagePrompt("cannot create table, some problem exists");
        }
        Utility.logMessageWithArgument("Table created",tableName);
    }

//    public boolean addColumns(String tableName,Object... columnNames) {
//        for (Object column : columnNames) {
//            if (!this.columnNames.contains(column)) {
//                this.columnNames.add((String) column);
//                return true;
//            }
//        }
//        return false;
//    }

//    public void insert(String tableName,Object...inputValues){
//        if(inputValues.length != columnNames.size()){
//            Utility.logMessagePrompt("values and column did not match");
//        }
//        values.clear();
//
//        for(String columnName : columnNames){
//            Object value = inputValues[columnNames.indexOf(columnName)];
//            values.put(columnName,value);
//        }
//        if(!addToDatabase()){
//            Utility.logMessagePrompt("cannot insert the data");
//        }
//        Utility.logMessageWithArgument("Inserted values into table %s: %s", tableName, values.toString());
//    }

    public boolean addColumnsAndInsert(String tableName, String[] columnNames, Object... inputValues) {
        for (String column : columnNames) {
            if (!this.columnNames.contains(column)) {
                this.columnNames.add(column);
            }
        }

        if (columnNames.length != inputValues.length) {
            Utility.logMessagePrompt("Values and columns do not match.");
            return false;
        }

        values.clear();

        for (int i = 0; i < columnNames.length; i++) {
            values.put(columnNames[i], inputValues[i]);
        }

        if (!addToDatabase()) {
            Utility.logMessagePrompt("Cannot insert the data.");
            return false;
        }

        Utility.logMessageWithArgument("Inserted values into table %s: %s", tableName, values.toString());
        return true;
    }

    private boolean addToDatabase(){
        return DatabaseUtility.writeColumnNamesAndValues(columnNames, values, concreteDatabase.databaseName);
    }

}
