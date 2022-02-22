package testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotations {
	
	// priority is used for running Tests
	// Test is used to run the method without main method
	// enabled - false will skip the test
	// dependsOnMethods--> will depend on a specific method.
	
	@BeforeSuite
	public void beforeSuite() {
	System.out.println("Before Suite");
	}
	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
		}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("After Class");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}
	
	@Test(priority=1)
	public void test2() {
		System.out.println("Test Method4");
	}

	@Test(priority=0)
	public void ztest1() {
		System.out.println("Test Method3");
	}
	

	@Test(priority=2,enabled = false)
	public void ignore() {
		System.out.println("DANGER SKIP THIS");
	}
	
	@Test(enabled=true)   //false
	public void parent() { 
		System.out.println("Test Method1");
	}
	
	@Test(dependsOnMethods = "parent")
	public void child() {
		System.out.println("TestMethod2");
	}
	
	
}
