import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Interfaces.Page;
import pages.Login;
import pages.MainMenu;
import pages.MovieBooking;
import pages.MovieSearch;
import util.*;

public class Main {

    private static final int MOVIEDATA = 0;
    private static final int USERDATA = 1;
    private static final int BOOKDATA = 2;


	private static ArrayList<Movie> movieData;
	private static ArrayList<User> userData;
	private static ArrayList<Booking> bookingData;


	private static boolean loggedIn = false;


	private static InputStreamReader inputStreamReader;
	private static BufferedReader bufferedReader;


	private static Page loginPage;
	private static Page mainMenu;
	private static Page movieSearch;
	private static Page movieBooking;
	private static Page[] pages;
    //private static List<Page> Pages;

    static volatile String input = "";


	public static void main (String args[]) throws IOException{

        init();

		/* Main loop of program here */

        do {

            displayMenu();
            int choice = Integer.parseInt(bufferedReader.readLine());

            switch (choice) {
                case 1:
                    if (!loggedIn) {
                        loggedIn = (boolean) loginPage.call();

                        loggedIn = (boolean) mainMenu.call();
                    }
                    break;
                case 2:

                    finish();

                    System.exit(0);
                default:
                    System.out.println("That is not a valid option");
            }

            System.out.println("System is now logged out!");


        } while (true);

        //System.out.println("System will now shutdown");
	}

	private static void finish () {
        DataWriter dataWriter = DataWriter.getDataWriter();

        dataWriter.setData(movieData, bookingData, userData);

        dataWriter.writeData(0);
        dataWriter.writeData(1);
        dataWriter.writeData(2);
    }

    /**
     * Initializes the program
     */
	private static void init () {

	    DataReader dataReader = DataReader.getDataReader();


	    movieData = new ArrayList<>();
	    userData = new ArrayList<>();
	    bookingData = new ArrayList<>();
	    for (Object o : dataReader.getData(MOVIEDATA)) {
	        movieData.add((Movie) o);
        }
        for (Object o : dataReader.getData(USERDATA)) {
	        userData.add((User) o);
        }

        dataReader.setMovieData(movieData);

        for (Object o : dataReader.getData(BOOKDATA)) {
	        bookingData.add((Booking) o);
        }


	    inputStreamReader = new InputStreamReader(System.in);
	    bufferedReader = new BufferedReader(inputStreamReader);


        loginPage = new Login(bufferedReader, userData);
        mainMenu = new MainMenu(bufferedReader, movieData);
        movieSearch = new MovieSearch(bufferedReader, movieData, bookingData);
        movieBooking = new MovieBooking(bufferedReader, movieData, bookingData);

        pages = new Page[4];

        pages[0] = loginPage;
        pages[1] = mainMenu;
        pages[2] = movieSearch;
        pages[3] = movieBooking;

        ((MainMenu)mainMenu).setPages(pages);

    }

    private static void displayMenu() {
	    System.out.println("What would you like to do?");
	    System.out.println("1. Login");
	    System.out.println("2. Exit");
    }
}
