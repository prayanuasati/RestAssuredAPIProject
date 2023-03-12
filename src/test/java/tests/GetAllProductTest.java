package tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllProductTest extends TestBase {

	@BeforeClass
	public void GetAllProducts() throws InterruptedException {
		String verifyapimessage=excelfile.getCellData("API Messages", "Messages", 5);
		logger.debug("-------------------"+verifyapimessage+"-----------------------");
		
		httpRequest= RestAssured.given();
		httpResponse= httpRequest.request(Method.GET,"/products");    
		Thread.sleep(2000);
	}

	@Test
	public void VerifyStatusCode() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 2));
		
		int statusCode=	httpResponse.getStatusCode();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 3))+ statusCode);
		Assert.assertEquals(statusCode,200);
	}

	@Test
	public void VerifyResponseTime() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 4));
		
		long responseTime=httpResponse.getTime();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 5))+ responseTime);
		Assert.assertTrue(responseTime<2000);
	}
}