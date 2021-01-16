
package com.mycompany.pizzaparlour;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Customer
 * 
 * Date: 17/01/2020 
 * School: Northview Heights Secondary School
 *
 * The following class carries the details and properties of a Customer at the
 * pizza parlour. This include properties of a regular day customer such as a name,
 * number of pizzas ordered, and a loyalty program which counts the number of pizzas
 * until a customer is able to get a free one. This allows us to make a regular day cutomer
 * in a standard shop, and record the details of these customers or make an order.
 * 
 * @author Nathan Tang
 * @version 1.0
 */

public class Customer implements Serializable {
    /* 
     implementing serializable allows objects in this class to be converted into a byte stream and 
     loaded onto a file (.dat file) that is saved everytime the program is finished running and loaded
     everytime the program begins to run. 
    
     Example of/Related to: FILE INPUT/OUTPUT
     */
    
    private String fname, lname;
    private int numPizzasOrdered, untilFree;
    private double amountSpent;
    //ENCAPSULATION is used to make fields that are exclusive to this class and can only be accessed
    //by other classes in the package using getters and setters as seen below.
    
    private DecimalFormat df = new DecimalFormat("0.00");

    public Customer(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
        this.numPizzasOrdered = 0;
        this.amountSpent = 0;
        this.untilFree = 5;
    }

    public double getAmountSpent() {
        return this.amountSpent;
    }

    public String getName() {
        return (this.fname + " " + this.lname);
    }
    
    public int getNumberOfPizzasOrdered() {
        return this.numPizzasOrdered;
    }
    
    public int getUntilFree() {
        return this.untilFree;
    }
    
    /**
     * resets the amount of pizzas needed to be purchased until the next free one is issued.
     */
    public void freePizza() {
        this.untilFree = 5;
    }
    
    /**
     * Adds the amount spent on the current trip to the overall amount spent at the store
     * 
     * @param amnt The amount spent on pizza
     */
    public void addAmountSpent(double amnt) {
        this.amountSpent += amnt;
    }
    
    /**
     * Adds number of pizzas ordered to the overall number of pizzas ordered by a customer
     * 
     * @param amnt The number of pizzas ordered
     */
    public void addPizza(int amnt) {
        this.numPizzasOrdered += amnt;
    }
    
    @Override
    public String toString() {
        return ("\n" + "\nName: " + this.getName() + "\nNumber of Pizzas Ordered in Total: " + this.getNumberOfPizzasOrdered() + "\nTotal Amount Spent: $" + df.format(this.getAmountSpent()));
    }
}
