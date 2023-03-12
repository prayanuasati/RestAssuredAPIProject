package tests;

import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class GetAllProductsCategeriesTest extends TestBase {

	@BeforeClass
	public void getallproductcategeries() throws InterruptedException {
		String verifyapimessage=excelfile.getCellData("API Messages", "Messages", 4);
		logger.debug("-------------------"+verifyapimessage+"-----------------------");
		
		httpRequest= RestAssured.given();
		httpResponse=httpRequest.request(Method.GET,"/products/categories");
		Thread.sleep(3000);
	}

	@Test
	public void verifystatuscode() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 2));
		
		int statuscode =httpResponse.getStatusCode();
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 3) + statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	public void verifyResponseTime() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 4));
		
		long responseTime=httpResponse.getTime();
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 5) + responseTime);
		Assert.assertTrue(responseTime<2000);
	}
}
