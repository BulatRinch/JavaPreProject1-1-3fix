package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    private static Util dbInstance;
    private static Connection con;

    private Util () {

    }

    public static Util getInstance(){
        if(dbInstance==null){
            dbInstance= new Util();
        }
        return  dbInstance;
    }


    public  Connection getConnection() throws SQLException {

        if(con==null || con.isClosed()){
            try {
                String URL = "jdbc:mysql://localhost:3306/mydbtest";
                String USERNAME = "root";
                String PASSWORD = "root";
                con = DriverManager.getConnection( URL, USERNAME, PASSWORD );
            } catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return con;
    }







}



/*
public class Database {

    private static Database dbIsntance;
    private static Connection con ;
    private static Statement stmt;


    private Database() {
      // private constructor //
    }

    public static Database getInstance(){
    if(dbIsntance==null){
        dbIsntance= new Database();
    }
    return dbIsntance;
    }

    public  Connection getConnection(){

        if(con==null){
            try {
                String host = "jdbc:derby://localhost:1527/yourdatabasename";
                String username = "yourusername";
                String password = "yourpassword";
                con = DriverManager.getConnection( host, username, password );
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return con;
    }



 */