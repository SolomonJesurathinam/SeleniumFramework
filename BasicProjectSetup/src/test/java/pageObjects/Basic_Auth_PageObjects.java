package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Basic_Auth_PageObjects {

	@FindBy(xpath="//*[contains(text(),'Congratulations')]")
	public static WebElement message;
}
