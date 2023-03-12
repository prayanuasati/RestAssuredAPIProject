package tests;

import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

import java.lang.System.Logger;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;

public class GetProductOfCategoryTest extends TestBase{

	String catagory=excelfile.getCellData("Product Data", "catagory", 3);

	
	@BeforeClass
	public void getProductOfCatagory() throws InterruptedException {
		String verifyapimessage=excelfile.getCellData("API Messages", "Messages", 6);
		logger.debug("----------------------"+verifyapimessage+"--------------------");

		httpRequest=RestAssured.given();
		httpResponse=httpRequest.request(Method.GET,"products/category/"+catagory);
		Thread.sleep(3000);
	}

	@Test
	public void verifystatusCode() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 2));
		
		int statuscode= httpResponse.getStatusCode();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 3))+statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	public void verifyResponseTime() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 4));
		
		long responseTime= httpResponse.getTime();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 5))+responseTime);
		Assert.assertTrue(responseTime<2000);
	}
}