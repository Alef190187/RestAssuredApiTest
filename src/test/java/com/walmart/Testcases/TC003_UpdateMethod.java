package com.walmart.Testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestUtil.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC003_UpdateMethod {
	String employeeName = RestUtils.empName();
	String employeeAge = RestUtils.empAge();
	String employeeSalary = RestUtils.empSal();
	Response response;
	@BeforeMethod
	public void setUp() throws InterruptedException{
		
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httprequest = RestAssured.given();
		
		JSONObject json = new JSONObject();
		json.put("name", employeeName);
		json.put("age",employeeAge );
		json.put("salary", employeeSalary);
		
		httprequest.header("Content-Type", "application/json");
		httprequest.body(json.toJSONString());
		
		response = httprequest.request(Method.PUT, "/update/3");
		Thread.sleep(4000);
	}
	
	
	@Test(priority=1)
    public void chectstatusCodeTest() {
		String body = response.body().asString();
		System.out.println(body.length());
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
		
//		Assert.assertEquals(body.contains(employeeName), true);
//		Assert.assertEquals(body.contains(employeeAge), true);
//		Assert.assertEquals(body.contains(employeeSalary), true);
//		
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
			System.out.println("The headers are==>"+header.getName() +"****"+ header.getValue());
		}
	}
	
	@Test(priority = 5)
	public void jsonpathTest() {
		JsonPath path= response.jsonPath();
		String jsonstatus = path.getString("status");
		//String name =path.get("data[0].employee_name");
		
		//System.out.println("the value of name ==>"+name);
		System.out.println("the json status is ==>"+jsonstatus);
	}
	
	@AfterMethod
	public void tearDown(){
		
	}
}
