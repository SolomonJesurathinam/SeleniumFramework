package testCases;
import org.testng.annotations.Test;

import commonfunctions.ApiMethods;

public class Api_3GetNotFoundUser {

	ApiMethods apiMethods;

	@Test
	public void userNotFound() {
		apiMethods = new ApiMethods();
		
		//Checks for 404 error
		apiMethods.getUserNotFound("https://reqres.in/api/users/23", "content-type", "application/json");
		
	}

}
