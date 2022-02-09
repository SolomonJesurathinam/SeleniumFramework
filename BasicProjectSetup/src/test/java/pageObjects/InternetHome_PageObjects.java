package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InternetHome_PageObjects {
	
	@FindBy(linkText="Add/Remove Elements")
	public static WebElement Add_Remove_Elements;
	
	@FindBy(linkText="Basic Auth")
	public static WebElement Basic_Auth;
	
	@FindBy(linkText="Broken Images")
	public static WebElement Broken_Images;
	
	@FindBy(linkText="Challenging DOM")
	public static WebElement Challenging_DOM;
	
	@FindBy(xpath="//a[@href=\"/checkboxes\"]")
	public static WebElement Checkboxes;
	
	@FindBy(xpath="//a[@href=\"/context_menu\"]")
	public static WebElement Context_Menu;
	
	@FindBy(xpath="//a[starts-with(text(),'Digest ')]")
	public static WebElement Digest_Authentication;
	
	@FindBy(xpath="//a[starts-with(text(),'Disappearing Elements')]")
	public static WebElement Disappearing_Elements;
	
	@FindBy(xpath="//a[starts-with(text(),'Drag and Drop')]")
	public static WebElement Drag_and_Drop;
	
	@FindBy(xpath="//a[starts-with(text(),'Dropdown')]")
	public static WebElement Dropdown;
	
	@FindBy(xpath="//a[contains(text(),'Dynamic Content')]")
	public static WebElement Dynamic_Content;
	
	@FindBy(xpath="//a[contains(text(),'Dynamic Controls')]")
	public static WebElement Dynamic_Controls;
	
	@FindBy(xpath="//a[contains(text(),'Dynamic Loading')]")
	public static WebElement Dynamic_Loading;
	
	@FindBy(xpath="//a[contains(@href,'/entry_ad')]")
	public static WebElement Entry_Ad;
	
	@FindBy(xpath="//a[contains(@href,'/exit_intent')]")
	public static WebElement Exit_Intent;
	
	@FindBy(linkText="File Download")
	public static WebElement File_Download;
	
	@FindBy(linkText="File Upload")
	public static WebElement File_Upload;
	
	@FindBy(linkText="Floating Menu")
	public static WebElement Floating_Menu;
	
	@FindBy(linkText="Forgot Password")
	public static WebElement Forgot_Password;
	
	@FindBy(linkText="Form Authentication")
	public static WebElement Form_Authentication;
	
	@FindBy(linkText="Frames")
	public static WebElement Frames;
	
	@FindBy(linkText="Geolocation")
	public static WebElement Geolocation;
	
	@FindBy(linkText="Horizontal Slider")
	public static WebElement Horizontal_Slider;
	
	@FindBy(linkText="Hovers")
	public static WebElement Hovers;
	
	@FindBy(linkText="Infinite Scroll")
	public static WebElement Infinite_Scroll;
	

}
