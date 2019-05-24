package pages;

import Interfaces.Page;
import util.User;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Login implements Page {

    private static final String totallyUnsafeUsername = "admin";
	private static final String totallyUnsafePassword = "admin";


    private BufferedReader bufferedReader;
    private ArrayList<User> userData;

    private static boolean cleared;



    public Login (BufferedReader bufferedReader, ArrayList<User> userData) {
        cleared = false;
        this.bufferedReader = bufferedReader;
        this.userData = userData;
    }

	/* Login loop here */
	public Boolean call () {

	    String username;
	    String password;

	    try {

            while (!cleared) {
                System.out.println("Please enter username: ");
                if ((username = bufferedReader.readLine()) != null);
                System.out.println("Please enter password: ");
                password = bufferedReader.readLine();

                for (User u : userData) {
                    if (u.verifyDetails(username, password)) {//username.equals(totallyUnsafeUsername) && password.equals(totallyUnsafePassword)) {
                        System.out.println("Success!");
                        cleared = true;
                        break;
                    } else {
                        System.out.println("Username or password entered was incorrect!");
                    }
                }
            }

        } catch (IOException e) {
	        e.printStackTrace();
        }

        return true;
    }



    public static void logout () {

	    cleared = false;

	    System.out.println("You are now logged out!");
        Toolkit.getDefaultToolkit().beep();
    }
	
}
