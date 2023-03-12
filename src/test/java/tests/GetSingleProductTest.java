package tests;

import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class GetSingleProductTest extends TestBase {

	String productId=excelfile.getCellData("Product Data", "id", 2);

	@BeforeClass
	public void GetSingleProductById() throws InterruptedException {
		String verifyapimessage=excelfile.getCellData("API Messages", "Messages", 7);
		logger.debug("----------------------"+verifyapimessage+"--------------------");

		httpRequest=RestAssured.given();
		httpResponse= httpRequest.request(Method.GET,"/products/" + productId.trim());
		Thread.sleep(3000);
	}

	@Test
	public void VerifyStatusCode() {		 
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 2));
		
		int statuscode=httpResponse.getStatusCode();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 3))+ statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	public void VerifyResponseTime() { 
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 4));
		
		long resonsetime=httpResponse.time();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 5))+ resonsetime);
		Assert.assertTrue(resonsetime<2000);
	}

	@Test
	public void VerifyResponseBody() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 6));
	
		String responsebody=httpResponse.getBody().asString();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 7))+ responsebody);
		Assert.assertEquals(responsebody.contains(productId.trim()), true);
	}
}