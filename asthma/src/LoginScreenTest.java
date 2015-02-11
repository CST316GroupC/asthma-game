import static org.junit.Assert.*;

import org.junit.Test;


public class LoginScreenTest {

	private static LoginScreen testLogin;
	
	@Test
	public void testCheckLogin() {

		char[] testPass = {'p','a','s','s'};
		
		assertEquals(LoginScreen.userNameTF.getText(), "test");
		assertEquals(LoginScreen.passwordTF.getPassword(), testPass);
		assertTrue(testLogin.checkLogin());
		assertFalse(testLogin.checkLogin());
	}

}
