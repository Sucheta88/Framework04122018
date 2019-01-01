package com.mercuryTravel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage extends BaseOperation {

	@FindBy(linkText = "International Holidays")
	private WebElement internationalHolidayLink;

	@FindBy(linkText = "Indian Holidays")
	private WebElement indianHolidayLink;

	@FindBy(linkText = "Flights")
	private WebElement flightLink;

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(linkText = "Customer Login")
	private WebElement customerLogIn;

	@FindBy(linkText = "User Login")
	private WebElement userLogiIn;

	@FindBy(linkText = "Register")
	private WebElement registerLink;

	@FindBy(id = "sign_user_email")
	private WebElement userEmailId;

	@FindBy(id = "sign_user_password")
	private WebElement userPassword;

	@FindBy(xpath = "//div[@id='modalLogin']//form[@class='form-signin']//button")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Welcome,")
	private WebElement welcomeText;

	public homePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void userLogIn(String sUserEmailId, String sUserPassword) throws Exception {
		mouseControl.moveToElement(customerLogIn);
		elementControl.clickElement(userLogiIn);
		Thread.sleep(5000);
		elementControl.setText(userEmailId, sUserEmailId);
		elementControl.setText(userPassword, sUserPassword);
		elementControl.clickElement(loginButton);
	}

	public String getWelcomeText() throws Exception {
		return elementControl.getText(welcomeText);
	}

}
