package demo;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;

public class DemoCommonDriver {

	public static void main(String[] args)  {
		try {
			CommonDriver cmnDriver = new CommonDriver("chrome");
			ScreenshotControl screenshotControl = new ScreenshotControl(cmnDriver.getDriver());
			cmnDriver.setPageLoadTimeOut(40);
			cmnDriver.setElementDetectionTimeOut(20);
			cmnDriver.navigateToFirstURL("http://qatechhub.com");
			String filename = System.getProperty("user.dir") + "/screenshots/testimg.jpeg";

			screenshotControl.captureAndSaveScreenshot(filename);
			System.out.println(cmnDriver.getTitle());
			cmnDriver.closeAllBrowser();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
