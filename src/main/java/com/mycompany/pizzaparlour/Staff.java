
package com.mycompany.pizzaparlour;

import java.io.Serializable;

/**
 * Staff
 *
 * Date: 17/01/2020 
 * School: Northview Heights Secondary School
 *
 * The following class carries the details and properties of a Staff Member at
 * the pizza parlour. This includes properties that both Managers and Employees
 * share, such as a name, number of shifts, or staff IDs. The following class is
 * a parent class to Manager and Employee classes. It also contains methods that
 * apply to both Managers and EMployees such as changing your password.
 *
 * @author Nathan Tang
 * @version 1.0
 */

public class Staff implements Serializable {
    /* 
     implementing serializable allows objects in this class to be converted into a byte stream and 
     loaded onto a file (.dat file) that is saved everytime the program is finished running and loaded
     everytime the program begins to run. 
    
     Example of/Related to: FILE INPUT/OUTPUT
     */

    private String fname, lname, password;
    private int staffID;
    private int numShifts;
    //ENCAPSULATION is used to make fields that are exclusive to this class and can only be accessed
    //by other classes in the package using getters and setters as seen below.

    public Staff(String fname, String lname, String password, int staffID, int numShifts) {
        this.fname = fname;
        this.lname = lname;
        this.staffID = staffID;
        this.password = password;
        this.numShifts = numShifts;
    }

    public int getStaffID() {
        return this.staffID;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return (this.fname + " " + this.lname);
    }

    public int getNumShifts() {
        return this.numShifts;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The following method verifies that the old password matches with the one
     * recorded in order to issue a new password.
     *
     * @param currentPassword The current password that the Staff Member carries
     * @param newPassword The new password that replaces the old one
     */
    public void changePassword(String currentPassword, String newPassword) {

        if (currentPassword.equals(this.getPassword())) {
            this.setPassword(newPassword);
            System.out.println("SUCCESSFUL PASSWORD CHANGE!");
        } else {
            System.out.println("UNSUCCESSFUL PASSWORD CHANGE!");
        }

    }

    /**
     * Returns the position of Staff Member.
     *
     * @return String position of Staff Member
     */
    public String checkPosition() {
        if (this instanceof Manager) {
            return "MANAGER";
        } else {
            return "EMPLOYEE";
        }
    }

    @Override
    public String toString() {
        return ("\n\n" + this.checkPosition() + "\nName: " + this.getName() + "\nStaff ID: " + this.getStaffID() + "\nPassword: " + this.getPassword() + "\nNumber of Shifts Per Week: " + this.getNumShifts());
    }

}
