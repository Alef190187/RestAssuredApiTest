package com.walmart.Testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestUtil.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_PostMethod {
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
		
		response = httprequest.request(Method.POST, "/create");
		Thread.sleep(4000);
	}
	
	@Test
      public void chectstatusCodeTest() {
		String body = response.body().asString();
		System.out.println(body.length());
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
		
		Assert.assertEquals(body.contains(employeeName), true);
		Assert.assertEquals(body.contains(employeeAge), true);
		Assert.assertEquals(body.contains(employeeSalary), true);
		
	}
	
	@AfterMethod
	public void tearDown(){
		
	}
}
