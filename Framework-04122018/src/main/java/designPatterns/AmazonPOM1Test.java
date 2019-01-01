package designPatterns;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPOM1Test {

	private CommonDriver cmnDriver;

	private AmazonPOMStyle1 homePage;

	private ElementControl eleControl;

	private DropdownControl drpDwnControl;

	private WebDriver driver;

	@BeforeClass
	public void initializeBrowser() throws Exception {
		cmnDriver = new CommonDriver("chrome");
		cmnDriver.setPageLoadTimeOut(40);
		cmnDriver.setElementDetectionTimeOut(20);
		cmnDriver.navigateToFirstURL("https://www.amazon.in/");
		driver = cmnDriver.getDriver();
		eleControl = new ElementControl();
		drpDwnControl = new DropdownControl();
		homePage = new AmazonPOMStyle1(driver);
	}

	@AfterClass
	public void cleanUP() throws Exception {
		cmnDriver.closeAllBrowser();
	}

	@Test
	public void searchProduct() throws Exception {
		eleControl.setText(homePage.searchBox, "RadoWatch");
		drpDwnControl.selectViaVisisbleText(homePage.searchCategory, "Watches");
		eleControl.clickElement(homePage.searchButton);
	}

}
