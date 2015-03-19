package com.groupc.junit;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.groupc.Database;
import com.groupc.Runner;


public class DatabaseTest {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/teamc";
	
	static final String USER = "root";
	static final String PASS = "teamc";
	Runner test;
	
	@Before
	public void setUp() throws Exception {
		test = new Runner();
	}
	
	@Test
	public void testget_doctor(){
		assertTrue(Database.getDoctor("bob@bob.com", "blh")); //correct user/pass
		assertTrue(!Database.getDoctor("bob@bob.co", "blh")); //incorrect user / correct pass
		assertTrue(!Database.getDoctor("ob@bob.com", "blh")); //incorrect user / correct pass
		assertTrue(!Database.getDoctor("bob@bob.com", "bl")); //correct user / incorrect pass
		assertTrue(!Database.getDoctor("bob@bob.com", "lh")); //correct user / incorrect pass
		assertTrue(Database.getDoctor("BOB@BOB.COM", "BLH")); //UPPER CASE TEST
	}
	
	@After
	public void cleanUp()
	{
		test.close();
	}

}
