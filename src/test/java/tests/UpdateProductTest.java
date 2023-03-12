package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class UpdateProductTest extends TestBase{

	String productid=excelfile.getCellData("Product Data", "id", 2);

	@BeforeClass
	public void UpdateProductbyid() throws InterruptedException {
		String verifyapimessage=excelfile.getCellData("API Messages", "Messages", 9);
		logger.debug("----------------------"+verifyapimessage+"--------------------");

		httpRequest= RestAssured.given();
		JSONObject data= new JSONObject();
		data.put("title",excelfile.getCellData("Product Data", "tittle", 4));
		
		httpRequest.body(data.toJSONString());
		httpResponse= httpRequest.request(Method.PUT,"/products/"+productid);    
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