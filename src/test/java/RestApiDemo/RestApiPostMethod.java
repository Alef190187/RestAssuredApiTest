package RestApiDemo;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestApiPostMethod {
	
	@Test
	public void postMethodTest() {
		RestAssured.baseURI= "https://fakerestapi.azurewebsites.net";
		RequestSpecification httprequest = RestAssured.given();
		
		JSONObject json = new JSONObject();
		json.put("Id", 20);
		json.put("UserName", "Alef");
		json.put("Password", "Alef");
		httprequest.body(json.toJSONString());
		httprequest.header("Content-Type", "application/json");
		
		Response response = httprequest.request(Method.POST,"/api/Users");
		System.out.println("The status code is ==>"+response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());
		String jsonBody = response.getBody().asString();
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("UserName"));
		System.out.println(jsonpath.get("Password"));
		Assert.assertEquals(jsonpath.get("Password"), "Alef");
		
	}

}
