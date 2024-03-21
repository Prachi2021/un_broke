package com.prachi.un_broke;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
    private Connection conn;
    private Statement stmt;


    public DbConnection() {
        try{
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unbroke", "root", "root2024");
            this.stmt = conn.createStatement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement getStmt() {
        return stmt;
    }

    public Connection getConn() {return conn;}

    public ResultSet getData(String table){
        String sql = "SELECT * FROM "+table;
        try{
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
