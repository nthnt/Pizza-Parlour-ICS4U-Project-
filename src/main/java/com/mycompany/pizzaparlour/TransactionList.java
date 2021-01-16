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
 * TransactionList
 * 
 * Date: 17/01/2020 
 * School: Northview Heights Secondary School
 *
 * The following class manipulates all instances of Transactions and their
 * details. This includes reading and writing from text files. Saving to and
 * Loading from byte files, and sorting arraylists of transactions.
 *
 *
 * @author Nathan Tang
 * @version 1.0
 */

public class TransactionList implements Serializable {
    /* 
     implementing serializable allows objects in this class to be converted into a byte stream and 
     loaded onto a file (.dat file) that is saved everytime the program is finished running and loaded
     everytime the program begins to run. 
    
     Example of/Related to: FILE INPUT/OUTPUT
     */

    protected ArrayList<Transaction> lists = new ArrayList<>();
    //ARRAY(LIST)OF OBJECTS

    protected File file = new File("transaction.txt");
    //indicates the text file to write the transaction details to

    protected Path path = Paths.get("transaction.txt");
    //indicates the text file to read transaction details from

    /**
     * Adds a transaction to the Transaction arraylist of this class to be
     * manipulated
     *
     * @param tr The transaction to be added to the arraylist of this class
     */
    public void addTransaction(Transaction tr) {
        (this.lists).add(tr);
    }

    /**
     * writes the details of the arraylist of Transactions to a text file using
     * a FileWriter
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void writeToTransaction() {
        try {
            FileWriter fr = new FileWriter(file);
            for (int i = 0; i < lists.size(); i++) {
                fr.write((lists.get(i)).toString());
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
    public void readFromTransaction() {
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
     * Saves the entire arraylist of Transactions to a byte file. This used used
     * after every program run in order to save everything for future logins and
     * uses.
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void saveTransaction() {
        try {
            ObjectOutputStream hannah;
            hannah = new ObjectOutputStream(new FileOutputStream("transaction.dat"));
            hannah.writeObject(this.lists);
            hannah.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the entire arraylist of Transactions from specified byte file. This
     * used used at the beginning of the program run in order to get previously
     * saved and manipulated data.
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void loadTransaction() {
        try {
            ObjectInputStream nathan = new ObjectInputStream(new FileInputStream("transaction.dat"));
            this.lists = (ArrayList<Transaction>) nathan.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The following method sorts the arraylist of Transactions by Price using
     * insertion sort Insertion sort is better for larger sets of elements, and
     * because a transaction object is made after every purchase of pizza, it is
     * more efficient to use insertion sort as there will be many elements as
     * more transactions are made.
     * 
     * Example of/Related to: SORTING
     */
    public void sortPrice() {
        for (int i = 1; i < lists.size(); i++) {
            Transaction value = ((lists.get(i)));

            int j = i - 1;
            while (j >= 0 && (lists.get(j).getPrice()) > (lists.get(i).getPrice())) {
                lists.set(j + 1, lists.get(j));
                j--;
            }

            lists.set(j + 1, value);
        }
    }

    /**
     * The following method sorts the arraylist of Transactions by Date using
     * insertion sort Insertion sort is better for larger sets of elements, and
     * because a transaction object is made after every purchase of pizza, it is
     * more efficient to use insertion sort as there will be many elements as
     * more transactions are made.
     * 
     * Example of/Related to: SORTING
     */
    public void sortDate() {
        for (int i = 1; i < lists.size(); i++) {
            Transaction value = ((lists.get(i)));

            int j = i - 1;
            while (j >= 0 && (lists.get(j).getEpochDate()) > (lists.get(i).getEpochDate())) {
                lists.set(j + 1, lists.get(j));
                j--;
            }

            lists.set(j + 1, value);
        }
    }

}
