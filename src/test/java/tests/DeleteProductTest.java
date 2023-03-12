package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class DeleteProductTest extends TestBase{

	String productid=excelfile.getCellData("Product Data", "id", 2);

	@BeforeClass
	public void DeleteProduct() throws InterruptedException {
		String verifyapimessage=excelfile.getCellData("API Messages", "Messages", 3);

		logger.debug("-------------------"+verifyapimessage+"-----------------------");
		httpRequest= RestAssured.given();
		httpResponse= httpRequest.request(Method.DELETE,"/products/"+productid);    
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
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 5) + responseTime);
		Assert.assertTrue(responseTime<2000);
	}

	@Test
	public void Verifyjsonbody() {
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 6));
		
		JsonPath jsonPath = httpResponse.jsonPath();
		logger.debug(excelfile.getCellData("Test Method Messages", "Messages", 7)+ httpResponse.getBody().asString());
		Assert.assertTrue((boolean)jsonPath.get("isDeleted"));
	}
}