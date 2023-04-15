/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp2dpbo;

// import
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
        
/**
 *
 * @author anand
 */

// property & constructor
public class dbConnection {
    // statement buat eksekusi query
    public Statement stmt = null;
    // connection ke db nya
    private Connection conn = null;
    
    // konstruktor
    public dbConnection(){
        String ConAddress = "jdbc:mysql://localhost/db_album_kpop";
        String user = "root";
        String pass = "";
        //bikin koneksi
        connect(ConAddress, user, pass);
    }
    
    // connect method
    // koneksi ke database  nya
    private void connect(String ConAddress, String username, String pass){
        try{
            // manggil class jdbc
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(ConAddress, username, pass);
            stmt = conn.createStatement();
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    // selectQuery method
    public ResultSet selectQuery(String sql){
        try{
           
            stmt.executeQuery(sql);
            // ini bakal return resultSet (nampung data hasil query)
            return stmt.getResultSet();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    // updateQuery method, ini dipake buat insert update delete
    public int updateQuery(String sql){
        try{
            // bakal return nilai baris yg kerubah
            return stmt.executeUpdate(sql);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    // getStatement method
    public Statement getStatement(){
        return stmt;
    }
    
}
