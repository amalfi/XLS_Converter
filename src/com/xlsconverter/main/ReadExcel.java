package com.xlsconverter.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
    String sCurrentCity = "";
    String sCurrentStreet="";
    String sCurrentNip="";
    boolean nipFieldEmpty=false;
    HashMap<String, ArrayList<String>> mapOfValues = new HashMap<String,ArrayList<String>>(); //mapa zawieraj¹ce arrayListy bêd¹ce odwzorowaniem kolumn z danymi (dostawcy, nipy itd)
    
    ArrayList<String> sCompanyList = new ArrayList<String>();
    ArrayList<String> sNipList = new ArrayList<String>();
    ArrayList<String> sPostalCodeList = new ArrayList<String>();
    ArrayList<String> sStreetList = new ArrayList<String>();
    ArrayList<String> sCityList =  new ArrayList<String>();
    
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
    	  if(sRow.equals("Miasto"))
    	  {
    		  sCurrentCity = sheet.getCell(4,i).getContents().toString();
    		  System.out.println(sCurrentCity);
    		  sCityList.add(sCurrentCity);
    	  }
    	  if(sRow.equals("Ulica"))
    	  {
    		  sCurrentStreet = sheet.getCell(4,i).getContents().toString();
    		  System.out.println(sCurrentStreet);
    		  sStreetList.add(sCurrentStreet);
    	  }
    	  if(sRow.equals("Nr pod. 1"))
    	  {
    		  sCurrentNip= sheet.getCell(4,i).getContents().toString();
    		  System.out.println(sCurrentNip);
    		  if(sCurrentNip.equals("")==true)
    		  {
    			  nipFieldEmpty=true;
    		  }
    		  
    		  sNipList.add(sCurrentStreet);
    	  }
    	  if(sRow.equals("Informacja") && nipFieldEmpty==true)
    	  {
    		  sCurrentNip= sheet.getCell(4,i).getContents().toString();
    		  System.out.println(sCurrentNip);
    		  sNipList.add(sCurrentNip);
    		
    	  }
    	  
    	  
    	  mapOfValues.put("Nipy", sNipList);
    	  mapOfValues.put("Nazwy", sCompanyList);
    	  mapOfValues.put("Miasta", sCityList);
    	  mapOfValues.put("Ulice", sStreetList);
    	  
      }
      
      String input = myFile.readTextFile("C:/Users/Marcin/Desktop/Magna Dostawcy/Testing.txt");
      System.out.println(input);
      myFile.writeTextFile("C:/Users/Marcin/Desktop/Magna Dostawcy/Testing2.txt", /*sCompanyList*/ mapOfValues);
      
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
    } catch (BiffException e)
    {
    	
      e.printStackTrace();
      System.out.println(e.getMessage());
      
    }
  }

  public static void main(String[] args) throws IOException {
    ReadExcel test = new ReadExcel();
    test.setInputFile("C:/Users/Marcin/Desktop/Magna Dostawcy/dostawcy.xls");
    test.read();
  }

} 