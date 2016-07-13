/**
 * ColoredPoint holds the color assigned to each point
 */

import java.awt.Point;
import java.awt.Color;

public class ColoredPoint extends Point {
	private Color color;
	private double originalX;
	private double originalY;
	
	public ColoredPoint (Color color, double mappedX, double mappedY, double originalX, double originalY) {

        // not sure if the mappedX Y is double or int
        // i assume double, instruction suggest casting back to int when plotting
        // may be there is two way of doing this.
        // either this for int
        super((int)mappedX,(int)mappedY);
        // or this for double, 
        //super.setLocation(mappedX,mappedY);
        //super.x = (int)mappedX;
        //super.y = (int)mappedY;
		this.color = color;
		this.originalX = originalX;
		this.originalX = originalY;
	}
	
	
	/**
	 * getColor() method returns the color instance variable.
	 * @return
	 */
	public Color getColor () {
		return this.color;
	}
	
	/**
	 * getLabel() used to label the plotted points
	 * @return
	 */
	public String getLabel() {
        return 
        "(" + originalX + ", " + originalY + ")";
	}

}
