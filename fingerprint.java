import java.util.*;
import java.io.*;
/**
 * Christopher Soto
 * COSC 1437, Lab02, FingerPrint
 * FingerPrint class with constructors and methods used to
 * extract and manipulate fingerprint data from a file
 **/

public class FingerPrint
{
  // declaring fields
  private String[][] data;
  private String name = "";
  private int year,rows,cols;
  
  /**
   * Constructor extracts data to initilize fields from passed string
   * (file name/location)
   * 
   * @param fileName name of file containing fingerprint info
   **/
  public FingerPrint(String fileName) throws IOException
  {
    
    // new scanner object for reading file
    Scanner input = new Scanner(new File(fileName));
    
    // setting fields to values scanned from fingerprint file
    this.name = input.nextLine();
    this.year = input.nextInt();
    this.rows = input.nextInt();
    this.cols = input.nextInt();
    
    // eating next line
    input.nextLine();
    
    // resizing array based off scanned cols/rows from file
    data = new String [this.rows][this.cols];
    
    // temp string to hold remaining file data
    String temp = "";
    
    // counter to track number of chars
    int counter = 0;
    
    // while loop to assign remaining file to temp string
    while(input.hasNext())
    {
      temp += input.nextLine();
    }
    
    // nested for loop to assign each char from temp string
    // to a position in the data array sequentially
    for(int i = 0;i < (rows);i++)
    {
      for(int p = 0;p < (cols-1);p++)
      {
        data[i][p] = String.valueOf(temp.charAt(counter));
        counter++;
      }
    }
    
    // calling setter to assign updated values to array
    setData(data);
    
    input.close();
  }
  
  /**
   * Custom constructor to initialize fields based off
   * passed information
   * 
   * @param name fingerprint name
   * @param year year of fingerprint
   * @param rows rows of data in file
   * @param cols columns of data in file
   **/
  public FingerPrint(String[][] data, String name, int year, int rows, int cols)
  {
    this.data = data;
    this.name = name;
    this.year = year;
    this.rows = rows;
    this.cols = cols;
  }
  
  /**
   * Compares fingerprint object fields to determine if they are equal
   * 
   * @param fingerPrint FingerPrint object to compare
   * @return t/f if object matches or not
   **/
  public boolean equals(FingerPrint fingerPrint)
  {
    if(this.name.equals(fingerPrint.name) && this.year == fingerPrint.year &&
       this.rows == fingerPrint.rows && this.cols == fingerPrint.cols)
    {
      for(int i = 0;i < this.rows;i++)
      {
        for(int p = 0;p < this.cols;p++)
        {
          if(this.data[i][p] == fingerPrint.data[i][p])
            return true;
        }
      }
    }
    return false;
  }
  
  
  /**
   * method to determine the matching accuracy between two fingerprint objects
   * @param fp fingerprint to test against
   * @return matching % as a double
   */
  public String accuracy(FingerPrint fp, int acc)
  {
    String[][] temp = fp.getData();
    double fp1 = fp.getNumberOfPixels();
    double fp2 = this.getNumberOfPixels();
    double counter = 0;
    double r = fp.getRows();
    double c = fp.getCols();
    try{
      if(fp1 >= fp2)
      {
        try{
          for(int i = 0;i < fp.rows;i++)
          {
            for(int p = 0;p < fp.cols;p++)
            {
              if(String.valueOf(fp.data[i][p]).compareToIgnoreCase(String.valueOf(temp[i][p])) == 0)
                counter++;
            }
          }
          counter = (counter/fp2)*100;
        }
        catch(ArrayIndexOutOfBoundsException e){
          System.out.println("Error comparing files.");    
        }
      }
      
      if(fp1 < fp2)
      {
        try{
          for(int i = 0;i < r;i++)
          {
            for(int p = 0;p < c;p++)
            {
              if(String.valueOf(this.data[i][p]).compareToIgnoreCase(String.valueOf(temp[i][p])) == 0)
                counter++;
            }
          }
          counter = (counter/fp1)*100;
        }
        catch(ArrayIndexOutOfBoundsException e){
          System.out.println("Error comparing files.");    
        }
      }
    }
    catch(NullPointerException e){}
    
    String accuracy;
    
    if(counter >= acc)
    {
      accuracy = "Pass: The finger print is a "+counter+" percent match.";
    }
    else
      accuracy = "Fail: The finger print is a "+counter+" percent match.";
    return accuracy;
  }
  
  /**
   * method to determine the matching accuracy between a loaded
   * fingerprint file and a fingerprint database (array)
   * @param fp fingerprint to test against
   * @return matching % as a double
   */

  
  
  
  /**
   * method to get number of pixels from file (cols * rows)
   * 
   * @return int value of pixels (cols * rows)
   **/ 
  public int getNumberOfPixels()
  {
    return this.rows*this.cols; 
  }
  
  /**
   * toString method to print out name, year, and number of pixels
   * from FingerPrint object
   **/
  public String toString()
  {
    return "Finger print for: "+this.name+". Year registered: "+
      this.year+". Number of pixels: "+getNumberOfPixels()+".";
  }
  
  /**
   * Method to print out fingerprint file data using a nested loop
   * 
   **/
  public void getImage()
  {
    for(int i = 0;i < data.length;i++)
    {
      for(int p = 0;p < ((data[0].length)-1);p++)
      {
        System.out.print(data[i][p]);
      }
      System.out.println();
    }
  }
  
  /**
   * Method to print out fingerprint file data using a nested loop
   * 
   **/
  public String printImage()
  {
    String s = "";
    for(int i = 0;i < data.length;i++)
    {
      for(int p = 0;p < ((data[0].length)-1);p++)
      {
        s += data[i][p];
      }
      s += "\n";
    }
    return s;
  }
  
  /**
   * Setter for fingerprint data array
   * @param data array of strings holding fingerprint data
   */
  public void setData(String[][] data)
  {
    this.data = data;
  }
  
  /**
   * Setter for name of fingerprint
   * @param name string containing name of fingerprint
   */
  public void setName(String name)
  {
    this.name = name;
  }
  
  /**
   * Setter for year
   * @param int for year of fingerprint
   */
  public void setYear(int year)
  {
    this.year = year;
  }
  
  /**
   * Setter for number of rows
   * @param int rows in fingerprint object
   */
  public void setRows(int rows)
  {
    this.rows = rows;
  }
  
  /**
   * Setter for number of columns
   * @param int columns in fingerprint object
   */
  public void setCols(int cols)
  {
    this.cols = cols;
  }
  
  /**
   * Getter for data array
   * @return data array
   **/
  public String[][] getData()
  {
    return this.data;
  }
  
  /**
   * Getter for fingerprint name value
   * @return fingerprint name
   **/
  public String getName()
  {
    return this.name;
  }
  
  /**
   * Getter for year value
   * @return year of fingerprint
   **/
  public int getYear()
  {
    return this.year;
  }
  
  /**
   * Getter for rows value
   * @return number of rows
   **/
  public int getRows()
  {
    return this.rows;
  }
  
  /**
   * Getter for columns value
   * @return number of columns
   **/
  public int getCols()
  {
    return this.cols;
  }
}
