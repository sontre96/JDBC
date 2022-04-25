package jdbcapp.ExamJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    public static Connection getSQLServerConnection() throws SQLException, ClassNotFoundException {
        String hostName = "127.0.0.1";
        String instanceName = "ADMIN";
        String database = "mycompany";
        String userName = "sa";
        String password ="sontre96";
        return getSQLServerConnection(hostName, instanceName, database, userName, password);
    }
    public static Connection getSQLServerConnection(String hostName,String instanceName, String databaseName,
                                                    String userName, String password)
            throws SQLException, ClassNotFoundException{

//Load drive
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String connectionString = "jdbc:sqlserver://"+hostName+":1433" +";instance ="+instanceName
                +";databaseName ="+databaseName;

        Connection connection = DriverManager.getConnection(connectionString, userName,password);
        return connection;
    }
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//
//        Connection connection = getSQLServerConnection();
//        if(connection!=null){
//            System.out.println("Ket noi CSDL thanh cong");
//        }
//    }
}