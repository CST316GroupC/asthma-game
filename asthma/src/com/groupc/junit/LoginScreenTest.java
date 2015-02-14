package com.groupc.junit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.groupc.screens.LoginScreen;


public class LoginScreenTest {

	private static LoginScreen testLogin;
	
	@Test
	public void testCheckLogin() {

		char[] testPass = {'p','a','s','s'};
		
		assertEquals(LoginScreen.userNameTF.getText(), "test");
		assertEquals(LoginScreen.passwordTF.getPassword(), testPass);
	}

}
