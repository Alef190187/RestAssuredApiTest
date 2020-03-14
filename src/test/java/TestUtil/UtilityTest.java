package TestUtil;

public class UtilityTest {
	static Xls_Reader reader;
	public static String[][] getdataFromExcel() {
		
		
	     reader = new Xls_Reader("C:\\Users\\alef1\\eclipse-workspace\\RestApiAutomation\\"
			+ "src\\test\\java\\TestData\\ApiTestData.xlsx");
	     
	     
	   int rowNum =reader.getRowCount("TestData");
	   int colNum = reader.getColumnCount("TestData");
	   String empdata[][] = new String[rowNum][colNum];
	   for(int i = 1; i<=rowNum; i++){
		   
		  for(int j=0; j<=colNum; j++) {
			  empdata [i-1][j] = reader.getCellData("TestData", i, j);
			   
		   }
	   }
	   return empdata;
}
}