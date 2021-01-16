
package com.mycompany.pizzaparlour;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CustomerList 
 * 
 * Date: 17/01/2020 
 * School: Northview Heights Secondary School
 *
 * The following class manipulates all instances of Customers and their details.
 * This includes reading and writing from text files. Saving to and Loading from
 * byte files, sorting arraylists of customers and searching for customers in
 * arraylists using customer names.
 *
 *
 * @author Nathan Tang
 * @version 1.0
 */

public class CustomerList implements Serializable {
    /* 
     implementing serializable allows objects in this class to be converted into a byte stream and 
     loaded onto a file (.dat file) that is saved everytime the program is finished running and loaded
     everytime the program begins to run. 
    
     Example of/Related to: FILE INPUT/OUTPUT
     */

    protected ArrayList<Customer> list = new ArrayList<>();
    //ARRAY(LIST)OF OBJECTS

    protected File file = new File("customer.txt");
    //indicates the text file to write the customer details to

    protected Path path = Paths.get("customer.txt");
    //indicates the text file to read customer details from

    /**
     * writes the details of the arraylist of Customers to a text file using a
     * FileWriter
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void writeToCust() {
        try {
            FileWriter fr = new FileWriter(file);
            for (int i = 0; i < list.size(); i++) {
                fr.write((list.get(i)).toString());
            }
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads the details of the specified text file line by line using a Scanner
     * and prints it to console
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void readFromCust() {
        try {
            Scanner scan = new Scanner(path);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println(line);
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the entire arraylist of Customers to a byte file. This used used
     * after every program run in order to save everything for future logins and
     * uses.
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void saveCust() {
        try {
            ObjectOutputStream hannah;
            hannah = new ObjectOutputStream(new FileOutputStream("customer.dat"));
            hannah.writeObject(this.list);
            hannah.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the entire arraylist of Customers from specified byte file. This
     * used used at the beginning of the program run in order to get previously
     * saved and manipulated data.
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void loadCust() {
        try {
            ObjectInputStream nathan = new ObjectInputStream(new FileInputStream("customer.dat"));
            this.list = (ArrayList<Customer>) nathan.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a customer to the Customer arraylist of this class to be manipulated
     *
     * @param customer The Customer to be added to the arraylist of this class
     */
    public void addCust(Customer customer) {
        (this.list).add(customer);
    }

    /**
     * The following searching algorithm searches for a Customer with a
     * specified name and returns it. (linear search)
     *
     * Example of/Related to: SEARCHING
     *
     * @param fullName The full name of the Customer that is requested to be
     * found
     * @return Customer The customer that is desired
     */
    public Customer findUsingName(String fullName) {
        String name = fullName.toUpperCase();

        for (int a = 0; a < list.size(); a++) {
            if ((list.get(a)).getName().equals(name)) {
                return list.get(a);
            }
        }

        return null;
    }
}

