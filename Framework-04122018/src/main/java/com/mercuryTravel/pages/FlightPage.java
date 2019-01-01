package com.mercuryTravel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FlightPage extends BaseOperation {

	public FlightPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
}
