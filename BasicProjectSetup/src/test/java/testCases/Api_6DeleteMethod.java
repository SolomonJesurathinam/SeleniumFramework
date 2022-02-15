package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonfunctions.ApiMethods;

public class Api_6DeleteMethod {

	ApiMethods apiMethods;
	
	@Test
	public void delete() {
		apiMethods = new ApiMethods();
		
		apiMethods.deleteMethod("https://reqres.in/api/users/2");
		
		Assert.assertTrue(apiMethods.returnResponse().getStatusLine().contains("No Content"),apiMethods.returnResponse().getStatusLine() +" is the actual value");
	}
}
