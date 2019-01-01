package com.mercuryTravel.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;


public class HomePageTests extends TestSetup {

	public HomePageTests() throws Exception {
		super();
		
	}

	@Parameters({"sUserEmailId","sUserPassword"})
	@Test
	public void verifyLoginToApplication(String sUserEmailId,String sUserPassword) throws Exception {
  
		extentReport.test = extentReport.extent.createTest("TC - 21 - Verify login to mercury travel with parameters : "+ sUserEmailId+ " " + sUserPassword );
		homePage.userLogIn(sUserEmailId, sUserPassword);
		String actualWelcomeText = "Welcome, Sucheta";
		String expectedWelcomeText = homePage.getWelcomeText();
		Assert.assertEquals(actualWelcomeText, expectedWelcomeText);
		extentReport.test.log(Status.INFO,"Both actual and welcome test match");
	}
	
}
