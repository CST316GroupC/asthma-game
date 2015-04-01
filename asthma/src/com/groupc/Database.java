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
		   String doc_e = doctor_email;
		   String doc_pw = doctor_password;
		   
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
			      sql = "select doctor_id from doctors where doctor_email = '" + doc_e + "' and doctor_password = '" + doc_pw + "'";
			   
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			    	 System.out.println("valid");
			         //Retrieve by column name
			         String id  = rs.getString("doctor_id");

			         //Display values
			         System.out.print("ID: " + id);
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
			         System.out.print("ID: " + id);
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
	   public static boolean addPatient(String patient_fname, String patient_lname, String patient_yob, String patient_password, String patient_email, String patient_contact){
		   Connection conn = null;
		   Statement stmt = null;	
		   String pat_f = patient_fname;
		   String pat_l = patient_lname;
		   String pat_pw = patient_password;
		   String pat_yob = patient_yob;
		   String pat_email = patient_email;
		   String pat_cont = patient_contact;
		   
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
			      sql = "insert into patients values (0, '" + pat_f + "', '" + pat_l + "', '" + pat_yob + "', '" + pat_email + "', '" + pat_pw +"', '" + pat_cont + "');";
			   
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