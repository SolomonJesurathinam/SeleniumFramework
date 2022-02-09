package testCases;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonfunctions.CommonFunctions;
import commonfunctions.CustomListeners;
import pageObjects.Add_Remove_Elements_PageObjects;
import pageObjects.InternetHome_PageObjects;

@Listeners(CustomListeners.class)

public class AddDeleteElements extends CommonFunctions{

	Logger log = LogManager.getLogger();
	
	
	@Test
	public void addDelete() throws InterruptedException {
		
		Classname = MethodHandles.lookup().lookupClass().getName();
		
		log.info("Clicking Add Remove link");
		extTest = extReport.createTest("Add Delete Elements Test");
		extTest.log(Status.INFO, "Clicking Add Remove link");
		PageFactory.initElements(driver, InternetHome_PageObjects.class);
		InternetHome_PageObjects.Add_Remove_Elements.click();
		log.info("Clicked Add Remove link");
		extTest.log(Status.INFO, "Clicked Add Remove link");
		PageFactory.initElements(driver, Add_Remove_Elements_PageObjects.class);
		
		for(int i=1;i<8;i++) {
		log.info("Adding an Element "+i);
		extTest.log(Status.INFO, "Adding an Element "+i);
		Add_Remove_Elements_PageObjects.AddElement.click();
		log.info(("Element Added "+i));
		extTest.log(Status.INFO, "Element Added "+i);
		}
		
		for(int i=5; i>=1; i--) {
			log.info("Removing an Element "+i);
			extTest.log(Status.INFO, "Removing an Element "+i);
			Add_Remove_Elements_PageObjects.DeleteElement.click();
			log.info("Element Removed "+i);
			extTest.log(Status.INFO, "Element Removed "+i);
		}
	
		Boolean deleteValue =null;
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			deleteValue = Add_Remove_Elements_PageObjects.DeleteElement.isDisplayed();
		}catch(NoSuchElementException e) {
			deleteValue=false;
		}
		finally {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
		
		try {
			log.info("Checking if the Delete element is not available");
			extTest.log(Status.INFO, "Checking if the Delete element is not available");
		Assert.assertEquals(false, deleteValue);
		} catch(AssertionError e) {
			log.info("Delete Element is available");
			extTest.log(Status.FAIL, "Delete Element is available");
			ColoringFailed=Add_Remove_Elements_PageObjects.DeleteElement;
			throw e;
		}
		
		log.info("AddDelete is passed");
		extTest.log(Status.PASS, "Add Delete Elements is passed");
		Thread.sleep(2000);
		driver.get(properties.getProperty("url"));
	}
}
