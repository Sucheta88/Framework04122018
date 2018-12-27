package demo;

import commonLibs.implementation.CommonDriver;

public class DemoCommonDriver {

	public static void main(String[] args)  {
		try {
			CommonDriver cmnDriver = new CommonDriver("chrome");
			cmnDriver.setPageLoadTimeOut(40);
			cmnDriver.setElementDetectionTimeOut(20);
			cmnDriver.navigateToFirstURL("http://qatechhub.com");
			System.out.println(cmnDriver.getTitle());
			cmnDriver.closeAllBrowser();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
