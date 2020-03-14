package RestApiDemo;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestUtil.Xls_Reader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest {
	static Xls_Reader reader;
	
	
	@DataProvider(name = "getTestData")
	
	public static String[][] getdataFromExcel() {
		
		
	     reader = new Xls_Reader("C:\\Users\\alef1\\eclipse-workspace\\RestApiAutomation\\"
			+ "src\\test\\java\\TestData\\ApiTestData.xlsx");
	     
	     
	   int rowNum =reader.getRowCount("TestData");
	   int colNum = reader.getColumnCount("TestData");
	   String empdata[][] = new String[rowNum][colNum];
	   for(int i = 1; i<=rowNum; i++){
		   
		  for(int j=0; j<colNum; j++) {
			  empdata [i][j] = reader.getCellData("TestData", i, j);
			   
		   }
	   }
	   return empdata;
}
	
	@Test(dataProvider="getTestData")
	public void dataDrivenPostTest(String Name, String Type, String Price, String Shipping, String Upc,
			String Description, String Manufacturer, String Model, String Url, String Image ) {
		
		RestAssured.baseURI= "http://localhost:3030";
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject json = new JSONObject();
		json.put("name", Name);
		json.put("type", Type);
		json.put("price", Price);
		json.put("shipping", Shipping);
		json.put("upc", Upc);
		json.put("description", Description);
		json.put("manufacturer", Manufacturer);
		json.put("model", Model);
		json.put("url", Url);
		json.put("image", Image);
		
		httpRequest.body(json.toJSONString());
		httpRequest.header("Content-Type", "application/json");
		
		Response response = httpRequest.request(Method.POST, "/products");
		String responseBody = response.getBody().asString();
		System.out.println("the response body is==>" +responseBody);
	   System.out.println(response.getStatusLine());
	   System.out.println(response.getStatusCode());
		
	}
}
