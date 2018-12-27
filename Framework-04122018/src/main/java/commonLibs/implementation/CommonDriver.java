package commonLibs.implementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import commonLibs.contracts.Driver;

public class CommonDriver implements Driver {

	private WebDriver driver;
	private int pageLoadTimeOut;
	private int elementDetectionTimeOut;

	public WebDriver getDriver() {
		return driver;
	}

	public void setPageLoadTimeOut(int pageLoadTimeOut) {
		this.pageLoadTimeOut = pageLoadTimeOut;
	}

	public void setElementDetectionTimeOut(int elementDetectionTimeOut) {
		this.elementDetectionTimeOut = elementDetectionTimeOut;
	}

	public CommonDriver(String browserType) throws Exception {
		pageLoadTimeOut = 30;
		elementDetectionTimeOut = 10;

		browserType = browserType.trim();
		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/gofor/Workspace/libs/chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:/Users/gofor/Workspace/libs/geckodriver-v0.23.0-win64/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserType.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:/Users/gofor/Workspace/libs/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new Exception("Invalid Browser Type : " + browserType);
		}
	}

	@Override
	public void navigateToFirstURL(String url) throws Exception {
		url = url.trim();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeOut, TimeUnit.SECONDS);
		driver.get(url);

	}

	@Override
	public String getTitle() throws Exception {
		return driver.getTitle();
	}

	@Override
	public String getCurrentUrl() throws Exception {
		return driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() throws Exception {
		return driver.getPageSource();
	}

	@Override
	public void navigateToURL(String url) throws Exception {
		url = url.trim();
		driver.get(url);

	}

	@Override
	public void navigateForward() throws Exception {
		driver.navigate().forward();
	}

	@Override
	public void navigateBackward() throws Exception {
		driver.navigate().back();

	}

	@Override
	public void refresh() throws Exception {
		driver.navigate().refresh();

	}

	@Override
	public void closeBrowser() throws Exception {
		if (driver != null) {
			driver.close();
		}

	}

	@Override
	public void closeAllBrowser() throws Exception {
		if (driver != null) {
			driver.quit();
		}

	}
}
