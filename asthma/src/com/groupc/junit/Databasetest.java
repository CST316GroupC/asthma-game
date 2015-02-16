package com.groupc.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.groupc.Database;
import com.groupc.Runner;


public class Databasetest {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/teamc";
	
	static final String USER = "root";
	static final String PASS = "teamc";
	
	@Before
	public void setUp() throws Exception {
		Runner test = new Runner();
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

}
