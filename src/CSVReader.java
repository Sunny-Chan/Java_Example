/** Programmer: Wai Hei (Sunny) Chan
 *  Date: 02/15/2015
 *  Description:
 *  This is a class called CellularData which process and display cellular data 
 *  from different countries for analytical use. 
 */

//package part02;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class CSVReader
{
    // Variables
    private String [] countryNames;
    private int [] yearLabels;
    private double [][] cellularDataTable;
    private int numberOfCountries;

    // constructor
    public CSVReader(String fileName)
    {
        initialize(fileName);
    }
    
    
    // Initialize the variables
    private void initialize(String fileName)
    {
        try
        {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            String currentLine;
            String [] currentArray;

            // skip the title of the data file
            input.nextLine();
            
            // setNumberOfCountries
            currentLine = input.nextLine();
            currentArray = currentLine.split(",");
            setNumberOfCountries(currentArray[1]);

            //setYearLabels
            currentLine = input.nextLine();
            currentArray = currentLine.split(",");
            setYearLabels(currentArray);

            // analys and read & store country and cellular data
            this.countryNames = new String[numberOfCountries];
            this.cellularDataTable = new double[numberOfCountries][yearLabels.length];
            for(int i=0; input.hasNextLine(); i++)
            {
                currentLine = input.nextLine();
                currentArray = currentLine.split(",");
                // store country name
                this.countryNames[i] = currentArray[0];  
                // store cellular data
                for(int j=1; j<currentArray.length; j++)
                    this.cellularDataTable[i][j-1] = Double.parseDouble(currentArray[j]);
            }
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + " not found");
        }
        catch(NullPointerException e)
        {
            System.out.println("Error: No Next (Null Pointer Exception)");
        }
    }


    private void setNumberOfCountries(String num_country)
    {
        numberOfCountries = Integer.parseInt(num_country);
    }


    private void setYearLabels(String [] years) 
    {
        this.yearLabels = new int[years.length-1];
        for(int i=1; i<years.length; i++)
        {
            this.yearLabels[i-1] = Integer.parseInt(years[i]);
        } 
    }

    public String [] getCountryNames()
    {   
        return countryNames;
    }


    public int [] getYearLabels()
    {
        return yearLabels;
    }


    public double [][] getParsedTable()
    {
        return cellularDataTable;
    }


    public int getNumberOfYears()
    {
        return yearLabels.length;
    }

}
