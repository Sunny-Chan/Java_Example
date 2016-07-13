//package part02;

import java.util.Random;
import java.util.Scanner;


/**
 *  Tests the LinkedList class by creating multiple objects of type Node.
 *  Creates one object of type CSVReader class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVReader object to create objects of type Country class.
 *  Then adds the newest country read into the singly linked list.
 */
public class TestGenericList 
{

	/**
	 * Builds a list of countries to debug.
	 */
	private void debugListOfCountries(Country [] allCountries)
	{
	    		// TO COMPLETE
	}
	
    private int processSelection(int selection, LinkedList<Country> selectedCountries)
    {
        Scanner input = new Scanner(System.in);
        switch (selection) {
            case 1:  return 1;
            case 2:  System.out.println("divided index: " + selectedCountries.getNodeCount()/2);
                     return selectedCountries.getNodeCount()/2;
            case 3:  return selectedCountries.getNodeCount();
            case 4:  System.out.println("Enter an index within 1 - " 
                     + (selectedCountries.getNodeCount() + 1)); 
                     int pos = input.nextInt();
                     return pos;
            case 5:  System.out.println(selectedCountries);
                     return 5;
            default: return 6;
        }
    
    }

    private void extraTest(Country[] allCountries)
    {
	    Scanner keyboard = new Scanner(System.in);
		System.out.println("How many countries do you want to add to the list?");
		int requestedSize = Integer.parseInt(keyboard.nextLine());
		
	    // Build the list out of a random selection of countries.
		Random random = new Random();
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < requestedSize; i++)
		{
			int selectedIndex = random.nextInt(allCountries.length);
			selectedCountries.add(allCountries[selectedIndex]);
		}
		
		System.out.println("List of countries:\n" + selectedCountries);
        
        int selection;
        //int userIndex = 0;
        do{
            System.out.print(
            "There are " + selectedCountries.getNodeCount() + " nodes\n"+
            "You may add at index 1 - " + (selectedCountries.getNodeCount()+1)  + "\n"+
            "Select from the following menu:\n" +
            "    1)  Add to front \n"+
            "    2)  Add to back\n"+    
            "    3)  add to middle\n"+ 
            "    4)  Add to index of your choice\n"+
            "    5)  Print List of Countries\n"+
            "    6)  Quit\n");
		    selection = Integer.parseInt(keyboard.nextLine());
            int userIndex = this.processSelection(selection, selectedCountries);
            if(userIndex!=5)
            {
			int selectedIndex = random.nextInt(allCountries.length);
			selectedCountries.insertAtIndex(allCountries[selectedIndex], userIndex);
            }
        }while(selection != 6);
    }

	/**
	 * Builds a random list of countries.
	 */
	private void testRandomListOfCountries(Country [] allCountries)
	{	
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many countries do you want to add to the list?");
		int requestedSize = Integer.parseInt(keyboard.nextLine());
		
		
		// Build the list out of a random selection of countries.
		Random random = new Random();
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < requestedSize; i++)
		{
			int selectedIndex = random.nextInt(allCountries.length);
			selectedCountries.add(allCountries[selectedIndex]);
		}
		
		
		// Note: To debug your list, comment this line in
		System.out.println("List of countries: " + selectedCountries);
		
		
		// Check if the name of a country is in the list.
		// If the country is found, print the details.
		// Otherwise output not found.
		System.out.println("\nWhat country do you want to search for?");
		String countryToFind = keyboard.nextLine();
		Country foundCountry = selectedCountries.contains(new Country(countryToFind));
		if (foundCountry != null)
		{
			System.out.println("Country " + countryToFind + " found with details:\n" + foundCountry);
		}
		else
			System.out.println("Country " + countryToFind + " not found.");


        // Extra Test
        this.extraTest(allCountries);
		
	}


	/**
	 * Includes test examples for class LinkedList.
	 */
	public static void main(String[] args) 
	{		
		// Create and set objects of type Country 
		//
		final String FILENAME = "../resources/cellular.csv";	// Directory path for Mac OS X
		//final String FILENAME = "resources\\cellular.csv";	// Directory path for Windows OS (i.e. Operating System)
		final int NUM_COUNTRIES_TO_TEST = 3;			// Note: Include test cases in addition to 3


		// Parse the CSV data file
		//
		CSVReader parser = new CSVReader(FILENAME);

		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		

		
		// Create and set objects of type Country 
		//
		Country [] countries;
		countries = new Country[NUM_COUNTRIES_TO_TEST];

		Country current;
		countries = new Country[countryNames.length];
		
		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   

			// Note: Initially convert your CountryList to a generic LinkedList and make sure that list builds 
			// 		 correctly using the original Country constructor which takes the numberOfYears to setup
			// 		 the array of subscriptions.
			// Note: Make sure to comment this out before submitting.
			//current = new Country(countryNames[countryIndex], numberOfYears);
			
			// Note: Once you are successful in creating a generic LinkedList of countries, create a
			// 		 LinkedList of SubscriptionYear in the Country class.
			// 	     So, your Country class should no longer have an array of SubscriptionYear objects.
			current = new Country(countryNames[countryIndex]);	
			
			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}
			countries[countryIndex] = current;
		}
			
		TestGenericList application = new TestGenericList();
		
		// Note: Initially, to test your output you may hard code the number of 
		//       countries added, and the array positions selected.
		//		 However, make sure to comment this out before submitting your work.
		//application.debugListOfCountries(countries);
		
		application.testRandomListOfCountries(countries);
	}
}
