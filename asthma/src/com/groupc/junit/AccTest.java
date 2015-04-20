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
	Vector<String> patientEmails = new Vector<String>();
	Vector<String> patientFirstNames = new Vector<String>();
	Vector<String> patientLastNames = new Vector<String>();
	Vector<String> types = new Vector<String>();
	Vector<String> newAge = new Vector<String>();
	Vector<String> newPass = new Vector<String>();
	Vector<String> patDoctors = new Vector<String>();
	int counter = 0;
	
	@Before
	public void setUp() throws Exception {
		
		acc.setFirstNameTF("testFirst");
		acc.setLastNameTF("testLast");
		acc.setDayTF("2");
		acc.setMonthTF("4");
		acc.setYearTF("94");
		acc.setPasswordTF("testPass");
		acc.setEmailTF("test@test.com");
		acc.setPatientDoctor("bill");
		
		
		
		FileReader fr = new FileReader("login_information.txt");
		BufferedReader br = new BufferedReader(fr);
		StringTokenizer st;
		
		while((line = br.readLine()) != null)
		{
			st = new StringTokenizer(line, " | ");
			patientEmails.add(counter, st.nextToken()); //email
			patientFirstNames.add(counter, st.nextToken()); //fname
			patientLastNames.add(st.nextToken());  //last name
			newAge.add(st.nextToken());  // dob
			newPass.add(st.nextToken());  //password
			types.add(counter, st.nextToken()); //type
			patDoctors.add(counter, st.nextToken()); //doctor for patient
			counter += 1;
			
			
		}
		br.close();
	}

	@Test
	public void setPatientDoctorTest(){
		System.out.println(acc.getPatientDoctor() + " pat doc");
		acc.setPatientDoctor("bill");		
		
		System.out.println(patDoctors.lastElement() + " last element");
		
		acc.setPatientDoctor(null);
		
		//acc.setPatientDoctor(acc.newPatientDoctor);
		
	}
	
	@Test
	public void addCurrentPatientTest(){
		
		acc.setFirstNameTF("");
		acc.addCurrentPatient();
		
		
		acc.setFirstNameTF("nextFirst");
		acc.setLastNameTF("nextLast");
		acc.setMonthTF("3");
		acc.setDayTF("2");
		acc.setYearTF("91");
		acc.setPasswordTF("nextPass");
		acc.setPatientDoctor("bill");
		
		acc.addCurrentPatient();
		
		
		
	}

}
