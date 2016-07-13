/** Programmer: Wai Hei (Sunny) Chan
 *  Date: 03/02/2015
 *  Description:
 *  Class SubscriptionYear used to setup year and subscription 
 */

//package part02;
public class SubscriptionYear
{
    // instance variables
    private int year;
    private double subscription;


    public SubscriptionYear(int newYear, double newSub)
    {
        setYear(newYear);
        setSubscription(newSub);
    }

    // Setter / mutator
    private void setYear(int newYear)
    {
        this.year = newYear;
    }
    
    private void setSubscription(double newSub)
    {
        this.subscription = newSub;
    }

    // Getter / accessor
    public int getYear()
    {
        return this.year;
    }

    public double getSubscription()
    {
        return this.subscription;
    }

    // overwrite toString()
    public String toString()
    {
        return String.valueOf(this.subscription);
    }

    /**
     * overwrite defaul equals method
     */
    public boolean equals(Object obj)
    { 
        System.out.println("in sub euqal1");
        if(this==obj)
            return true;

        System.out.println("in sub euqal2");
        // Same/better than if(obj instanceof Country)
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        System.out.println("in sub euqal3");
        SubscriptionYear current = (SubscriptionYear)obj;
        return 
        this.year == current.getYear() &&
        this.subscription == current.getSubscription();
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
        hash = hash * 31 + year;
        long bits = Double.doubleToLongBits(subscription);
        hash = hash * 31 + (int)(bits ^ (bits >>> 32));
        return hash;
    }
}

