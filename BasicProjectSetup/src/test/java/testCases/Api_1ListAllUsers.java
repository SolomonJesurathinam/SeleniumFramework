package testCases;

import org.testng.annotations.Test;
import commonfunctions.ApiMethods;
import static org.hamcrest.Matchers.*;

public class Api_1ListAllUsers {

	ApiMethods apiMethods;

	@Test
	public void listAllUsers() {
		apiMethods = new ApiMethods();

		//Calling Get Method with basic verification
		apiMethods.getMethod("https://reqres.in/api/users?page=2", "content-type", "application/json");

		//Extra Verification
		apiMethods.returnResponse()
		.then()
		.statusLine("HTTP/1.1 200 OK")
		.body("data.email[0]", equalTo("michael.lawson@reqres.in"))
		.body("data.first_name", hasItems("Michael","Lindsay"));

		//Get Body value 
		String email = apiMethods.getBodyValue("data.email[1]");
		System.out.println(email);
	}

}
