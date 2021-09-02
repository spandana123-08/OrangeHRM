package com.OrangeHRM.constants;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class TestDataFromExcel {
	String testName="LoginTest";
	public Excelreader xls=new Excelreader("C:\\Users\\spand\\eclipse-workspace\\OrangeHRM\\Excel\\Data.xlsx");

	@org.testng.annotations.Test(dataProvider="getdata")
	public void test(Hashtable<String, String> data) {
		// TODO Auto-generated method stub
	System.out.println(data.get("Runmode")+"--"+data.get("Browser")+"--"+data.get("Username")+"--"+data.get("Password")+"--"+data.get("ExpectedResult"));

	}
	
	@DataProvider
	public  Object[][] getdata(){
	 
		int testCasseStateIndex= 0;
		String SheetName=HRMConstants.TESTDATA_SHEET;
		for(int i=0;i<=xls.getRowCount(SheetName);i++ ){
			
			if (testName.equals(xls.getCellData(SheetName, 0, i))){
				testCasseStateIndex=i;
				break;
			}
		}
		System.out.println("Test Starts From - "+testCasseStateIndex);
		int colStartIndex=testCasseStateIndex+1;
		int col=0;
				while(!xls.getCellData(SheetName, col, colStartIndex).equals("")){
					col++;
				}
				System.out.println("Toatal cols are -"+col);
				System.out.println("Test Starts From - "+testCasseStateIndex);
				int dataStartIndex=testCasseStateIndex+2;
				int row=0;
						while(!xls.getCellData(SheetName, row, dataStartIndex).equals("")){
							row++;
						}
						System.out.println("Toatal rows are -"+row);
						Object[][]data=new Object[row][1];
						Hashtable<String, String> tabl=null;
				for(int i=dataStartIndex;i<(dataStartIndex+row);i++){

					tabl=new Hashtable<String, String>();
					
					for(int j=0;j<col;j++){
						tabl.put(xls.getCellData(SheetName, j, colStartIndex), xls.getCellData(SheetName, j, i));
						//System.out.print(xls.getCellData("Test Data", j, i)+"---");
					}
					//System.out.println();
					data[i-dataStartIndex][0]=tabl;
				}
				return data;
				
			}

}
