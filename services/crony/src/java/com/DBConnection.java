/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Divya
 */
class DBConnection {
     public static Connection connect()
	 {
		Connection conn=null;
		Statement stmt=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1:3306/cronydb";
			conn=DriverManager.getConnection(url,"root","");
            return conn;
		}
		catch(Exception e)
		{
		  System.out.println(e);
		}
		return conn;
	}


    
}
