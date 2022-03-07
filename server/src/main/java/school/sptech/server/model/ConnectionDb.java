package school.sptech.server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    private Connection con ;
    private String hostName;
    private String userName;
    private String password;
    private String url;
    private String jdbcDriver;
    private String dataBaseName;
    private String dataBasePrefix;
    private String dabaBasePort;

    public ConnectionDb() {
        hostName = "localhost";
        userName = "homebox";
        password = "Homebox265@";
        jdbcDriver = "com.mysql.cj.jdbc.Driver";
        dataBaseName = "homebox";
        dataBasePrefix = "jdbc:mysql://";
        dabaBasePort = "3306";
        url = dataBasePrefix + hostName + ":"+dabaBasePort+"/" + dataBaseName ;


    }
    public Connection getConnection() {
        try {
            if (con == null) {
                Class.forName(jdbcDriver);
                con = DriverManager.getConnection(url, userName, password);
            } else if (con.isClosed()) {
                con = null;
                return getConnection();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
