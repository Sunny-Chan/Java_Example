import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;


/**
 *  Tests the GraphView class by creating an object of type GraphView and adding components to it. 
 *  Creates one container of type JFrame and adds an object of type GraphView.
 */
public class TestGraphView 
{

	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 600;

	/**
	 * Builds a list of countries to debug.
	 */
	private void debugListOfCountries(Country [] allCountries)
	{
		// TO COMPLETE
	}


	private LinkedList<Country> buildCountryList(Country [] allCountries)
	{	
		JFrame frame = new JFrame("Cellular Graph");

		String userInput = (String)JOptionPane.showInputDialog(
				frame,
				"Enter the number of countries to graph:\n",
				"Input",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"5");

		int requestedSize = Integer.parseInt(userInput);

		// Build the list out of a random selection of countries.
		Random random = new Random();
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < requestedSize; i++)
		{
			int selectedIndex = random.nextInt(allCountries.length);
			selectedCountries.add(allCountries[selectedIndex]);
		}

        // Test output
        // ** TO BE DELETED **
        System.out.println(selectedCountries);

		return selectedCountries;
	}
	
	
	private void initializeGui(LinkedList<Country> selectedCountries)
	{
		JFrame frame = new JFrame("Cellular Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TO COMPLETE: Select a layout for your frame

		//frame.setLayout(new FlowLayout());
        // Grid layout might be better
		frame.setLayout(new GridLayout(1,2,3,3));


		// TO COMPLETE: Specify the size of your graph view based on your other panels
		int graph_panel_size = FRAME_WIDTH;

		GraphView myPlots = new GraphView(graph_panel_size, FRAME_HEIGHT, selectedCountries);	
        

		// TO COMPLETE: add the GraphView object to your layout
		frame.add(myPlots);

		// Draw the legend
        // graph_panel_size = 80;
		// GraphView legendPanel = new GraphView(graph_panel_size, FRAME_HEIGHT, selectedCountries);	

		// TO COMPLETE: add the legend panel to your frame
		//frame.add(/*legendPanel, etc */);
		//frame.add(legendPanel);


		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);		
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

			current = new Country(countryNames[countryIndex]);	

			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}
			countries[countryIndex] = current;
		}

		TestGraphView application = new TestGraphView();

		// Note: Initially, to test your output you may hard code the number of 
		//       countries added, and the array positions selected.
		//		 However, make sure to comment this out before submitting your work.
		//application.debugListOfCountries(countries);

		//application.initializeGUI(countries);
		LinkedList<Country> listOfCountries = application.buildCountryList(countries);
		application.initializeGui(listOfCountries);

	}
}
