package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Add_Remove_Elements_PageObjects {
	
	@FindBy(xpath="//*[@onclick='addElement()']")
	public static WebElement AddElement;
	
	@FindBy(xpath="//*[@onclick='deleteElement()']")
	public static WebElement DeleteElement;

}
