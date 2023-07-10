package controller;
import java.io.*;
import java.sql.SQLException;

import dao.AgentDao;
import dao.LoginDao;
import dao.ProductDao;
import model.*;
public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Login login=new Login();
		Product product=new Product();
		LoginDao logindao=new LoginDao();
		ProductDao productdao=new ProductDao();
		AgentDao agentdao=new AgentDao();
		int option;int option1;
		do {
			System.out.println("1.Admin");
			System.out.println("2.Agent");
			System.out.println("3.Exit");
			System.out.println("---------------------------------------------------------------------------------------------");
			option=Integer.parseInt(br.readLine());
			
			switch(option) {
			case 1:{
				System.out.println("-----------------------------------------------------------------------------------------");
				System.out.println("Enter username");
				String username=br.readLine();
				System.out.println("Enter password");
				String password=br.readLine();
				login.setUSERNAME(username);
				login.setPASSWORD(password);
				boolean result=logindao.validate(login);
				if(result==true) {
					System.out.println("lOGIN successful");
					do {
						System.out.println("1.Add prodect");
						System.out.println("2.display the inventory details");
						System.out.println("3.logout");
				   		System.out.println("---------------------------------------------------------------------------------");
						option=Integer.parseInt(br.readLine());
						switch(option) {
						
						case 1:{
							
							System.out.println("Enter protect name");
							String productName=br.readLine();
							System.out.println("Enter protect id");
							int productId=Integer.parseInt(br.readLine());
							System.out.println("Enter the min selling quentity");
							int minsell=Integer.parseInt(br.readLine());
							System.out.println("Enter the price of the product");
							int price=Integer.parseInt(br.readLine());
							System.out.println("Enter the quentity");
							int quentity=Integer.parseInt(br.readLine());
							
							product.setPRODECTNAME(productName);
							product.setPRODUCTID(productId);
							product.setMINSELL(minsell);
							product.setQUENTITY(quentity);
							product.setPRICE(price);
							productdao.addProduct(product);
							break;
							}
						case 2:{
							productdao.display();
							break;
						}
						case 3:break;
						}
					}while(option!=3);
					
				}
				else {
					System.out.println("Username & password incorrect");
				}
				}break;
			case 2:{
				int display;
				System.out.println("-------------------------------------------------");
				System.out.println("Enter username");
				String Agentusername=br.readLine();
				System.out.println("Enter password");
				String Agentpassword=br.readLine();
				login.setUSERNAME(Agentusername);
				login.setPASSWORD(Agentpassword);
				boolean result=logindao.validate(login);
				
				
				if(result==true) {
					System.out.println("lOGIN successful");
					do {
						System.out.println("1.Buy/sell");
						System.out.println("2.show history");
						System.out.println("3.logout");
						System.out.println("------------------------------------------");
						option1=Integer.parseInt(br.readLine());
						switch(option1) {
							case 1:{
								System.out.println("------------------------------------------------");
								System.out.println("Enter your option (Buy or Sell)");
								String choice=br.readLine();
								
								if(choice.equalsIgnoreCase("Buy")){
									System.out.println("Enter protect name");
									String productName=br.readLine();
									System.out.println("Enter protect id");
									int productId=Integer.parseInt(br.readLine());
									System.out.println("Enter the min selling quentity");
									int minsell=Integer.parseInt(br.readLine());
									System.out.println("Enter the price of the product");
									int price=Integer.parseInt(br.readLine());
									System.out.println("Enter the quentity");
									int quentity=Integer.parseInt(br.readLine());
									
									product.setPRODECTNAME(productName);
									product.setPRODUCTID(productId);
									product.setMINSELL(minsell);
									product.setQUENTITY(quentity);
									product.setPRICE(price);
									productdao.addProduct(product);
									break;
									}
								
								 if(choice.equalsIgnoreCase("Sell")) {
									
									System.out.println("------------------------------------------------------");
									System.out.println("Enter the Product Id");
									int productId=Integer.parseInt(br.readLine());
									System.out.println("Enter the quantity");
									int quentity=Integer.parseInt(br.readLine());
									
									if(productdao.checkQuantity(productId,quentity)) {
										int totalcast=productdao.totalCast(productId,quentity);
										System.out.println("------------------------------------------------------");
										System.out.println("The product total cast is "+totalcast);
										System.out.println("------------------------------------------------------");
										agentdao.AgentaddProduct(productId);
										System.out.println("Confirm Booking the product (yes/no)");
										String booking=br.readLine();
										
											
				
									}
									
								}
								break;
							}
							case 2:{
								System.out.println("-----------------------------------------------------------");
								System.out.println("All Previous Product List");
								productdao.display();
								System.out.println("-----------------------------------------------------------");
								System.out.println("New Updated valus in the List");
								productdao.display1();
								break;
							}
							case 3:{
								break;
							}
								
							}
						
						} while(option!=3);break;
					}
				}
			}
		
	}while(option!=3);
}
}

