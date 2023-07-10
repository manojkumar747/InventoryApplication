package dao;
import model.*;
import java.io.*;
import java.sql.*;
import java.sql.Statement;

import ConnectionManager.*;
public class LoginDao {
	public boolean validate(Login login)throws IOException,SQLException, ClassNotFoundException {
		String username=login.getUSERNAME();
		String password=login.getPASSWORD(); 
		
		ConnectionManager1 conn=new ConnectionManager1();
		Connection con=conn.establishConnection();
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM LOGIN");
		
		while(rs.next()) {
			if(username.equals(rs.getString("USERNAME"))&& password.equals(rs.getString("PASSWORD"))) {
				conn.closeConnection();
				
				return true;
				
			}
		}
		conn.closeConnection();
		return false;
	}

}
