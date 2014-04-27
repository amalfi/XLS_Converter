package com.xlsconverter.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

  private String inputFile;

  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }
 //Dane do odczytania :
  //Nip dostawcy, Nazwa dostawcy, Kod pocztowy, Miejscowoœæ, Adres, Nr rachunku bankowego
  public void read() throws IOException
  {
    File inputWorkbook = new File(inputFile);
    Workbook w;
    //-----------------------------------------
    String sCurrentCompanyName = "";  
    ArrayList<String> sCompanyList = new ArrayList<String>();
    
    WriteTXTFile myFile = new WriteTXTFile();
    
    //----------------------------------------
    try 
    {
      w = Workbook.getWorkbook(inputWorkbook);
      // Get the first sheet
      Sheet sheet = w.getSheet(0);
      // Loop over first 10 column and lines
      for(int i=0; i<sheet.getRows(); i++)
      {		
    	  Cell cell =  sheet.getCell(3, i);  
    	  String sRow = cell.getContents();

    	  if(sRow.equals("Nazwa"))
    	  {
    		  sCurrentCompanyName = sheet.getCell(4,i).getContents().toString();
    		  System.out.println(sCurrentCompanyName);
    		  sCompanyList.add(sCurrentCompanyName);
    	  }
      }
      
      String input = myFile.readTextFile("C:/Users/Marcin/Desktop/Magna Dostawcy/Testing.txt");
      System.out.println(input);
      myFile.writeTextFile("C:/Users/Marcin/Desktop/Magna Dostawcy/Testing2.txt", sCompanyList);
      
      /*
       Petla pobierajaca odpowiednie dane z sekcji z dostawca
		for(int i=(numer kolumny D); i<columny.size() ; i++)
		{
			(if columna.zawartosc.equals(Nazwaa)'
			{
				System out println(Nazwa)
			}
		}
       */
      
     /* for (int j = 0; j < sheet.getColumns(); j++)
      {
	        for (int i = 0; i < sheet.getRows(); i++)
	        {
	          Cell cell = sheet.getCell(j, i);
	          CellType type = cell.getType();
	          if (type == CellType.LABEL) {
	            System.out.println("I got a label "
	                + cell.getContents());
	          }
	
	          if (type == CellType.NUMBER) {
	            System.out.println("I got a number "
	                + cell.getContents());
	          }
	
	        }
      }*/
    } catch (BiffException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    ReadExcel test = new ReadExcel();
    test.setInputFile("C:/Users/Marcin/Desktop/Magna Dostawcy/dostawcy.xls");
    test.read();
  }

} 