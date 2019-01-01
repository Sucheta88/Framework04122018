package commonLibs.implementation;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import commonLibs.contracts.WindowHandler;

public class WindowControl implements WindowHandler {

	private WebDriver driver;

	public WindowControl(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void switchToAnyWindow(String windowHandle) throws Exception {
		driver.switchTo().window(windowHandle);

	}

	@Override
	public void switchToAnyWindow(int childWindowIndex) throws Exception {
		String windowHandle = driver.getWindowHandles().toArray()[childWindowIndex].toString();
		driver.switchTo().window(windowHandle);

	}

	@Override
	public String getWindowHandle(String windowHandle) throws Exception {

		return driver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() throws Exception {
		// TODO Auto-generated method stub
		return driver.getWindowHandles();
	}

}
