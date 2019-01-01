package com.mercuryTravel.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.Status;
import com.mercuryTravel.common.ExtentReport;
import com.mercuryTravel.pages.FlightPage;
import com.mercuryTravel.pages.homePage;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
import commonLibs.utils.ConfigReader;
import commonLibs.utils.DateUtils;

public class TestSetup {

	CommonDriver cmnDriver;
	homePage homePage;
	FlightPage flightPage;

	private WebDriver driver;

	public ScreenshotControl screenshotControl;

	public Properties configProperty;

	public String currentPathProject;
	
	public ExtentReport extentReport;
	
	public String executionStartTime;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		
		setCurrentDirectoryPath();
		
		uploadConfigProperty();
		
		executionStartTime = DateUtils.getDate();
		
		extentReport = new ExtentReport();
		
		String reportFileName = String.format("%s/reports/MercuryTravelTest_%s.html", currentPathProject, executionStartTime);

		extentReport.initializeReport(reportFileName);

		extentReport.test = extentReport.extent.createTest("Initialize Basic Setup");

		cmnDriver = new CommonDriver(configProperty.getProperty("browserType"));

		int pageLoadTimeout = Integer.parseInt(configProperty.getProperty("pageLoadTimeout"));

		int elementDetectionTimeout = Integer.parseInt(configProperty.getProperty("elementDetectionTimeout"));

		cmnDriver.setPageLoadTimeOut(pageLoadTimeout);

		cmnDriver.setElementDetectionTimeOut(elementDetectionTimeout);

		extentReport.test.log(Status.INFO, "Navigating to base Url");

		cmnDriver.navigateToFirstURL(configProperty.getProperty("baseUrl"));

		driver = cmnDriver.getDriver();

		initializeScreenshot();

		homePage = new homePage(driver);

		flightPage = new FlightPage(driver);
	}

	// Reading the path

	private void initializeScreenshot() {
		screenshotControl = new ScreenshotControl(driver);
	}

	@AfterClass(alwaysRun = true)
	public void cleanUp() throws Exception {
	  cmnDriver.closeAllBrowser();
		extentReport.flushReport();
	}

	// To take screenshot of every failed scenario and save it in file
	@AfterMethod(alwaysRun = true)
	public void afterAMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			
			extentReport.test.fail(result.getName());
			
			String executionTime = DateUtils.getDate();
			String screenshotPath = String.format("%s/screenshots/%s_%s.jpeg", currentPathProject, result.getName(),
					executionTime);
			String screenshotFile = screenshotControl.captureAndSaveScreenshot(screenshotPath);

			extentReport.test.addScreenCaptureFromPath(screenshotFile);
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			extentReport.test.pass(result.getName());
		}else {
			extentReport.test.skip(result.getName());
		}
	}
	
	private void setCurrentDirectoryPath() {
		currentPathProject = System.getProperty("user.dir");
	}
	
	private void uploadConfigProperty() throws Exception {
		
		configProperty = ConfigReader.getProperties(currentPathProject + "/config/config.properties");
	}
	
}
