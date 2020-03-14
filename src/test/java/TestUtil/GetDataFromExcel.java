package TestUtil;

import java.util.ArrayList;

public class GetDataFromExcel {
	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getdata() {
		
		ArrayList<Object[]> myData= new ArrayList<Object[]>();
		reader = new Xls_Reader("C:\\Users\\alef1\\eclipse-workspace\\RestApiAutomation\\"
				+ "src\\test\\java\\TestData\\ApiTestData.xlsx");
		//int rowNum= reader.getRowCount("TestData");
		for(int rowNum = 2; rowNum<= reader.getRowCount("TestData"); rowNum++ ) {
			String Name= reader.getCellData("TestData", "name", rowNum);
			String Type= reader.getCellData("TestData", "type", rowNum);
			String Price= reader.getCellData("TestData", "price", rowNum);
			String Shipping= reader.getCellData("TestData", "shipping", rowNum);
			String Upc= reader.getCellData("TestData", "upc", rowNum);
			String Description= reader.getCellData("TestData", "description", rowNum);
			String Manufacturer= reader.getCellData("TestData", "manufacturer", rowNum);
			String Model= reader.getCellData("TestData", "model", rowNum);
			String Url= reader.getCellData("TestData", "url", rowNum);
			String Image= reader.getCellData("TestData", "image", rowNum);
			
			Object []ob = {Name, Type, Price, Shipping, Upc, Description, Manufacturer, Model, Url,Image};
			
			myData.add(ob);
		}
		return myData;
	}



	

}
