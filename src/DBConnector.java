import java.security.PublicKey;
import java.util.List;

public class DBConnector {

    static ConcreteDatabase concreteDatabase;
    static Table table;

    public static ConcreteDatabase connectToDb(String dbName) {
        concreteDatabase = ConcreteDatabase.getDatabase(dbName);
        return concreteDatabase;
    }

    public static Table connectToTable(String tableName){
        if(!DatabaseUtility.checkTableExists(concreteDatabase.databaseName, tableName)){
            table = new Table(concreteDatabase);
            table.createTable(tableName);
            return table;
        }else {
            table =  new Table(concreteDatabase);
            return table;
        }
    }

}
