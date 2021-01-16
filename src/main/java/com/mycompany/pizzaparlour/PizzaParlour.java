
package com.mycompany.pizzaparlour;


import java.awt.Color;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * PizzaParlour
 *
 * Date: 17/01/2020 
 * School: Northview Heights Secondary School
 *
 * The following class contains the main class and handles all of the GUI. It
 * contains many instances of supporting classes in order to implement a pizza
 * shop. This class contains the main class. Provides a login, saving, loading,
 * and manipulation of user information (of both customers and staff) and
 * transactions of the pizza shop.
 *
 * TO LOG IN AS A MANAGER TO TEST THE PROGRAM, PLEASE USE: Staff ID: 1000
 * Password: goosey
 *
 * TO LOG IN AS AN EMPLOYEE TO TEST THE PROGRAM, PLEASE USE: Staff ID: 4000
 * Password: banana
 *
 * TO LOG IN AS A CUSTOMER TO TEST THE PROGRAM, PLEASE USE: Customer Name: harry
 * potter (Or Create your own by simply typing something random in the text
 * field of customer login)
 *
 *
 * @author Nathan Tang and Hannah Hepburn
 * @version 1.0
 */

public class PizzaParlour implements Serializable {
    /* 
     implementing serializable allows objects in this class to be converted into a byte stream and 
     loaded onto a file (.dat file) that is saved everytime the program is finished running and loaded
     everytime the program begins to run. 
    
     Example of/Related to: FILE INPUT/OUTPUT
     */

    private static String custName, clname, cfname;
    //ENCAPSULATION is used. The following fields are private and are only used by the following methods within this class. 

    public static void main(String[] args) {
        boolean logout = false;
        String newPass, oldPass;

        CustomerList customer = new CustomerList();
        customer.addCust(new Customer("HARRY", "POTTER"));
        customer.saveCust();

        StaffList staff = new StaffList();
        staff.addStaff(new Manager("LUCY","GOOSEY","goosey", 1000, 3));
         staff.saveStaff();
        
         staff.addStaff(new Employee("HANNAH","BANANA","banana", 4000, 3, 14));
         staff.saveStaff();
        
         staff.addStaff(new Employee("NATE","LATE","late", 2000, 6, 18));
         staff.saveStaff();
        
         staff.addStaff(new Employee("SAM","BAM","bam", 3000, 5, 19));
         staff.saveStaff();
        
         //System.exit(0);
         

        TransactionList tran = new TransactionList();
        tran.addTransaction(new Transaction (15));
        tran.saveTransaction();

        staff.loadStaff();
        customer.loadCust();
        tran.loadTransaction();

        /*
         Instantiating CustomerList, StaffList, and TransactionList and loading the arraylist in each
         byte file to the arraylist within each class. This instance will then be manipulated.
        
         Example of/Related to: FILE INPUT/OUTPUT
         */
        Staff loggedIn;
        Customer logged;
        //the following objects are used to keep track of the person logged in and is used to manipulate their information

        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);

        DecimalFormat df = new DecimalFormat("0.00");

        do {
            /* 
             This do while loop is used to keep cycling the logout process. Once a person logs out of their selection menu,
             the boolean variable logout is set to true and the login process starts again.
             */

            String result = popUp();
            //calls on the popUp() method to create a small menu

            if (result.equals("Customer")) {
                //if the "Customer" login option was selected from the dropdown, run the following cutomer menu options

                int custSelect;
                // variable that records customer selection

                boolean cont = true;
                //Used to constantly repreat the menu until the customer decides to logout (turns false)

                logged = custLogin(customer);
                //prompts the custLogin method to log a customer in

                System.out.println();
                System.out.printf("%-40s%50s\n", ("Name: " + logged.getName()), ("Number of Pizzas until FREE: " + logged.getUntilFree()));
                System.out.println("Welcome back " + logged.getName() + "!");
                System.out.println();
                //Prints customer details

                do {
                    /*
                     The following do-while loop is used to repeat the customer selection menu and the prompt for the selection.
                     It is broken when the Logout selection is made (in which cont is false).
                     */

                    System.out.println("1) Order Pizza!");
                    System.out.println("2) Logout");

                    System.out.println("What would you like to do today?");
                    custSelect = scan.nextInt();
                    //get customer selection 

                    if (custSelect == 1) {
                        //if 1 is selected, run this code which orders a pizza

                    } else if (custSelect == 2) {
                        //if 2 was selected, cont is set to false to exit this loop and logout variable is set to true to restart login process

                        cont = false;
                        logout = true;
                    } else {
                        //if one of the following options was not selected, do nothing and restart the loop.

                        System.out.println("That wasn't a valid option!");
                    }

                } while (cont);

            } else if (result.equals("Staff")) {
                //if the "Staff" login option was selected from the dropdown, run the following cutomer menu options

                int managerSelect, empSelect;
                // variable that records manager OR employee selection

                boolean cont = true;
                //Used to constantly repreat the menu until the staff member decides to logout (turns false)

                loggedIn = login(staff);
                //prompts the login method to log a staff member in in

                staff.addLogin(loggedIn);
                /*
                 calls on the following method which records the login details of every Staff Member login
                
                 Example of/Related to: FILE INPUT/OUTPUT
                 */

                System.out.println();
                System.out.printf("%-20s%-20s%50s\n", ("Staff ID: " + loggedIn.getStaffID()), ("Position: " + loggedIn.checkPosition()), ("Your Name: " + loggedIn.getName()));
                System.out.println("Welcome back " + loggedIn.getName() + "!");
                System.out.println();
                //Prints staff details

                if (loggedIn instanceof Manager) {
                    //If the Staff Member logged in is a Manager, give them this menu selection.

                    do {
                        /*
                         The following do-while loop is used to repeat the Manager selecion menu and the prompt for the selection.
                         It is broken when the Logout selection is made (in which cont is false).
                         */

                        System.out.println();
                        System.out.println("1) Print Staff List By...");
                        System.out.println("2) Check Login Logs");
                        System.out.println("3) Change Password");
                        System.out.println("4) Add a Staff!");
                        System.out.println("5) Remove a Staff!");
                        System.out.println("6) Check the Average Hourly Wage");
                        System.out.println("7) Logout!");

                        System.out.println("What would you like to do today?");
                        managerSelect = scan.nextInt();
                        System.out.println();
                        //get manager selection 

                        if (managerSelect == 1) {
                            /*
                             If 1 was selected, ask the user how they want their Staff sorted. Then apply different methods
                             in StaffList class to sort Staff arraylist and print it to the screen. If a method which manipulates ALL
                             STAFF is used, write it to a text file and read it from the text file.  If a method which only manipulates
                             JUST MANAGERS or JUST EMPLOYEES, do not write to the text file and simple print the ordered members to the
                             screen using the method.
                        
                             Example of/Related to: FILE INPUT/OUTPUT, SORTING
                             */

                            System.out.println("Print By: ");
                            System.out.println("1) Alphabetically");
                            System.out.println("2) Number of Shifts");
                            System.out.println("3) (Employee) Hourly Wage");
                            System.out.println("4) (Manager) Rating");
                            int sortBy = scan.nextInt();
                            //get the selected sorting method desired by user

                            if (sortBy == 1) {
                                staff.sortAlphabetically();
                                staff.writeToStaff();
                                staff.readFromStaff();
                            } else if (sortBy == 2) {
                                staff.sortNumShifts();
                                staff.writeToStaff();
                                staff.readFromStaff();
                            } else if (sortBy == 3) {
                                staff.compilingEmps();
                            } else if (sortBy == 4) {
                                staff.compilingManagers();
                            } else {
                                System.out.println("That wasn't a valid option. Automatically returning to main menu.");
                                System.out.println();
                            }
                            // If an invalid selection was made, simply do nothing and print the selection menu again.

                        } else if (managerSelect == 2) {
                            /*
                             If 2 was selected, call the loadLogin method, which reads from the login text file and print it out.
                          
                             Example of/Related to: FILE INPUT/OUTPUT
                             */

                            staff.loadLogin();
                            System.out.println();
                        } else if (managerSelect == 3) {
                            //if 3 was selected, start the change password process

                            boolean empty;
                            //empty variable used if nothing was entered in the prompt

                            do {
                                //do-while loop used to cycle prompt if nothing was entered

                                empty = false;
                                System.out.println("Please Enter Your Current Password: ");
                                oldPass = scan2.nextLine();
                                System.out.println();

                                if (oldPass.isEmpty()) {
                                    //if nothing was entered, prompt again

                                    System.out.println("You didn't seem to enter anything, try again");
                                    empty = true;
                                }
                            } while (empty);

                            do {
                                //do-while loop used to cycle prompt if nothing was entered

                                empty = false;
                                System.out.println("Please Enter Your New Password:");
                                newPass = scan2.nextLine();

                                if (newPass.isEmpty()) {
                                    //if nothing was entered, prompt again

                                    System.out.println("You didn't seem to enter anything, try again");
                                    empty = true;
                                }
                            } while (empty);

                            loggedIn.changePassword(oldPass, newPass);
                            //call on the following method to change Manager's password usign the following user input

                        } else if (managerSelect == 4) {
                            //If 4 was selected, prompt the user for all the information required to make a new manager

                            boolean pass, notReasonable;
                            //pass variable used if staffID entered already exists or if the staffID is not a 4-digit number
                            //notReasonable variable used if the number of shifts assigned to new staff is unreasonabble

                            int staffID, numShifts;
                            //staffID variable stores the value of the new staffID
                            //numShifts variable stores the number of shifts assign to the new Staff

                            double hourlyWage;
                            //hourlyWage stores the new wage assigned if an employee is instantiated

                            System.out.println("What is their First Name?");
                            String fname = scan2.nextLine();
                            fname = fname.toUpperCase();
                            System.out.println();
                            //prompts for the first name of new Staff Member

                            System.out.println("What is their Last Name?");
                            String lname = scan2.nextLine();
                            lname = lname.toUpperCase();
                            System.out.println();
                            //prompts for the last name of new Staff Member

                            System.out.println("What is their SUPER SECRET PASSWORD?");
                            String password = scan2.nextLine();
                            System.out.println();
                            //prompts for the password of new Staff Member

                            do {
                                /* 
                                 This do while loop is used to keep cycling the staffID prompt if a Staff Member with the following
                                 staffID already exists or the staffID entered was invalid.
                                 */
                                pass = false;

                                System.out.println("Please Enter a 4-digit Staff ID:");
                                staffID = scan.nextInt();
                                System.out.println();
                                //prompts for staffID of new Staff Member

                                for (int i = 0; i < (staff.list).size(); i++) {
                                    if (staffID == ((staff.list).get(i)).getStaffID()) {
                                        System.out.println("That Staff ID already exists! Please choose another one!");
                                        System.out.println();
                                        pass = true;
                                        break;
                                    }
                                }
                                //find if the staffID exists already and prompt again if it already exists

                                if (staffID > 9999 || staffID < 1000) {
                                    System.out.println("That doesn't seem to be a 4-digit number, try again!");
                                    System.out.println();
                                    pass = true;
                                }
                                //if the staffID wasn't a 4-digit number, prompt again

                            } while (pass);

                            do {
                                /* 
                                 This do while loop is used to keep cycling the number of shifts prompt if the number of shifts entered
                                 was not reasonable
                                 */

                                notReasonable = false;

                                System.out.println("How many shifts are they able to work per week? (1 to 6)");
                                numShifts = scan.nextInt();
                                System.out.println();
                                //prompts for the number of shifts for new Staff MEMBER

                                if (!((numShifts >= 1) && (numShifts <= 6))) {
                                    notReasonable = true;
                                    System.out.println("Hmm... That doesn't seem to be between 1 and 6 shifts, Try again!");
                                }
                                //if the number of shifts entered was not between 1 and 6 (inclusive), prompt again

                            } while (notReasonable);

                            System.out.println("Will you be adding a:");
                            System.out.println("1)\tManager");
                            System.out.println("2)\tEmployee");
                            int type = scan.nextInt();
                            System.out.println();
                            //prompts for whether the user would like to crete a Manager Staff Member or an Employee Staff Member

                            if (type == 1) {
                                //if 1 was selected, create a Manager Staff Member using the following user input

                                staff.addStaff(new Manager(fname, lname, password, staffID, numShifts));
                                System.out.println("Successfully Added!!!");
                                System.out.println();
                                //use Manager constructor in Manager Class to instantiate a new Manager Staff Member
                            }

                            if (type == 2) {
                                //if 2 was selected, prompt for more information to create an Employee Staff Member

                                boolean pay;
                                //variable used if the hourlyWage being payed to the new Employee Staff Member if unreasonable

                                do {
                                    /*
                                     This do while loop is used to keep cycling the hourly wage prompt if the hourly wage entered was unreasonable.
                                     */

                                    pay = false;

                                    System.out.println("Sorry, One last question, How much are you paying this person?");
                                    hourlyWage = scan.nextDouble();
                                    System.out.println();
                                    //prompt for the hourlyWage for the new Employee Staff Member

                                    if (hourlyWage >= 0 && hourlyWage < 14) {
                                        //if too high o was assigned, prompt again

                                        System.out.println("That is way too little! Please pay this person reasonably!");
                                        System.out.println();
                                        pay = true;
                                    } else if (hourlyWage >= 14 && hourlyWage <= 20) {
                                        // if a reasonable hourly wage between $14 and $20 (inclusive) was assigned, prompt again

                                        System.out.println("That seems reasonable :).");
                                        System.out.println();
                                    } else {
                                        // if too high of an hourly wage was assigned, prompt again

                                        System.out.println("That is too much! Please pay this person reasonably.");
                                        System.out.println();
                                        pay = true;
                                    }
                                } while (pay);

                                staff.addStaff(new Employee(fname, lname, password, staffID, numShifts, hourlyWage));
                                //use Employee constructor to instantiate a new Employee Staff Member

                                System.out.println("Successfully Added!!!");
                                System.out.println();
                            }

                        } else if (managerSelect == 5) {
                            // if 5 was selected, remove a staff member

                            System.out.println("What is the Staff ID of the Staff Member?");
                            int tempID = scan.nextInt();
                            //prompt for the Staff ID of the staff member being removed

                            Staff select = staff.findUsingID(tempID);
                            /*
                             Find the following staff member by using the searching method in the StaffList class
                             and the staff ID inputted.
                             */

                            if (select == null) {
                                //if the select object is null, meaning that the method did not find a Staff Member with the following Staff ID,
                                //do nothing and return to the main menu

                                System.out.println("Sorry, we couldn't find a Staff Member with that ID. Try again later!");
                                System.out.println();
                            } else {
                                //if a Staff Member was found with the following staffID, ask for their password and remove them.

                                System.out.println("You're gonna need that person to type their password.");
                                String tempPass = scan2.nextLine();
                                //prompt for the password of the Staff Member

                                if (tempPass.equals(select.getPassword())) {

                                    int indexOfRemoval = 0;
                                    for (int i = 0; i < (staff.list).size(); i++) {
                                        if (select.getStaffID() == ((staff.list).get(i).getStaffID())) {
                                            indexOfRemoval = i;
                                        }
                                    }

                                    staff.removeStaff(indexOfRemoval);
                                    staff.saveStaff();
                                    System.out.println("Success!!!");
                                } else {
                                    //if the password did not match the password of the selected Staff Member, do nothing and return to the main selection menu.

                                    System.out.println("Sorry the password didn't match! Try again later.");
                                    System.out.println();
                                }
                            }

                        } else if (managerSelect == 6) {
                            //if 6 was selected, print the average hourly wage using a method in the StaffList class

                            System.out.println("The average Hourly Wage is: $" + df.format(staff.averageHourlyWage()));
                        } else if (managerSelect == 7) {
                            //if 7 was selected, repeat the entire login process and discontinue looping the menu.

                            cont = false;
                            logout = true;
                            System.out.println("Goodbye!!");
                        } else {
                            //If an invalid selection was made, simply do nothing and loop to the selection menu again.

                            System.out.println("That wasn't a valid option. Please try again!");
                            System.out.println();
                        }

                    } while (cont);
                } else if (loggedIn instanceof Employee) {
                    //If the Staff Member logged in is a Employee, give them this menu selection.

                    do {
                        /*
                         The following do-while loop is used to repeat the employee selecion menu and the prompt for the selection.
                         It is broken when the Logout selection is made (in which cont is false).
                         */

                        System.out.println();
                        System.out.println("1) Print Transactions By...");
                        System.out.println("2) Rate a Manager ANONYMOUSLY!");
                        System.out.println("3) Change Password");
                        System.out.println("4) Logout");

                        System.out.println("What would you like to do today?");
                        empSelect = scan.nextInt();
                        System.out.println();
                        //get employee selection

                        if (empSelect == 1) {
                            /*
                             If 1 was selected, ask the user how they want their transactions sorted. Then apply different methods
                             in StaffList class to sort arraylist and print it to the screen. 
                             Write it to a text file and read it from the text file.
                        
                             Example of/Related to: FILE INPUT/OUTPUT, SORTING
                             */

                            System.out.println("Print By: ");
                            System.out.println("1) Date");
                            System.out.println("2) Price");
                            int sortBy = scan.nextInt();
                            System.out.println();

                            if (sortBy == 1) {
                                tran.sortDate();
                                tran.writeToTransaction();
                                tran.readFromTransaction();
                            } else if (sortBy == 2) {
                                tran.sortPrice();
                                tran.writeToTransaction();
                                tran.readFromTransaction();
                            } else {
                                System.out.println("Sorry that wasn't a valid option. Automatically returning to main menu.");
                                System.out.println();
                            }
                            // If an invalid selection was made, simply do nothing and print the selection menu again.

                        } else if (empSelect == 2) {
                            //if 2 is selected, give the user the opportunity to rate a Manager of their choice usign their default 3 ratings.

                            boolean empGiven;
                            //Used to indicate whether an employee Staff ID was given.

                            Manager victim = null;
                            //The victim (or Staff Member) being rated

                            int ratingsLeft = ((Employee) loggedIn).getRatingsLeft();
                                //indicates whether an employee has reached 0 ratings left

                            if (ratingsLeft > 0) {
                            //if the employee has greater than 0 ratings, prompt for Manager they want to rate.
                                
                                do {
                                    /*
                                     The following do while loop prompts the user until an employee staffID is NOT GIVEN.   
                                     */

                                    empGiven = false;

                                    System.out.println("Please enter the Staff ID of the Manager you would like to Rate:");
                                    int tempNum = scan.nextInt();
                                    //prompt for the Staff ID to find the Manager

                                    try {
                                        for (int i = 0; i < (staff.list).size(); i++) {
                                            if (tempNum == ((staff.list).get(i).getStaffID())) {
                                                victim = (Manager) ((staff.list).get(i));
                                                /*
                                                 cast is used to assign victim to a Manager Object from the Staff arraylist
                                            
                                                 Example of/Related to: POLYMORPHISM
                                                 */

                                                break;
                                            }
                                        }
                                    } catch (ClassCastException e) {
                                        System.out.println("The Staff ID you typed wasn't a Manager! Please try again!");
                                        empGiven = true;
                                    }
                                    /*
                                     if a ClassCastException error isissued, an employee was found using the staffID and cannot be casted to Manager
                                     this results in a "catch" which instead, sets empGiven to true and loops through the prompt again unttil the user enters
                                     something that doesn't refer to an Employee Object.
                                     */

                                } while (empGiven);

                                if (victim == null) {
                                    //if the victim is null, meaning no Staff Member was found, do nothing and return to the main selection menu.

                                    System.out.println("There isn't a manager with that Staff ID. Try again later...");
                                    System.out.println();
                                } else {
                                    //If a manager was found using the searching method, allow the user to rate the manager.

                                    boolean notBetween;
                                    //used to indicate whether the value inputted is between 0 - 10 (inclusive)

                                    do {
                                        /*
                                         The following do while loop is used to keep prompting the user until a valid     
                                         */
                                        notBetween = false;
                                        System.out.println("Please enter a rating between 0.00 and 10.00");
                                        double rating = scan2.nextDouble();

                                        if (!(rating >= 0.00 && rating <= 10.00)) {
                                            //if the following rating was not between 0 and 10 (inclusive), prompt again for rating

                                            notBetween = true;
                                            System.out.println("That doesn't seem to be between 0.0 and 10.0");
                                            System.out.println();
                                        } else {
                                            //if the rating was between 0 and 10 (inclusive), process the rating

                                            victim.setRating(rating);
                                            //add the rating to the Manager's ratings using a method that belongs to Manager class

                                            ((Employee) loggedIn).subtractRatings();
                                            /*
                                             Casting is used in order to use the .subtractingRatings() method that is exclusive to Employee objects only
                                        
                                             Example of/Related to: POLYMORPHISM
                                             */

                                            System.out.println("Successful! You have " + ((Employee) loggedIn).getRatingsLeft() + " ratings left!");
                                            /*
                                             Casting is used in order to use the .getRatingsLeft() method that is exclusive to Employee objects only
                                        
                                             Example of/Related to: POLYMORPHISM
                                             */
                                        }
                                    } while (notBetween);
                                }
                            } else {
                              //if all ratings are used up, do nothing, and display main selection menu again.
                                
                                System.out.println("You've already used all your ratings up! Sorry!");
                                System.out.println();
                            }
                        } else if (empSelect == 3) {
                            //if 3 was selected, start the change password process

                            boolean empty;
                            //empty variable used if nothing was entered in the prompt

                            do {
                                //do-while loop used to cycle prompt if nothing was entered

                                empty = false;
                                System.out.println("Please Enter Your Current Password: ");
                                oldPass = scan2.nextLine();
                                System.out.println();

                                if (oldPass.isEmpty()) {
                                    //if nothing was entered, prompt again

                                    System.out.println("You didn't seem to enter anything, try again");
                                    empty = true;
                                }
                            } while (empty);

                            do {
                                //do-while loop used to cycle prompt if nothing was entered

                                empty = false;
                                System.out.println("Please Enter Your New Password:");
                                newPass = scan2.nextLine();

                                if (newPass.isEmpty()) {
                                    //if nothing was entered, prompt again
                                    System.out.println("You didn't seem to enter anything, try again");
                                    empty = true;
                                }
                            } while (empty);

                            loggedIn.changePassword(oldPass, newPass);
                            //call on the following method to change Employee's password using the following user input

                        } else if (empSelect == 4) {
                            //if 4 was selected, repeat the entire login process and discontinue looping the menu.

                            cont = false;
                            logout = true;
                            System.out.println("GoodBye!");
                        } else {
                            //If an invalid selection was made, simply do nothing and loop to the selection menu again.

                            System.out.println("That wasn't a valid option. Please try again!");
                        }
                    } while (cont);

                }
            } else {
                /*
                 if "EXIT" was selected, safely end the program after saving all arraylists to a byte file
                
                 Example of/Related to: FILE INPUT/OUTPUT
                 */

                staff.saveStaff();
                customer.saveCust();
                tran.loadTransaction();
                System.exit(0);
            }

        } while (logout);

    }

    /**
     * The following method implements the Staff member GUI. It is used to log a
     * Staff Member in and access the Manager OR Employee menu.
     *
     * @param staff Takes the staff arraylist and uses it to find staff login
     * details
     * @return Staff returns the Staff that has successfully logged in
     */
    public static Staff login(StaffList staff) {
        String password = null;
        //password variable stores the password the user entered. 

        boolean other = false;
        //other variable is used to indicate whether a name or a staffID was entered in the text field

        String name = null;
        int identity = 0;
        //name variable is used to store the name of the user if they tried logging in with a name while the identity variable stores the staffID of the user if they tried loggin in with a staffID

        JTextField staffIDBox = new JTextField(10);
        JPasswordField pinBox = new JPasswordField(10);
        pinBox.setEchoChar('*');
        //creates a password field that masks the letters typed in the field with asteriks (for security purposes)

        Staff loggedIn;
        //Used to store the Staff Member trying to log in if any

        JPanel panel = new JPanel();

        panel.setBackground(new Color(217, 56, 56));
        panel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(237, 150, 78)));

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("<html><font face=\"Verdana, Geneva, sans-serif\"><pre>    🍕 WELCOME TO THE PIZZA PARLOUR! Staff Login 🍕</font></pre><html>"));

        panel.add(new JLabel("<html><pre>                                     ._<br>"
                + "                                   ,(  `-.<br>"
                + "                                 ,': `.   `.<br>"
                + "                               ,` *   `-.   <br>"
                + "                             ,'  ` :+  = `.  `.<br>"
                + "                           ,~  (o):  .,   `.  `.<br>"
                + "                         ,'  ; :   ,(__) x;`.  ;<br>"
                + "                       ,'  :'  hannah ;  ; ; _,-'<br>"
                + "                     .'O ;= and ; o ;'_,_ ;<br>"
                + "                   ,;  nathan ` : ;'_,-'   i'<br>"
                + "                 ,` `;(_)  0 ; ','';     :<br>"
                + "               .';6     ; ' ,-'~<br>"
                + "             ,' Q  ,& ;',-.'<br>"
                + "           ,( :` ; _,-'~  ;<br>"
                + "         ,~.`c _','<br>"
                + "       .';^_,-' ~<br>"
                + "     ,'_;-''<br>"
                + "    ,,~<br>"
                + "    i'<br>"
                + "    :<br><br><br></pre> </html>"));

        panel.add(new JLabel("<html> <font color='orange'> Staff ID or Name (FirstName LastName): </font></html>"));
        panel.add(staffIDBox);

        panel.add(new JLabel("<html> <font color='orange'> Password: </font></html>"));
        panel.add(pinBox);

        int indicator = JOptionPane.showConfirmDialog(null, panel, "Welcome to the Pizza Parlour: ", JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION);
        //The following code above creates a popup and text fields for user input

        if (indicator == JOptionPane.OK_OPTION && !("".equals(staffIDBox.getText()) || "".equals(pinBox.getText()))) {
            try {
                identity = Integer.parseInt(staffIDBox.getText());

            } catch (NumberFormatException e) {
                other = true;
                name = staffIDBox.getText();
            }
            /*
             Try and see if the user entered an Integer or not. If they entered an integer, the Integer.parseInt should
             be able to convert the String into an integer without issuing a NumberFormatException error. However, if
             the NumberFormatException error was issued, the String entered must have been a name and not an integer. Assign the
             string to name variable instead.
             */

            password = pinBox.getText();
            //get the password typed in and put it in the password variable

        }
        /*
         If the OK button was clicked and the field is not empty, store the name in the box to custName
         execute the following code which assigns the user input to variables.
         */

        if (indicator == -1) {
            System.out.println("Goodbye!");
            staff.saveStaff();
            System.exit(0);
        }
        //If the X button was closed on the window, safely save and exit the program

        if (other) {
            loggedIn = staff.findUsingName(name);
        } else {
            loggedIn = staff.findUsingID(identity);
        }
        /*
         If a name was given (in which other is true), call on the method that goes through the arraylist and 
         search for the Staff Member using a name. Otherwise, go through the arraylist and search for the Staff 
         Member using a staffID. The following methods are in the StaffList class. They return null if no such 
         Staff Member with that name or identity is found.
        
         Example of/Related to: SEARCHING
         */

        if (loggedIn == null) {
            /*
             If the Staff Member was not found using the following user input, call on the login method again to 
             restart the Staff login process.
        
             Example of/Related to: RECURSION  
             */

            System.out.println("Sorry, we couldn't find a staff ID that matched that one. Try Again!");
            loggedIn = login(staff);
        } else {
            if (password.equals(loggedIn.getPassword())) {
                //If the password entered matches the password assigned to the Staff memer, allow entry but returning the staff member

                return loggedIn;
            } else {
                //If the password does not match, call on the login method again to restart the Staff login process.

                System.out.println("Sorry, The safe pin was incorrect for this ID!");
                loggedIn = login(staff);
            }
        }
        //If a Staff member was found, in which case loggedIn is an instance of Staff instead of null, compare passwords

        return loggedIn;
    }

    /**
     * The following method implements the Customer GUI. It is used to log a
     * customer in and access the customer menu.
     *
     * @param customer Takes the customer arraylist and uses it to find customer
     * login details
     * @return Customer returns the Customer that has successfully logged in
     */
    public static Customer custLogin(CustomerList customer) {
        Scanner scan = new Scanner(System.in);
        JTextField nameBox = new JTextField(10);

        Customer logged;
        //Used to store the Customer trying to log in if any

        JPanel panel = new JPanel();

        panel.setBackground(new Color(227, 133, 66));
        panel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(125, 206, 130)));

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("<html><font face=\"Verdana, Geneva, sans-serif\"><pre>  🍕 WELCOME TO THE PIZZA PARLOUR! Customer Login 🍕</font></pre><html>"));
        panel.add(new JLabel("<html><pre>                                                \n<br>"
                + "                        88                                 <br>"
                + "                        \"\"                                 <br>"
                + "                                                           <br>"
                + "            8b,dPPYba,  88 888888888 888888888 ,adPPYYba,  <br>"
                + "            88P'    \"8a 88      a8P\"      a8P\" \"\"     `Y8  <br>"
                + "            88       d8 88   ,d8P'     ,d8P'   ,adPPPPP88  <br>"
                + "            88b,   ,a8\" 88 ,d8\"      ,d8\"      88,    ,88  <br>"
                + "            88`YbbdP\"'  88 888888888 888888888 `\"8bbdP\"Y8  <br>"
                + "            88                                             <br>"
                + "            88                                             <br><br><br></pre></html>"));

        panel.add(new JLabel("<html> <font color='black'> Customer Name (FirstName LastName): </font></html>"));
        panel.add(nameBox);

        int indicator = JOptionPane.showConfirmDialog(null, panel, "Welcome to the Pizza Parlour: ", JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION);
        //The following code above creates a popup and text fields for user input

        if (indicator == JOptionPane.OK_OPTION && !("".equals(nameBox.getText()))) {
            custName = nameBox.getText();
            custName = custName.toUpperCase();
        } else if (indicator == JOptionPane.OK_OPTION && ("".equals(nameBox.getText()))) {
            custLogin(customer);
        }
        /*
         If the OK button was clicked and the field is not empty, store the name in the box to custName
         If the OK button was clicked and the field was empty, call the customer login method again.
        
         Example of/Related to: RECURSION
         */

        if (indicator == -1) {
            System.out.println("Goodbye!");
            customer.saveCust();
            System.exit(0);
        }
        //If the X button was closed on the window, safely save and exit the program

        logged = customer.findUsingName(custName);
        /*
         Find the customer using their name previously stored. Use the searching method in the 
         Customer class. Returns null if Customer without the following name is not found.
        
         Example of/Related to: SEARCHING
         */

        if (logged == null) {
            boolean invalid = true;

            do {
                //loops if the variable invalid is true (when a "Y" or a "N" is not entered).
                System.out.println("Sorry We couldn't find a name that matched that one! Would you like to make a new account? (Y or N)");
                String answer = scan.nextLine();
                answer = answer.toUpperCase();

                if (answer.equals("Y")) {
                    newCust();
                    //pops up a tiny window which asks for the first and last name of the person

                    customer.addCust(new Customer(cfname, clname));
                    //adds the new customer

                    invalid = false;
                }
                if (answer.equals("N")) {
                    invalid = false;
                } else if (!answer.equals("N")) {
                    System.out.println("Sorry That wasn't a valid entry. Please try again!");
                }
                //used if the user does not enter "Y" or "N".

            } while (invalid);

            logged = custLogin(customer);
        }
        /*
         If no such customer with the following name exists, ask if the user wants to create a new Customer. If yes, 
         ask for a name and add it to the arraylist and the call on the Customer login method again. If no, simply go
         straight to calling the Customer login method again.
        
         Example of/Related to: RECURSION
         */

        return logged;
    }

    /**
     * The following method creates a small popup dialog which asks the user
     * whether they are logging in as a customer or staff member or exiting.
     * This popup is repeatedly.
     *
     * @return String This returns the user selection of login (staff or
     * customer) or exit
     */
    public static String popUp() {
        String[] choices = {"Customer", "Staff", "EXIT"};
        //An array is used to store the drop down menu choices

        String input = (String) JOptionPane.showInputDialog(null, "Are you a...",
                "Login", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
        //creates a dialog with a drop down menu and assigns the selected choice to the string

        return input;
    }

    /**
     * The following creates a tiny popup that is used to ask new customers for
     * their first and last name
     *
     */
    public static void newCust() {
        JTextField fname = new JTextField(10);
        JTextField lname = new JTextField(10);

        JPanel panel = new JPanel();

        panel.setBackground(new Color(227, 133, 66));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(new JLabel("<html> <font color='black'>First Name: </font></html>"));
        panel.add(fname);

        panel.add(new JLabel("<html> <font color='black'>Last Name: </font></html>"));
        panel.add(lname);

        int indicator = JOptionPane.showConfirmDialog(null, panel, "New Customer!", JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION);

        if (indicator == JOptionPane.OK_OPTION && !("".equals(fname.getText()) || "".equals(lname.getText()))) {
            cfname = (fname.getText()).toUpperCase();
            clname = (lname.getText()).toUpperCase();
        }
        //If the button was pushed and the text fields aren't empty, assign the first and last name of the customer to variables
    }
}
