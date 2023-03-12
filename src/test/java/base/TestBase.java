package base;


import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Xls_Reader;

public class TestBase {
	public Xls_Reader excelfile = new Xls_Reader("D:\\Study\\QA\\Testing\\Rest Assured Project Data.xlsx");
	public Response httpResponse;
	public RequestSpecification httpRequest;
    public Logger logger;
    
    @BeforeClass
    public void setup() {
        logger= Logger.getLogger("devpinoyLogger");
    	RestAssured.baseURI="https://dummyjson.com";
    }
    
}
