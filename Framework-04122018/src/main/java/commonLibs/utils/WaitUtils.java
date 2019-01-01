package commonLibs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	public static void waitForSeconds(int timeInSecond) throws Exception {
		Thread.sleep(timeInSecond * 1000);
	}

	public static void explicitWait(String waitType, WebDriver driver, WebElement element, int timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

		switch (waitType) {
		case "visibility":
			wait.until(ExpectedConditions.visibilityOf(element));
			break;

		case "clickable":
			wait.until(ExpectedConditions.elementToBeClickable(element));
			break;
		}
	}
	
}
