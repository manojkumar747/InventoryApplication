package dao;
import java.sql.*;

import ConnectionManager.*;
import model.Product;

public class ProductDao {
	public void addProduct(Product product) throws SQLException, ClassNotFoundException {
		int id=product.getPRODUCTID();
		String name=product.getPRODECTNAME();
		int minsell=product.getMINSELL();
		int price=product.getPRICE();
		int quentity=product.getQUENTITY();
		
		ConnectionManager1 conn1=new ConnectionManager1();
		Connection con=conn1.establishConnection();
		
		String query="insert into PRODUCT(PRODUCTID,PRODUCTNAME,MINSELL,PRICE,QUANTITY)"+
				"values(?,?,?,?,?)";
	
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1,id);
		ps.setString(2, name);
		ps.setInt(3, minsell);
		ps.setInt(4,price);
		ps.setInt(5, quentity);
		
		ps.executeUpdate();
		conn1.closeConnection();
		
		
	}
	
	public void display() throws ClassNotFoundException,SQLException{
		ConnectionManager1 conn=new ConnectionManager1();
		Connection con=conn.establishConnection();
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM PRODUCT");
		
		while(rs.next()) {
			System.out.println("-----------------------------");
			System.out.println(rs.getString("PRODUCTNAME")+"   "+rs.getInt("PRODUCTID")+"   "+
						rs.getInt("MINSELL")+"   "+rs.getInt("PRICE")+"  "+rs.getInt("QUANTITY"));
			System.out.println("-----------------");
		}
		conn.closeConnection();
	}
	
	
	public boolean checkQuantity(int id,int quantity) throws ClassNotFoundException, SQLException {
		ConnectionManager1 conn=new ConnectionManager1();
		Connection con=conn.establishConnection();
		
		PreparedStatement st=con.prepareStatement("SELECT QUANTITY,MINSELL FROM  PRODUCT WHERE PRODUCTID=?");
		st.setInt(1,id);
		ResultSet rs=st.executeQuery();
		if(rs.next()) {
			if(quantity<=rs.getInt("QUANTITY") && quantity <rs.getInt("MINSELL")) {
				conn.closeConnection();
				return true;
			}
			else {
				conn.closeConnection();
				return false;
			}
		}
		
		return false;
	}
	
	public int totalCast(int id ,int quantity) throws ClassNotFoundException, SQLException {
		ConnectionManager1 conn=new ConnectionManager1();
		Connection con=conn.establishConnection();
		
		PreparedStatement st=con.prepareStatement("SELECT PRICE FROM PRODUCT WHERE PRODUCTID =?");
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		int totalcost=0;
		if(rs.next()) {
			totalcost=quantity*rs.getInt("PRICE");
		}
		conn.closeConnection();
		return totalcost;
	}
	
	public void display1() throws ClassNotFoundException,SQLException{
		ConnectionManager1 conn=new ConnectionManager1();
		Connection con=conn.establishConnection();
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM TRANSACTION");
		
		while(rs.next()) {
			System.out.println("-----------------------------");
			System.out.println(rs.getString("PRODUCTNAME")+"   "+rs.getInt("PRODUCTID")+"   "+
						rs.getInt("MINSELL")+"   "+rs.getInt("PRICE")+"  "+rs.getInt("QUANTITY"));
			System.out.println("-----------------");
		}
		conn.closeConnection();
	}
}
