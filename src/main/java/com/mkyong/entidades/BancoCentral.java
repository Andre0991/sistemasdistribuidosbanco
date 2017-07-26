package com.mkyong.entidades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BancoCentral {
	
		public static void main(String[] args) throws Exception {
			
			
			String url = "jdbc:mysql://localhost:3306/banco_central";
			String uname = "root";
			String pass = "senha@"; 
			String query = "select url_banco from new_table where id_banco=2";
			
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection(url,uname,pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			rs.next();
			String name = rs.getString("url_banco");
			System.out.println(name);
			st.close();
			con.close();
			

		}

	}
	

