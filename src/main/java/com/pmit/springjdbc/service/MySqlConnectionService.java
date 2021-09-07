package com.pmit.springjdbc.service;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class MySqlConnectionService {

    public static Connection getConnection()
    {
        String url,user_id,pass;
        url="jdbc:mysql://localhost:3306/learn";
        user_id="root";
        pass="xyz123X!";
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,user_id,pass);
        }

        catch (ClassNotFoundException | SQLException c)
        {
            c.printStackTrace();
        }

        return con;
    }
}
