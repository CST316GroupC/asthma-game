import static org.junit.Assert.*;
import java.sql.Connection;

public class Databasetest {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/teamc";
	
	static final String USER = "root";
	static final String PASS = "teamc";

	@Test
	public void testget_doctor(){
		assertTrue(check.deposit(50f));
		assertTrue(!check.deposit(-50f));
	}

}
