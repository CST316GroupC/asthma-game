package com.groupc.junit;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.groupc.Runner;
import com.groupc.screens.AccountCreationScreen;

public class AccTest {

	private Runner run = new Runner();
	private AccountCreationScreen acc = new AccountCreationScreen(run);
	
	String line = null;
	Vector<String> patientFirstNames = new Vector<String>();
	Vector<String> patientLastNames = new Vector<String>();
	Vector<String> types = new Vector<String>();
	Vector<String> newAge = new Vector<String>();
	Vector<String> newPass = new Vector<String>();
	Vector<String> patDoctors = new Vector<String>();
	int counter = 0;
	
	@Before
	public void setUp() throws Exception {
			
		acc.firstNameTF.setText("testFirst");
		acc.lastNameTF.setText("testLast");
		//acc.dobTF.setText("3");
		acc.passwordTF.setText("testPass");
		acc.infoTA.setText("test");
		acc.newPatientDoctor = "bill";
		
		
		
		FileReader fr = new FileReader("login_information.txt");
		BufferedReader br = new BufferedReader(fr);
		StringTokenizer st;
		
		while((line = br.readLine()) != null)
		{
			st = new StringTokenizer(line, " | ");
			patientFirstNames.add(counter, st.nextToken()); //fname
			patientLastNames.add(st.nextToken());  //last name
			newAge.add(st.nextToken());  // age
			newPass.add(st.nextToken());  //password
			types.add(counter, st.nextToken()); //type
			patDoctors.add(counter, st.nextToken()); //doctor for patient
			
			counter += 1;
			
			
		}
		br.close();
	}

	@Test
	public void setPatientDoctorTest(){
		System.out.println(acc.newPatientDoctor + " pat doc");
		acc.setPatientDoctor(acc.newPatientDoctor);		
		
		System.out.println(patDoctors.lastElement() + " last element");

		assertTrue(patDoctors.lastElement().equals("bill"));
		assertTrue(!patDoctors.lastElement().equals("Bob"));
		assertTrue(!patDoctors.lastElement().equals("bil1"));
		assertTrue(!patDoctors.lastElement().equals("b i l l"));
		assertTrue(!patDoctors.lastElement().equals(" bill"));
		assertTrue(!(patDoctors.lastElement() == null));
		
		acc.newPatientDoctor = null;
		
		acc.setPatientDoctor(acc.newPatientDoctor);
		
	}
	
	@Test
	public void addCurrentPatientTest(){
		
		acc.firstNameTF.setText("");
		acc.addCurrentPatient();
		
		
		acc.firstNameTF.setText("nextFirst");
		acc.lastNameTF.setText("nextLast");
		//acc.dobTF.setText("4");
		acc.passwordTF.setText("nextPass");
		acc.infoTA.setText("test2");
		acc.newPatientDoctor = "bill";
		
		acc.addCurrentPatient();

		
	}

}
