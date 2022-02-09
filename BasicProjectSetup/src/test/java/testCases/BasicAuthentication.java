package testCases;

import java.lang.invoke.MethodHandles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonfunctions.CommonFunctions;
import commonfunctions.CustomListeners;
import pageObjects.Basic_Auth_PageObjects;

@Listeners(CustomListeners.class)
public class BasicAuthentication extends CommonFunctions{

	Logger log = LogManager.getLogger();
	@Test
	public void basicAuth() {
		Classname = MethodHandles.lookup().lookupClass().getName();
		
		log.info("Getting Username");
		extTest = extReport.createTest("Basic Authentiation");
		String username = properties.getProperty("username");
		log.info("Getting Password");
		String password = properties.getProperty("password");
		log.info("Opening site with username and password");
		extTest.log(Status.INFO, "Opening site with username and password");
		String url = "https://"+username+":"+password+"@the-internet.herokuapp.com/basic_auth/";
		driver.get(url);
		log.info(url);
		log.info("Site is opened");
		extTest.log(Status.INFO, "Site is opened");
		PageFactory.initElements(driver, Basic_Auth_PageObjects.class);
		String actual= Basic_Auth_PageObjects.message.getText();
		String expected = properties.getProperty("authMessage");
		
		try{
			log.info("Verifying Logged in message");
			extTest.log(Status.INFO, "Verifying Logged in message");
			Assert.assertEquals(actual, expected);
			log.info("Verification passed");
			extTest.log(Status.INFO, "Verification passed");
		} catch(AssertionError e){
			log.info("Verification failed");
			extTest.log(Status.FAIL, "Verification failed");
			ColoringFailed=Basic_Auth_PageObjects.message;
			throw e;
		}
		
		log.info("Basic Authentication testcase is passed");
		extTest.log(Status.INFO, "Basic Authentication testcase is passed");
		driver.get(properties.getProperty("url"));
	}
}
