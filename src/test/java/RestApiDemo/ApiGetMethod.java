package RestApiDemo;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiGetMethod {
	
	@Test
	public void getMethodTest() {
		RestAssured.baseURI= "http://localhost:3030/products";
		RequestSpecification httpRequest= RestAssured.given();
		//Response response = httpRequest.get("?id=43900");
		Response response = httpRequest.get("");
		
		String resBody = response.getBody().asString();
		JsonPath jpath = response.jsonPath();
		System.out.println(jpath.get("data"));
		List<Object> value= jpath.getList("data");
		for(Object abc:value) {
			System.out.println("The array values"+abc.toString());
		}
		
		
		System.out.println(jpath.get("type"));
		JsonPath path = new JsonPath(resBody);
		System.out.println(path.get("data"));
		//JSONArray jsonarray = new JSONArray();
		
		Headers allheaders = response.getHeaders();
		for(Header header: allheaders) {
			System.out.println( header.getName() +"===>"+ header.getValue());
		}
		System.out.println("the response body is ==>"+resBody );
		System.out.println("the status line is==> "+response.getStatusLine());
		System.out.println("the status content is==> "+response.getContentType());
		System.out.println("the status code is==> "+response.getStatusCode());
		System.out.println("the value of header is ===>" +response.getHeader("Connection"));
		
		Assert.assertEquals(response.getHeader("Connection"), "keep-alive" , "value as given as expected");
	}

}
