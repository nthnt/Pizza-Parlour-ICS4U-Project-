
package com.mycompany.pizzaparlour;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Employee 
 * Date: 17/01/2020 
 * School: Northview Heights Secondary School
 *
 * The following class carries the details and properties of a Employee at the
 * pizza parlour. This includes properties of staff and unique properties of
 * its own such as hourly wage.
 * 
 * @author Nathan Tang
 * @version 1.0
 */

public class Employee extends Staff implements Serializable {
    /* 
     implementing serializable allows objects in this class to be converted into a byte stream and 
     loaded onto a file (.dat file) that is saved everytime the program is finished running and loaded
     everytime the program begins to run. 
    
     The following is a child class and extends parent class, Staff.
    
     Example of/Related to: FILE INPUT/OUTPUT, INHERITANCE
     */

    private double hourlyWage;
    private int ratingsLeft;
    private DecimalFormat df = new DecimalFormat("0.00");
    //ENCAPSULATION is used to make fields that are exclusive to this class and can only be accessed
    //by other classes in the package using getters and setters as seen below.

    public Employee(String fname, String lname, String password, int staffID, int numShifts, double hourlyWage) {
        super(fname, lname, password, staffID, numShifts);
        this.hourlyWage = hourlyWage;
        this.ratingsLeft = 3;
    }

    public double getHourlyWage() {
        return Double.parseDouble(df.format(this.hourlyWage));
    }

    public int getRatingsLeft() {
        return this.ratingsLeft;
    }

    public void setHourlyWage(double amount) {
        this.hourlyWage = amount;
    }
    
    /**
     * Decrements the number of ratings the employee has left.
     */
    public void subtractRatings() {
        this.ratingsLeft--;
    }

    public String toString() {
        return ("\n\n" + this.checkPosition() + "\nName: " + this.getName() + "\nStaff ID: " + this.getStaffID() + "\nPassword: " + this.getPassword() + "\nNumber of Shifts Per Week: " + this.getNumShifts() + "\nHourly Wage: $" + this.getHourlyWage());
    }
}
