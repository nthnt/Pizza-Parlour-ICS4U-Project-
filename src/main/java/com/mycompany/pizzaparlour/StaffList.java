
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
import java.util.Date;
import java.util.Scanner;

/**
 * StaffList 
 * Date: 17/01/2020 
 * School: Northview Heights Secondary School
 *
 * The following class manipulates all instances of Staff and their details.
 * This includes reading and writing from text files. Saving to and Loading from
 * byte files, sorting arraylists of staff (by Manager OR Employee properties)
 * and searching for customers in arraylists using Staff names or IDs. This
 * mimics the characteristics of real life Staff working at a pizza parlour.
 *
 *
 * @author Nathan Tang
 * @version 1.0
 */

public class StaffList implements Serializable {
    /* 
     implementing serializable allows objects in this class to be converted into a byte stream and 
     loaded onto a file (.dat file) that is saved everytime the program is finished running and loaded
     everytime the program begins to run. 
    
     Example of/Related to: FILE INPUT/OUTPUT
     */

    protected ArrayList<Staff> list = new ArrayList<>();
    //ARRAY(LIST)OF OBJECTS

    protected File file = new File("staff.txt");
    protected File file2 = new File("logs.txt");
    //indicates the text file to write the Staff details to

    protected Path path = Paths.get("staff.txt");
    protected Path path2 = Paths.get("logs.txt");
    //indicates the text file to read Staff details from

    protected Date date = new Date();
    //records the date

    /**
     * writes the details of the arraylist of Staff to a text file using a
     * FileWriter
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void writeToStaff() {
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
    public void readFromStaff() {
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
     * Saves the entire arraylist of Staff to a byte file. This used used after
     * every program run in order to save everything for future logins and uses.
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void saveStaff() {
        try {
            ObjectOutputStream hannah;
            hannah = new ObjectOutputStream(new FileOutputStream("staffing.dat"));
            hannah.writeObject(this.list);
            hannah.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the entire arraylist of Staff from specified byte file. This used
     * used at the beginning of the program run in order to get previously saved
     * and manipulated data.
     *
     * Example of/Related to: FILE INPUT/OUTPUT
     */
    public void loadStaff() {
        try {
            ObjectInputStream nathan = new ObjectInputStream(new FileInputStream("staffing.dat"));
            this.list = (ArrayList<Staff>) nathan.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the who logged in and at what exact moment they logged in to a
     * text file using a FileWriter.
     *
     * @param staff The staff member that logged in
     */
    public void addLogin(Staff staff) {
        try {
            FileWriter fr = new FileWriter(file2, true);
            fr.write(staff.getName() + " logged in on " + date.toString() + "\n");
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
    public void loadLogin() {
        try {
            Scanner scan = new Scanner(path2);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println(line);
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a Staff to the Staff arraylist of this class to be manipulated
     *
     * @param staff The Staff to be added to the arraylist of this class
     */
    public void addStaff(Staff staff) {
        (this.list).add(staff);
    }

    /**
     * Removes a Staff from the Staff arraylist of this class.
     *
     * @param IndexOfRemoval The index of the Staff to be removed from the
     * arraylist of this class
     */
    public void removeStaff(int IndexOfRemoval) {
        (this.list).remove(IndexOfRemoval);
    }

    /**
     * The following searching algorithm searches for a Staff with a specified
     * staffID and returns it. (linear search)
     *
     * Example of/Related to: SEARCHING
     *
     * @param staffID The staffID that corresponds to the Staff Member
     * @return Staff The Staff Member
     */
    public Staff findUsingID(int staffID) {
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i)).getStaffID() == staffID) {
                return list.get(i);
            }
        }

        return null;
    }

    /**
     * The following searching algorithm searches for a Staff with a specified
     * name and returns it. (linear search)
     *
     * Example of/Related to: SEARCHING
     *
     * @param fullName The full name that corresponds to the Staff Member
     * @return Staff The Staff Member
     */
    public Staff findUsingName(String fullName) {
        String name = fullName.toUpperCase();

        for (int a = 0; a < list.size(); a++) {
            if ((list.get(a)).getName().equals(name)) {
                return list.get(a);
            }
        }

        return null;
    }

    /**
     * The following method sorts the arraylist of Staff Members by alphabetical
     * order using insertion sort Insertion sort is better for larger sets of
     * elements, and because a Staff Member object is made quite often, it is
     * more efficient to use insertion sort as there will be many elements as
     * more Staff Members are made.
     *
     * The following is a VARIATION of insertion sort and instead of comparing
     * direct numerical values, the names are compares LEXICOGRAPHICALLY
     * instead. This allows for strings to be compared and if a positive value
     * is returned, string1 comes alphabetically AFTER.
     *
     * e.g. string1.compareTo(String2) > 0
     *
     * Example of/Related to: SORTING
     */
    public void sortAlphabetically() {
        int i, j;

        Staff key;

        for (j = 1; j < list.size(); j++) {
            key = list.get(j);
            i = j - 1;

            while (i >= 0) {
                if (key.getName().compareTo(list.get(i).getName()) > 0) {
                    break;
                }

                Staff temp = list.get(i + 1);
                list.set(i + 1, list.get(i));
                list.set(i, temp);
                i--;
            }
        }
    }

    /**
     * The following method sorts the arraylist of Staff Members by number of
     * Shifts using insertion sort Insertion sort is better for larger sets of
     * elements, and because a Staff Member object is made quite often, it is
     * more efficient to use insertion sort as there will be many elements as
     * more Staff Members are made.
     *
     * Example of/Related to: SORTING
     */
    public void sortNumShifts() {
        for (int i = 1; i < list.size(); i++) {
            Staff value = ((list.get(i)));

            int j = i - 1;
            while (j >= 0 && (list.get(j).getNumShifts()) > (list.get(i).getNumShifts())) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, value);
        }
    }

    /**
     * The following method compiles all instances of Employee and stores them
     * in an arraylist. This method makes it easier to sort Employees by their
     * own unique characteristics that Managers may not have (such as hourly
     * wage). This method then calls on the sortingHourlyWage method and passes
     * off the new smaller arraylist of employees to be sorted. This method then
     * prints out the details of the sorted arraylist.
     *
     * Example of/Related to: SORTING, POLYMORPHISM
     */
    public void compilingEmps() {
        ArrayList<Employee> temp = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Employee) {
                temp.add((Employee) list.get(i));
                //Casting needed to convert Staff Objects to Employee Objects to be stored in the Employee arraylist
            }
        }

        sortingHourlyWage(temp);

        for (int a = 0; a < temp.size(); a++) {
            System.out.println((temp.get(a)).toString());
        }

    }

    /**
     * The following method uses the arraylist of Employees and sorts them by
     * selection sort. We decided to use selection sort because it is the most
     * efficient for the following number of elements. Because the number of
     * Employees is less than the overall number of Staff members, selection
     * sort was used because it is more efficient when used with small sets of
     * elements.
     *
     * @param emp The ArrayList of Employees needed to be sorted
     */
    public void sortingHourlyWage(ArrayList<Employee> emp) {
        int iPos;
        int iMin;

        for (iPos = 0; iPos < emp.size(); iPos++) {
            iMin = iPos;
            for (int i = iPos + 1; i < emp.size(); i++) {
                if (((emp.get(i)).getHourlyWage()) > ((emp.get(iMin)).getHourlyWage())) {
                    iMin = i;
                }
            }
            if (iMin != iPos) {
                Employee temp = emp.get(iPos);
                emp.set(iPos, emp.get(iMin));
                emp.set(iMin, (temp));
            }
        }
    }

    /**
     * The following method compiles all instances of Manager and stores them in
     * an arraylist. This method makes it easier to sort Managers by their own
     * unique characteristics that Employees may not have (such as rating). This
     * method then calls on the sortingRating method and passes off the new
     * smaller arraylist of managers to be sorted. This method then prints out
     * the details of the sorted arraylist.
     *
     * Example of/Related to: SORTING, POLYMORPHISM
     */
    public void compilingManagers() {
        ArrayList<Manager> temp = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Manager) {
                temp.add((Manager) list.get(i));
                //Casting needed to convert Staff Objects to Manager Objects to be stored in the Manager arraylist
            }
        }

        sortingRating(temp);

        for (int a = 0; a < temp.size(); a++) {
            System.out.println((temp.get(a)).toString());
        }

    }

    /**
     * The following method uses the arraylist of Managers and sorts them by
     * selection sort. We decided to use selection sort because it is the most
     * efficient for the following number of elements. Because the number of
     * Managers is less than the overall number of Staff members, selection sort
     * was used because it is more efficient when used with small sets of
     * elements.
     *
     * @param mng The arraylist of managers to be sorted
     */
    public void sortingRating(ArrayList<Manager> mng) {
        int iPos;
        int iMin;

        for (iPos = 0; iPos < mng.size(); iPos++) {
            iMin = iPos;
            for (int i = iPos + 1; i < mng.size(); i++) {
                if (((mng.get(i)).getRating()) > ((mng.get(iMin)).getRating())) {
                    iMin = i;
                }
            }
            if (iMin != iPos) {
                Manager temp = mng.get(iPos);
                mng.set(iPos, mng.get(iMin));
                mng.set(iMin, (temp));
            }
        }
    }

    /**
     * The following method adds the hourly wages of each employee and returns
     * average hourly wage.
     *
     * @return double The average hourly wage of employees
     */
    public double averageHourlyWage() {
        double pay = 0;
        int numEmployees = 0;

        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i)) instanceof Employee) {
                pay += ((Employee) (list.get(i))).getHourlyWage();
                //Casting needed to convert Staff Objects to Employee Objects to use method .getHourlyWage()

                numEmployees++;
            }
        }

        return pay / numEmployees;
    }
}
