/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamingConectDB;
import java.sql.*;

/**
 *
 * @author roxana
 */
public class ConectareBD {
    public static Connection bdConectare(){
        
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/streaming2", "root", "Roxi.15K");
            return conn;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }
    }
}
