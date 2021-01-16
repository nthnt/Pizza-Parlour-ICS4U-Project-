
package com.mycompany.pizzaparlour;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Manager 
 * Date: 17/01/2020 
 * School: Northview Heights Secondary School
 *
 * The following class carries the details and properties of a Manager at the
 * pizza parlour. This includes properties of staff and unique properties of
 * its own such as ratings.
 * 
 * @author Nathan Tang
 * @version 1.0
 */

public class Manager extends Staff implements Serializable {
    /* 
     implementing serializable allows objects in this class to be converted into a byte stream and 
     loaded onto a file (.dat file) that is saved everytime the program is finished running and loaded
     everytime the program begins to run. 
    
     The following is a child class and extends parent class, Staff.
    
     Example of/Related to: FILE INPUT/OUTPUT, INHERITANCE
     */

    private double rating, overall;
    private int numRaters = 0;
    //ENCAPSULATION is used to make fields that are exclusive to this class and can only be accessed
    //by other classes in the package using getters and setters as seen below.
    
    private DecimalFormat df = new DecimalFormat("0.00");

    public Manager(String fname, String lname, String password, int staffID, int numShifts) {
        super(fname, lname, password, staffID, numShifts);
        this.rating = 0;
        this.numRaters = 0;
        this.overall = 5;
    }

    public double getRating() {
        return this.overall;
    }
    
    public void setRating(double amount) {
        this.rating += amount;
        this.numRaters++;
        this.overall = (this.rating / this.numRaters);
    }

    public String toString() {
        return ("\n\n" + this.checkPosition() + "\nName: " + this.getName() + "\nStaff ID: " + this.getStaffID() + "\nPassword: " + this.getPassword() + "\nNumber of Shifts Per Week: " + this.getNumShifts() + "\nRating: " + df.format(this.getRating()));
    }
}
