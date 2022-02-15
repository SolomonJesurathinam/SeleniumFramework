package testCases;

import org.testng.annotations.Test;
import commonfunctions.ApiMethods;

public class Api_4Post {
	
	ApiMethods apiMethods;

	@Test
	public void postMethod() {
		
		apiMethods = new ApiMethods();
		
		apiMethods.createJson("name", "Solomon");
		apiMethods.createJson("job", "teacher");
		System.out.println(apiMethods.returnJson());
		
		apiMethods.postMethod("https://reqres.in/api/users", "content-type", "application/json", apiMethods.returnJson().toJSONString());
		
		if(apiMethods.returnResponse().getStatusLine().contains("Created")){
			System.out.println("User created successfully");
		}
	}
}
