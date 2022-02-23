package commonfunctions;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer{

	int failedcout = 0;
	int limit = 4;
	@Override
	public boolean retry(ITestResult result) {
		if(failedcout<limit) {
			failedcout++;
			return true;
		}
		return false;
	}

}
