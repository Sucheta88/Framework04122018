package designPatterns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPOMStyle2 {

	private WebElement searchBox;
	private WebElement searchCategory;
	private WebElement searchButton;
	private WebElement result;

	private ElementControl eleControl;
	private DropdownControl drpDwnControl;

	public AmazonPOMStyle2(WebDriver driver) {
		eleControl = new ElementControl();
		drpDwnControl = new DropdownControl();

		searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchCategory = driver.findElement(By.id("searchDropdownBox"));
		searchButton = driver.findElement(By.xpath("//input[@value='Go']"));
		result = driver.findElement(By.id("s-result-count"));
	}

	public void searchProduct(String product, String category) throws Exception {
		eleControl.setText(searchBox, "RadoWatch");
		drpDwnControl.selectViaVisisbleText(searchCategory, "Watches");
		eleControl.clickElement(searchButton);
		System.out.println(result.getText());
	}

}
