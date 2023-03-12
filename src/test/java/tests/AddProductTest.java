package tests;

import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class AddProductTest extends TestBase{

	@BeforeClass
	public void addProduct() throws InterruptedException {

		String verifyapimessage=excelfile.getCellData("API Messages", "Messages", 2);

		logger.debug("----------------------"+verifyapimessage+"--------------------");

		httpRequest= RestAssured.given();
		JSONObject data= new JSONObject();
		data.put("title", excelfile.getCellData("Product Data", "tittle", 2));
		data.put("description", excelfile.getCellData("Product Data", "description", 2));
		httpRequest.body(data.toJSONString());
		httpResponse= httpRequest.request(Method.POST,"products/add");    
		Thread.sleep(2000);
	}

	@Test
	public void VerifyStatusCode() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 2));

		int statusCode=	httpResponse.getStatusCode();
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 3)+ statusCode);

		Assert.assertEquals(statusCode,200);
	}

	@Test
	public void VerifyResponseTime() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 4));
		
		long responseTime=httpResponse.getTime();
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 5)+ responseTime);
		Assert.assertTrue(responseTime<2000);
	}

	@Test
	public void VerifyResponseBody() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 6));
		
		String responsebody=httpResponse.getBody().asString();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 7))+ responsebody);
		Assert.assertEquals(responsebody.contains("id"), true);

	}

	@Test(dependsOnMethods= "VerifyResponseBody")
	public void  VerifyResponseBodyValue() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 8));
		
		JsonPath jsonPath = httpResponse.jsonPath();
		logger.debug((excelfile.getCellData("Test Method Messages", "Messages", 9))+ httpResponse.body().asString());
		Assert.assertTrue((int)jsonPath.get("id")>0);
	}

}
