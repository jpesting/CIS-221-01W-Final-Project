/*
Name: Jonathan Pestinger
Class Number: CIS221
Time: Tuesdays and Thursdays at 2:10pm
Period: Spring 2022
*/

import java.io.File; // Used to locate login info from 'credentials.txt'
import java.io.FileWriter; // Used to add new login information to the file
import java.io.IOException; // Throw an error if the file cannot be found, if there are permission issues, or if the file is corrupt

import java.util.Scanner; // Used to read the file that contains user login information
import java.util.HashMap; // Used to store user login info as key/value pairings
import java.awt.Color;
import java.awt.event.ActionEvent; // Used to run an event upon a user's action
import java.awt.event.ActionListener; // Used to identify user user's action

import javax.swing.JButton; // Used to create a button that the user will interact with
import javax.swing.JFrame; // Used to create a frame for the GUI
import javax.swing.JLabel; // Used to create labels for elements within the frame
import javax.swing.JOptionPane; // Used to output a valid or invalid login attempt to the user
import javax.swing.JPanel; // Used to create a window within the frame that will hold all of the elements
import javax.swing.JPasswordField; // Used to create a field to enter in the user's password
import javax.swing.JTextField; // Used to create a field that will display text


public class Main implements ActionListener { // Create a the Main class that will implement ActionListener which is needed to create a new Main object for the button's ActionListener

    // Creation of private variables for security
    // Use of static to allow every instance of the Main class to reference these variables
    // Uses for each individual object is seen in the import statements
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;

    public static void main(String[] args) throws IOException { // Throw an error if the file cannot be found, if there are permission issues, or if the file is corrupt

        // JFrame will be the window
        // JPanel will be the layout of the elements inside the window

        JFrame frame = new JFrame(); // Create a new object from the JFrame class and name it "frame"
        JPanel panel = new JPanel(); // Create a new object of the JPanel class and name it "panel"

        frame.setTitle("Login Page");
        frame.setLocation(750, 300);
        frame.setSize(400, 200); // Create dimensions for JFrame frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allow the frame and program to be closed on command
        frame.add(panel); // Add the panel to the frame - Will cover the entire width and height of the screen
        panel.setLayout(null); // Create the layout of the panel

        userLabel = new JLabel("Username"); // This label will be used to identify the user
        userLabel.setBounds(100, 5, 70, 20); // Set the dimensions of the label
        panel.add(userLabel); // Add the label to the frame

        userText = new JTextField(); // This text field will be used to enter the user's ID
        userText.setBounds(100, 25, 195, 25); // Set the dimensions of the text field
        panel.add(userText); // Add the Text box to the panel

        passwordLabel = new JLabel("Password"); // This label will be used to identify the user's password
        passwordLabel.setBounds(100, 55, 70, 20); // Set the dimensions of the label
        panel.add(passwordLabel); // Add the label to the panel

        passwordText = new JPasswordField(); // This password field will be used to enter the user's password
        passwordText.setBounds(100, 75, 195, 25); // Set the dimensions of the password field
        panel.add(passwordText); // Add the password field to the panel

        button = new JButton("Login"); // This button will be used to submit the information
        button.setBounds(100, 110, 90, 25); // Set the dimensions of the button
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.addActionListener(new Main()); // Add action to the button using an ActionListener - Pass through a new object for the GUI
                                              // Must take in a parameter of ActionListener - which is also an object
                                              // Main() is not an ActionListener object - Implement ActionListener to the Main class
        panel.add(button); // Add the button to the panel

        success = new JLabel(""); // This labrl will be used to show the user if they entered correct or incorrect login information
        success.setBounds(10, 110, 300, 25); // Set the dimensions for the label
        panel.add(success); // Add the label to the panel

        frame.setVisible(true); // Allow the frame to be visible
        
    }

    @Override
    public void actionPerformed(ActionEvent a) { // Events for the ActionListener button will run here

        // try { // Run this portion of the program if 'credentials.txt' can be located
        //     String user = userText.getText(); // Get the value of the user's input within the username text box
        //     String password = passwordText.getText(); // Get the value of the user's password within the password text box
        //     HashMap<String, String> info = new HashMap<String, String>(); // Create a hashmap to store key/value pairings of the login info for previous users
        //     File file = new File("finalproject/credentials.txt"); // Create the file to store the login info for the users
        //     Scanner scan = new Scanner(file); // Use the scanner to read into the file that was created
        //     String line = ""; // Used to take pull the data from the text file and turn it into usable data

        //     while (scan.hasNextLine()) { // Populate the hashmap - Will continue running until all lines in the text file have been read
        //         line = scan.nextLine(); // Pull the data from one line of the text file
        //         String [] credentials = line.split(","); // Remove the comma from the line pulled
        //         info.put(credentials[0], credentials[1]); // Store the first string as a key, and the second string as a value within the hashmap
        //         // The process will repeat until all lines have been read by the scanner
        //     }

        //     if (info.containsKey(user)) { // Match to see if the user entered in a correct username
        //         if (password.equals(info.get(user))) { // If the user entered in a correct username, check to see if their password matches the value stored with the key that they successfully matched with
        //             System.out.println("Login Successfull");
        //             JOptionPane.showMessageDialog(null, "Login Successfull", "Success", JOptionPane.INFORMATION_MESSAGE); // Output a successfull login
        //         } else { // If the user enters a correct username but an incorrect password, output a failed login attempt
        //             System.out.println("Invalid username or password");
        //             userText.setText(""); // Clear the fields upon an invalid login
        //             passwordText.setText("");
        //             JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        //         }

        //     } else { // If the user entered in an invalid username or password, output a failed login attempt
        //         System.out.println("Invalid username or password");
        //         userText.setText(""); // Clear the fields upon an invalid login
        //         passwordText.setText("");
        //         JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        //     }

        try {
            String user = userText.getText();
            String password = passwordText.getText();
            HashMap<String, String> info = new HashMap<String, String>();
            File file = new File("finalproject/credentials.txt");
            Scanner scan = new Scanner(file);
            String[] credentials;
            String line;

            while (scan.hasNextLine()) {
                line = scan.nextLine();
                credentials = line.split(",");
                info.put(credentials[0], credentials[1]);
                System.out.println(info);
            }

            if ((info.containsKey(user)) && (password.equals(info.get(user)))) {
                System.out.println("Login Successfull");
                JOptionPane.showMessageDialog(null, "Login Successfull", "Success", JOptionPane.INFORMATION_MESSAGE); // Output a successfull login
            } else {
                System.out.println("Invalid username or password");
                userText.setText(""); // Clear the fields upon an invalid login
                passwordText.setText("");
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }

            scan.close(); // Close the file scanner

        } catch (IOException e) { // If 'credentials.txt' cannot be found, throw an error and do not run this portion of the program
            JOptionPane.showMessageDialog(null, "User Credentials Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } // Closes actionPerformed method

}