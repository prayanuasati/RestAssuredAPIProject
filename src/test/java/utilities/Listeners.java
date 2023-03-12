package utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public void onStart(ITestContext testContext) {
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")  +"/reports/myReport.html");
		
		htmlReporter.config().setDocumentTitle("Automation Report"); //Title of Report
		htmlReporter.config().setReportName("Rest Assured API Testing Report"); //name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "Rest Assured API Automation Project");
		extent.setSystemInfo("Host name", "localHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Prayanu");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.PASS, "Test Case PASSED IS "+ result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.FAIL, "Test Case FAILED IS "+ result.getName()); // to add name in extent report
		test.log(Status.FAIL, "Test Case PASSED IS "+ result.getThrowable()); // to add error/exception in extent report
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.SKIP, "Test Case SKIPPED IS "+ result.getName()); // to add name in extent report
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
