package testCases;

import org.testng.annotations.Test;
import commonfunctions.ApiMethods;
import static org.hamcrest.Matchers.*;

public class Api_2GetsingleUser {

	ApiMethods apiMethods;	
	@Test	
	public void getSingleUser() {
		apiMethods = new ApiMethods();

		apiMethods.getMethod("https://reqres.in/api/users/2", "content-type", "application/json");

		//Extra Verifications

		apiMethods.returnResponse().
		then().
		body("data.id", equalTo(2)).
		body("data.email", equalTo("janet.weaver@reqres.in")).
		body("data.first_name", equalTo("Janet")). 
		body("data.last_name",equalTo("Weaver")). 
		body("data.avatar",equalTo("https://reqres.in/img/faces/2-image.jpg"));

	}

}
