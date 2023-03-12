package tests;

import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;

public class SearchProductTest extends TestBase {

	String searchtext=excelfile.getCellData("Product Data", "tittle", 3);
	
	@BeforeClass
	public void searchProduct() throws InterruptedException {
		String verifyapimessage=excelfile.getCellData("API Messages", "Messages", 8);
		logger.debug("----------------------"+verifyapimessage+"--------------------");

		httpRequest= RestAssured.given();
		httpResponse= httpRequest.request(Method.GET,"/products/search?q="+searchtext);
		Thread.sleep(3000);
	}

	@Test
	public void verifyStatusCode() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 2));  
		
		int statuscode = httpResponse.getStatusCode();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 3))+statuscode);
		assertEquals(statuscode, 200);
	}

	@Test
	public void verifyResponseTime() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 4));
		
		long responseTime =httpResponse.getTime();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 5))+responseTime);
		assertTrue(responseTime<2000);
	}
}
