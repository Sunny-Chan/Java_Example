 /**
  * Date: 03/14/2015
  * at 9:26pm 53seconds
  * ## Happy Pi Day
  *
  * Description:
  * this is a class GraphView  which extends JPanel. 
  * This class update what is drawn to the frame via the 
  * method “paintComponent”
  *
  * @author Wai Hei (Sunny) Chan
  */

import javax.swing.JPanel;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Random;


public class GraphView extends JPanel
{    
    // window size
    private int width, height;
    private int margin;

    // totally unnecessary data points thats very confusing
    // use to map the data points to the drawing area with map()
    // (such as panel’s width and height).
    private int plottedXmin, plottedXmax, plottedYmin, plottedYmax;
    // years values
    private int dataMinX, dataMaxX;
    // subscriptions values
    private double dataMinY, dataMaxY;
    // font of graphics objects
    private Font font;
    private LinkedList<ColoredPoint> PlottedDataSet;
    private final int POINT_SIZE = 5;

    /**
     * Constructor
     */
    public GraphView(int newWidth, int newHeight, LinkedList<Country> countries)
    {
        this.width = newWidth;
        this.height = newHeight;
        this.margin = 40;
        // assuming this is the right way to immeplement
        // super confusing as the data points are not needed.
        // makes it harder to figure out the solution with 3 sets of data points
        this.plottedXmin = margin;;
        this.plottedXmax = width - margin;
        this.plottedYmin = height - margin;
        this.plottedYmax = margin;

        font = new Font("Serif", Font.PLAIN,12);

        // new LinkedList to store plotted data
        PlottedDataSet = new LinkedList<ColoredPoint>();
        
        Iterator<Country> iter = countries.iterator();
        Country other = null;
        // traverse country
        while(iter.hasNext())
        {
            other = iter.next();
        
            this.dataMinX = other.getMinYear();
            this.dataMaxX = other.getMaxYear();
            this.dataMinY = 0.0;
            this.dataMaxY = other.getMaxSubscription();
            
            Iterator<SubscriptionYear> iter2 = other.getSubscriptions().iterator(); 
            while(iter2.hasNext())
            {
                SubscriptionYear current = iter2.next();
                double originalX = current.getYear();
                double originalY = current.getSubscription();

                double mappedX = map(originalX, dataMinX, dataMaxX, plottedXmin, plottedXmax);
                double mappedY = map(originalY, dataMinY, dataMaxY, plottedYmin, plottedYmax);
                PlottedDataSet.add(new ColoredPoint(new Color(125,125,125), mappedX, mappedY, originalX, originalY));

            }
        }
    }


    /**
     * Method used for plotting the graph
     * to be implement...
     */
    public void paintComponent(Graphics g2d)
    {
        Iterator<ColoredPoint> iter = PlottedDataSet.iterator();
        ColoredPoint dot = null;
        while(iter.hasNext())
        {
            dot = iter.next();
            g2d.setColor(dot.getColor()); 
            g2d.fillOval((int)dot.getX(),(int)dot.getY(),POINT_SIZE, POINT_SIZE);
            g2d.drawString(dot.getLabel(), (int)dot.getX(),(int)dot.getY());
        }
    }

    /**
     * map function used to map the numbers to the available space of our screen
     * maps data points to our drawing area
     */
    static public final double map(double value, double dataMin, double dataMax, double plottedMin, double plottedMax) 
    {
        return plottedMin+(plottedMax-plottedMin)*
               ((value-dataMin)/(dataMax-dataMin));
    } 
}
