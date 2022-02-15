package testCases;

import org.testng.annotations.Test;
import commonfunctions.ApiMethods;


public class Api_5PutMethod {

	ApiMethods apiMethods;

	@Test
	public void putMethod() {
		
		apiMethods = new ApiMethods();
		
		apiMethods.createJson("name", "Solomon");
		apiMethods.createJson("job", "Tester");
		System.out.println(apiMethods.returnJson());
		
		apiMethods.putMethod("https://reqres.in/api/users", "content-type", "application/json", apiMethods.returnJson().toJSONString());
		
		if(apiMethods.returnResponse().getStatusLine().contains("OK")){
			System.out.println("User updted successfully");
		}
	}
}
