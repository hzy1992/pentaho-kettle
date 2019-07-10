import org.pentaho.di.core.KettleClientEnvironment;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleDatabaseException;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.logging.LoggingObjectInterface;
import org.pentaho.di.core.logging.LoggingObjectType;
import org.pentaho.di.core.logging.SimpleLoggingObject;
import org.pentaho.di.core.plugins.DatabasePluginType;
import org.pentaho.di.core.row.RowMeta;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.row.value.ValueMetaFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TestDatabase {
    public static final LoggingObjectInterface loggingObject = new SimpleLoggingObject(
            "Database factory", LoggingObjectType.GENERAL, null );
    public static void main(String[] args) throws KettleException {
        DatabasePluginType.getInstance().searchPlugins();
        KettleClientEnvironment.init();
        DatabaseMeta databaseMeta1 = new DatabaseMeta("a", "ORACLE", "JDBC", "10.1.2.222", "orcl", "1521", "system", "123456");
        Database database1 = new Database(loggingObject, databaseMeta1);
        database1.connect();
        String[] testtb_10s = database1.getPrimaryKeyColumnNames("EXFSYS","NewTable");
        System.out.print(testtb_10s.length);

    }

    private static String getQueryTableName(String tablename){
        String name = "";
        if(tablename.contains(".")) {
            for (String s : tablename.split("\\.")) {
                if (name.length() != 0) {
                    name += ".";
                }
                name += ("\"" + s + "\"");
            }
        }else {
            name = "\""+tablename + "\"";
        }
        return name;
    }
}
