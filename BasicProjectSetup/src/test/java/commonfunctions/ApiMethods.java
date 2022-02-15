package commonfunctions;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiMethods {

	Response response;
	JSONObject json = new JSONObject();

	/*
	 * public void getBaseURI(String baseURI) { RestAssured.baseURI = baseURI; }
	 * 
	 * public void getMethodType(Method type, String pathParameter) {
	 * RequestSpecification httpsrequest = RestAssured.given(); response =
	 * httpsrequest.request(type,pathParameter);
	 * System.out.println(response.getBody().asString()); }
	 * 
	 * public void verifyStatuscode(int ExpectedStatus) { int statusCode =
	 * response.getStatusCode(); Assert.assertEquals(statusCode, ExpectedStatus); }
	 * 
	 * public void verifyStatusLine(String ExpectedStatusLine) { String statusLine =
	 * response.getStatusLine(); System.out.println(statusLine);
	 * Assert.assertEquals(statusLine, ExpectedStatusLine); }
	 */

	public void getMethod(String URL, String Content_Type, String Value) {

		response = RestAssured.given().header(Content_Type, Value).get(URL);
		response.then().statusCode(200);
		//.log().all();
	}

	public Response returnResponse() {
		return response;
	}

	public String getBodyValue(String data) {
		String Value = response.body().path(data);
		return Value;
	}

	public int getBodyValueInt(String data) {
		int Value = response.body().path(data);
		return Value;
		}
	
	public void getUserNotFound(String URL, String Content_Type, String Value) {

		response = RestAssured.given().header(Content_Type, Value).get(URL);
		response.then().statusCode(404);
		//.log().all();
	}
	
	public void createJson(String Key, String Value) {
		json.put(Key, Value);
	}
	
	public JSONObject returnJson() {
		return json;
	}
	
	public void postMethod(String URL, String Content_Type, String Value, String json) {

		response = 	RestAssured.given().
					header(Content_Type, Value).
					body(json).
					when().
					post(URL);
		
		response.then().statusCode(201);
	}
	
	public void putMethod(String URL, String Content_Type, String Value, String json) {

		response = 	RestAssured.given().
					header(Content_Type, Value).
					body(json).
					when().
					put(URL);
		
		response.then().statusCode(200);
	}
	
	public void deleteMethod(String URL) {
		
		response = RestAssured.given().delete(URL);
		response.then().statusCode(204);
	}
	

}
