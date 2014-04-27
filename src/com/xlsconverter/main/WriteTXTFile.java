package com.xlsconverter.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteTXTFile {

  public String readTextFile(String fileName) 
  {
    String returnValue = "";
    FileReader file = null;
    String line = "";
    try {
      file = new FileReader(fileName);
      BufferedReader reader = new BufferedReader(file);
      while ((line = reader.readLine()) != null) {
        returnValue += line + "\n";
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException("File not found");
    } catch (IOException e) {
      throw new RuntimeException("IO Error occured");
    } finally {
      if (file != null) {
        try {
          file.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return returnValue;
  }

  public void writeTextFile(String fileName, /*String*/ ArrayList<String> s)
  {
    FileWriter output = null;
    try 
    {
      output = new FileWriter(fileName);
      BufferedWriter writer = new BufferedWriter(output);
      String sWholeString = "";
      for(int i=0; i<s.size(); i++)
      {
    	String currentString = s.get(i);  
    	writer.write(currentString); 
        writer.newLine();
        
    	//sWholeString = sWholeString + "\n" + currentString;
      }
      
    } 
    catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (output != null) 
      {
        try {
          output.close();
        } catch (IOException e) 
        {
          e.printStackTrace();
        }
      }
    }

  }
} 