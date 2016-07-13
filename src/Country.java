/** 
 *  Date: 03/02/2015
 *  Description:
 *  Class that store the cellular subscription data and country name *
 *  @author Wai Hei (Sunny) Chan
 */

//package part02;
import java.util.Iterator;
public class Country
{
    // variables
    private String name;
    private LinkedList<SubscriptionYear> subscriptions;
    private int counter;
    private int minYear;
    private int maxYear;
    private double minSubscription;
    private double maxSubscription;
    /* wrong
    private int newYear;
    private double newSub;
    */


    /**
     * constructor
     */
    public Country(String country)
    {
        this.name = country;
        this.subscriptions = new LinkedList<SubscriptionYear>();
        this.counter = 0;
        this.minYear = 9999;
        this.maxYear = 0;
        this.minSubscription = 999;
        this.maxSubscription = 0;
    }
    
    /**
     * this is not needed for project 5
     */
    /*
    public Country(String country, int numYears)
    {
        this.name = country;
        this.subscriptions = new SubscriptionYear[numYears];
        this.counter = 0;
    }
    */

    /**
     * add a new SubscriptionYear generic object to LinkedList
     */
    public void addSubscriptionYear(int newYear, double newSub)
    {
        /* wrong
    	this.newYear = newYear;
    	this.newSub = newSub;
        */
        if(newYear>this.maxYear)
            this.maxYear = newYear;
        if(newYear<this.minYear)
            this.minYear = newYear;
        if(newSub>this.maxSubscription)
            this.maxSubscription = newSub;
        if(newSub<this.minSubscription)
            this.minSubscription = newSub;
        //subscriptions.add(new SubscriptionYear(newYear, newSub));
        SubscriptionYear testss = new SubscriptionYear(newYear, newSub);
        subscriptions.add(testss);
        counter++;
    }
    

    // get the sum of the requested data
    public double getNumSubscriptionsForPeriod(int start, int end)
    {

        // validation for start and end year. Throws exception
        validateYear(start, end);

        double sum = 0;
        //Iterator<SubscriptionYear> itr = subscriptions.iterator();
        Iterator<SubscriptionYear> iter = subscriptions.iterator();
        while(start<=end)
        {
            SubscriptionYear current = iter.next();
            if(current.getYear()==start)
            {
                sum+=current.getSubscription();
                start++;
            }

        }
        return sum;
    }

    public String getName()
    {
        return this.name;
    }

    public LinkedList<SubscriptionYear> getSubscriptions()
    {
        if(this.subscriptions == null)
            System.out.println("getsub");
        return this.subscriptions;
    }

    // validation method for start and end year request
    private void validateYear(int start, int end)
    {
        // This implementation still works with the same efficiency for this project
        // but I guess not for search algorithms
        /*
        if(start<subscriptions[0].getYear())
            throw new IllegalArgumentException("Start year lower than existing data year");
        if(end>subscriptions[counter-1].getYear())
            throw new IllegalArgumentException("End year larger than existing data year");
        if(start>end)
            throw new IllegalArgumentException("Start year larger than end year");
        */

        if(start<minYear)
            throw new IllegalArgumentException("Start year lower than existing data year");
        if(end>maxYear)
            throw new IllegalArgumentException("End year larger than existing data year");
        if(start>end)
            throw new IllegalArgumentException("Start year larger than end year");
        
        
    }
        
    // overwrite default toString method
    public String toString()
    {
        Iterator<SubscriptionYear> iter = subscriptions.iterator();
        String string = this.name + ":\n";
        for(int i=0; i<this.counter; i++)
        {
            string += iter.next() + " ";
        }
        string += "\n";
        return string;
    }

    /**
     * overwrite defaul equals method
     */
    public boolean equals(Object obj)
    { 
        if(this==obj)
            return true;

        // Same/better than if(obj instanceof Country)
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        Country current = (Country)obj;
       
        return 
        this.name.equals(current.getName());
        // commenting out calling to compare Subscription class to resolve problem
        /*
        this.name == current.getName();
        &&
        (this.subscriptions == current.getSubscriptions() || (subscriptions != null && this.subscriptions.equals(current.getSubscriptions())));
        */
    }


    /**
     * overwrite hashCode() to make sure it returns the right value
     * if two objects are equal, that is obj1.equals(obj2) is true 
     * then, obj1.hashCode() and obj2.hashCode() must return same int
     */
    public int hashCode()
    {
        // hash needs to be prime to result in distinct hashcode for distinct obj
        int hash = 7;
        hash = hash * 31 + (name == null ? 0 : name.hashCode());
        hash = hash * 31 + (subscriptions== null ? 0 : subscriptions.hashCode());
        return hash;
    }


    /**
     * Added by Sunny for P6 for GraphView
     */ 
    public int getMinYear()
    {
        return this.minYear;
    }

    public int getMaxYear()
    {
        return this.maxYear;
    }

    public double getMinSubscription()
    {
        return this.minSubscription;
    }

    public double getMaxSubscription()
    {
        return this.maxSubscription;
    }

}
