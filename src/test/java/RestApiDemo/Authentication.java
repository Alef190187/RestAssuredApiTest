package RestApiDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authentication {

	@Test
	public void authenticationTest() {
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
     //set the authentication
		PreemptiveBasicAuthScheme authentication = new PreemptiveBasicAuthScheme();
		authentication.setUserName("ToolsQA");
		authentication.setPassword("TestPassword");
		RestAssured.authentication = authentication;
        ///set the request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		JsonPath path = response.jsonPath();
		System.out.println(path.prettyPrint());
		
	}

}
