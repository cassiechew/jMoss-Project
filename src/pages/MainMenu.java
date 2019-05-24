package pages;

import Interfaces.Page;
import util.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu implements Page{

    /* Exit codes */
    private static final int USEREXIT = 0;

    /* The movie data */
    private ArrayList<Movie> movieData;

    private BufferedReader bufferedReader;

    private Page[] pages;

    public MainMenu (BufferedReader bufferedReader, ArrayList<Movie> movieData) {
        this.movieData = movieData;
        this.bufferedReader = bufferedReader;
    }

    public void setPages(Page[] pages) {
        this.pages = pages;
    }

    @Override
    public Boolean call() {

        String choice;

        displayMenu();
        try {
            while ((choice = bufferedReader.readLine()) != null) {

                /* Menu choices */
                switch (Integer.parseInt(choice)) {
                    case 1:
                        pages[2].call();
                        break;
                    case 2:
                        pages[3].call();
                        break;
                    case 3:
                        Login.logout();
                        return false;
                    case 4:
                        displayInformation();
                        break;
                    default:
                        System.out.println("That was not a valid option");
                }
                displayMenu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /* The menu choices for UI */
    private void displayMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Search for a movie");
        System.out.println("2. Book a movie");
        System.out.println("3. Logout");
    }

    /* Private function to display all movies information */
    private void displayInformation () {
        for (Movie m : movieData) {
            System.out.print(m.getMovieInformation()[0] + " / ");
            System.out.print(m.getMovieInformation()[1] + " / ");
            System.out.print(m.getMovieInformation()[2] + " / ");
            System.out.print(m.getMovieInformation()[3] + " / ");
            System.out.println(m.getMovieInformation()[4]);
        }
    }

}
