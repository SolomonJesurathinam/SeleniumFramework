package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderExample {
	
	@DataProvider(name="loginData")
	public String[][] data() {
	String[][] data = {
			{"input1","output1"},
			{"input2","output2"},
			{"input3","output3"},
			{"input4","output4"},
			{"input5","output5"},
			{"input6","output6"}
			};
	return data;
	}
	
	@Test(dataProvider = "loginData")
	public void example(String input, String output) {
		System.out.println("Input is "+input);
		System.out.println("Output is "+output);
	}

}
