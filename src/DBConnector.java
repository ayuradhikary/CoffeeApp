
public class DBConnector {

    static ConcreteDatabase concreteDatabase;
    static Table table;

    public static ConcreteDatabase connectToDb(String dbName) {
        concreteDatabase = ConcreteDatabase.getDatabase(dbName);
        return concreteDatabase;
    }

    public static Table connectToTable(String tableName, String...columnNames){
        if(!DatabaseUtility.checkTableExists(concreteDatabase.databaseName, tableName)){
            table = new Table(concreteDatabase);
            table.createTable(tableName, columnNames);
            return table;
        }else {
            table =  new Table(concreteDatabase);
            return table;
        }
    }


}
