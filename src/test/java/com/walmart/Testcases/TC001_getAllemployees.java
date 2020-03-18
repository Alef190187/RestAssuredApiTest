package com.walmart.Testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_getAllemployees {

	public RequestSpecification httsRequest;
	public Response response;
	Logger logger = Logger.getLogger(TC001_getAllemployees.class);

	// TestBase testbase= new TestBase();

	@BeforeMethod
	public void setUp() throws InterruptedException {
		logger.info("*****Started my the cases******");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httsRequest = RestAssured.given();
		response = httsRequest.request(Method.GET, "employees");
		Thread.sleep(3000);

	}

	@Test(priority = 1)
	public void statusCodeTest() {
		logger.info("*****Started my status code checking test case******");
		int statusCode = response.getStatusCode();
		System.out.println(" the code is ==>" + statusCode);
		logger.info("*****Started the validation checke******");
		Assert.assertEquals(statusCode, 200);
		logger.info("*****finished the validation******");
	}

	@Test(priority = 2)
	public void responseBodyTest() {
		String body = response.getBody().asString();
		logger.info("the response body is ==>" + body);
		Assert.assertTrue(body != null);
	}

	@Test(priority = 3)
	public void statusresponsetimeTest() {
		Long time = response.getTime();
		System.out.println("response time is==>" + time);
		if (time > 500)
			Assert.assertTrue(time < 500);
	}
	
	@Test(priority=4)
	public void getHeaderTest() {
		Headers allheaders =response.headers();
		for(Header header:allheaders) {
			System.out.println("The headers are==>"+header.getName() +""+ header.getValue());
		}
	}
	
	@Test(priority = 5)
	public void jsonpathTest() {
		JsonPath path= response.jsonPath();
		String jsonstatus = path.getString("status");
		String name =path.get("data[0].employee_name");
		
		System.out.println("the value of name ==>"+name);
		System.out.println("the json status is ==>"+jsonstatus);
	}

	@AfterMethod
	public void tearDown() {

	}

}
