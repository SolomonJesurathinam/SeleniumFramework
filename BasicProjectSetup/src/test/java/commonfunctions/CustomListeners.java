package commonfunctions;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class CustomListeners extends CommonFunctions implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			screenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Starting ");
	}

	@Override
	public void onFinish(ITestContext context) {
	}

	
}
