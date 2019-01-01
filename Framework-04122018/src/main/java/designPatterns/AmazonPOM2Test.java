package designPatterns;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;

public class AmazonPOM2Test {

	private CommonDriver cmnDriver;
	private AmazonPOMStyle2 homePage;
	private WebDriver driver;

	@BeforeClass
	public void initializeBrowser() throws Exception {
		cmnDriver = new CommonDriver("chrome");
		cmnDriver.setPageLoadTimeOut(40);
		cmnDriver.setElementDetectionTimeOut(20);
		cmnDriver.navigateToFirstURL("https://www.amazon.in/");
		driver = cmnDriver.getDriver();
		homePage = new AmazonPOMStyle2(driver);
	}

	@AfterClass
	public void cleanUP() throws Exception {
		cmnDriver.closeAllBrowser();
	}

	@Test
	public void searchProduct() throws Exception {
		homePage.searchProduct("Rado Watch", "Watches");

	}

}
