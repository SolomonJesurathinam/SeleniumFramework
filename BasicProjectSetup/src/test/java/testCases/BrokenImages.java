package testCases;


import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonfunctions.CommonFunctions;

import pageObjects.InternetHome_PageObjects;

public class BrokenImages extends CommonFunctions{

	Logger log = LogManager.getLogger();
	@Test
	public void brokenImages() throws IOException {
		extTest = extReport.createTest("BROKEN IMAGES");
		PageFactory.initElements(driver, InternetHome_PageObjects.class);
		log.info("Clicking Broken image link");
		extTest.log(Status.INFO, "Clicking Broken image link");
		InternetHome_PageObjects.Broken_Images.click();
		List <WebElement> image_list = driver.findElements(By.tagName("img"));
		int brokenImage = 0;
		
		//Method1
		log.info("Method 1");
		extTest.log(Status.INFO, "Method 1");
		for(WebElement list:image_list) {
			log.info("Getting request for links");
			extTest.log(Status.INFO, "Getting request for links");
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(list.getAttribute("src"));
			HttpResponse response = client.execute(request);
			log.info("Checking if image is broken or not");
			extTest.log(Status.INFO, "Checking if image is broken or not");
			
			if(response.getCode()!=200) {
				log.info(list.getAttribute("outerHTML")+" Image is broken");
				extTest.log(Status.INFO, list.getAttribute("src")+" Image is broken");
				brokenImage++;
			}
		}
		log.info("Total broken images are "+brokenImage);
		extTest.log(Status.INFO, "Total broken images are "+brokenImage);
		
		
		//Method2
		log.info("METHOD 2");
		extTest.log(Status.INFO, "METHOD 2");
		brokenImage = 0;
		for(WebElement list:image_list) {

			if(list.getAttribute("naturalWidth").equals("0")) {
				log.info(list.getAttribute("outerHTML")+" Image is broken");
				extTest.log(Status.INFO, list.getAttribute("outerHTML")+" Image is broken");
				brokenImage++;
			}
		}
		log.info("Total broken images are "+brokenImage);
		extTest.log(Status.INFO, "Total broken images are "+brokenImage);
		
		log.info("BROKEN IMAGE TC is PASSED");
		extTest.log(Status.PASS, "BROKEN IMAGE TC is PASSED");
	}
}
