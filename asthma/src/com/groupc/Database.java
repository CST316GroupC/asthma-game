package com.groupc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/teamc";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "teamc";
	   
	   public static boolean getDoctor(String doctor_email, String doctor_password){
		   Connection conn = null;
		   Statement stmt = null;
		   
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      //STEP 4: Execute a query
			      System.out.println("Validating login...");
			      stmt = conn.createStatement();
			      String sql;
			      sql = "select doc_id from doctors where doc_email = '" + doctor_email + "' and doc_password = '" + doctor_password + "';";
			   
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			    	 System.out.println("valid");
			         //Retrieve by column name
			         String id  = rs.getString("doc_id");

			         //Display values
			         System.out.println("ID: " + id);
			         return true;
			         
			      }
			      
			      //STEP 6: Clean-up environment
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		   //System.out.println("Invalid login");
		   return false;
	   }
	   public static boolean getPatient(String patient_email, String patient_password){
		   Connection conn = null;
		   Statement stmt = null;	
		   String pat_e = patient_email;
		   String pat_pw = patient_password;
		   
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      //STEP 4: Execute a query
			      System.out.println("Validating login...");
			      stmt = conn.createStatement();
			      String sql;
			      sql = "select pat_id from patients where pat_email = '" + pat_e + "' and pat_password = '" + pat_pw + "'";
			   
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			    	 System.out.println("valid");
			         //Retrieve by column name
			         String id  = rs.getString("pat_id");

			         //Display values
			         System.out.println("ID: " + id);
			         return true;
			         
			      }
			      
			      //STEP 6: Clean-up environment
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		   System.out.println("Invalid login");
		   return false;
	   }
	   public static boolean addPatient(String patient_fname, String patient_lname, String patient_mob, String patient_dob, String patient_yob, String patient_password, String patient_email, String patient_contact){
		   Connection conn = null;
		   Statement stmt = null;	
		   Statement stmt2 = null;
		   String patient_birth = (patient_mob+"/"+patient_dob+"/"+patient_yob);
		   
		   
		   
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      
			      //STEP 4: Execute a query
			      
			      System.out.println("Validating unique email...");
			      stmt = conn.createStatement();
			      String sql;
			      sql = "select pat_email from patients where pat_email = '" + patient_email + "';";
			   
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      while(rs.next()){
			    	 System.out.println("email already registered");
			         //Retrieve by column name
			         stmt.close();
			         return false;
			      }
			      
			      System.out.println("Updating database...");
			      stmt2 = conn.createStatement();
			      String sql2;
			      sql2 = "insert into patients values (0, '" + patient_fname + "', '" + patient_lname + "', '" + patient_birth + "', '" + patient_email + "', '" + patient_password +"', '" + patient_contact + "', '0', '0', '0', '0', '9999');";
			   
			      stmt2.executeUpdate(sql2);
			     
			      
			      //STEP 6: Clean-up environment
			      stmt2.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		   return true;
	   }
	   public static boolean deletePatient(String patient_email){
		   Connection conn = null;
		   Statement stmt = null;	
		   String pat_e = patient_email;
		   
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      //STEP 4: Execute a query
			      System.out.println("Updating database...");
			      stmt = conn.createStatement();
			      String sql;
			      sql = "delete from patients where pat_email = '" + pat_e + "';";
			   
			      stmt.executeUpdate(sql);
			     
			      
			      //STEP 6: Clean-up environment
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		   return true;
	   }
	   
	   public static boolean updatepin(String patient_email, String patient_oldpin, String patient_newpin){
		   Connection conn = null;
		   Statement stmt = null;	
		   
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      //STEP 4: Execute a query
			      stmt = conn.createStatement();
			      String sql;
			      System.out.println("email: " + patient_email);
			      System.out.println("old pin: " + patient_oldpin);
			      sql = "select pin from patients where pat_email = '" + patient_email + ".com' and pin = "+patient_oldpin+";";
			   
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			    	 System.out.println("valid old pin");
			         //Retrieve by column name
			         String id  = rs.getString("pin");
			         
			         String sql2;
					 sql2 = "update patients set pin = "+ patient_newpin + " where pat_email = '"+patient_email+".com';";
					 stmt.executeUpdate(sql2);
			         //Display values
					 System.out.println("old pin: " + id);
			         System.out.println("new pin: " + patient_newpin);
			         return true;
			         
			      }
			      
			      //STEP 6: Clean-up environment
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		   System.out.println("Invalid pin");
		   return false;
	   }
	   
	   public static boolean updatepass(String patient_email, String patient_oldpass, String patient_newpass){
		   Connection conn = null;
		   Statement stmt = null;	
		   
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      //STEP 4: Execute a query
			      stmt = conn.createStatement();
			      String sql;
			      System.out.println("email: " + patient_email);
			      System.out.println("old pass: " + patient_oldpass);
			      sql = "select pat_password from patients where pat_email = '" + patient_email + ".com' and pat_password = '"+patient_oldpass+"';";
			   
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			    	 System.out.println("valid old pass");
			         //Retrieve by column name
			         String id  = rs.getString("pat_password");
			         
			         String sql2;
					 sql2 = "update patients set pat_password = '"+ patient_newpass + "' where pat_email = '"+patient_email+".com';";
					 stmt.executeUpdate(sql2);
			         //Display values
					 System.out.println("old pass: " + id);
			         System.out.println("new pass: " + patient_newpass);
			         return true;
			         
			      }
			      
			      //STEP 6: Clean-up environment
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		   System.out.println("Invalid old password");
		   return false;
	   }
	   
	   public static boolean updateg1(String patient_email, String score){
		   Connection conn = null;
		   Statement stmt = null;	
		   
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      System.out.println("email: "+patient_email);
			      System.out.println("input score: "+score);
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      //STEP 4: Execute a query
			      stmt = conn.createStatement();
			      String sql;
			      sql = "select gm_1 from patients where pat_email = '" + patient_email + "';";
			   
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			    	 System.out.println("valid score");
			         //Retrieve by column name
			         String id  = rs.getString("gm_1");
			         int aInt = Integer.parseInt(id);
			         int bInt = Integer.parseInt(score);
			         
			         if (bInt>aInt){
			        	 String sql2;
					     sql2 = "update patients set gm_1="+score+"where pat_email = '"+patient_email+"';";
					     stmt.executeUpdate(sql2);
			         }
			         //Display values
			         System.out.println("Old score: " + id);
			         return true;
			         
			      }
			      
			      //STEP 6: Clean-up environment
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		   System.out.println("Invalid score");
		   return false;
	   }
}
	   /*
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "Select * from doctors";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         String id  = rs.getString("doctor_id");
	         String name = rs.getString("doctor_names");
	         String email = rs.getString("doctor_email");
	         String pass = rs.getString("doctor_password");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Name: " + name);
	         System.out.print(", Email: " + email);
	         System.out.println(", Password: " + pass);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
	}//end FirstExample
	*/