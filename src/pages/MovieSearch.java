package pages;

import Interfaces.Page;
import util.Booking;
import util.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class MovieSearch implements Page{

    private static final int TOPLEVEL = 1;
    private static final int SEARCHLEVEL = 2;

    private static final int MOVIENAME = 1;
    private static final int LOCATION  = 2;
    private static final int CUSTEMAIL = 3;
    private static final int EXITING = 4;

    private List<Movie> movieData;
    private List<Booking> bookingData;
    private BufferedReader bufferedReader;

    private int patternType;

    public MovieSearch(BufferedReader bufferedReader, List<Movie> movieData, List<Booking> bookingData) {

        this.movieData = movieData;
        this.bookingData = bookingData;
        this.bufferedReader = bufferedReader;
        patternType = -1;
    }

    @Override
    public Object call() {

        String pattern;
        String choice;

        displayChoiceMenu(TOPLEVEL);

        try {
            while ((choice = bufferedReader.readLine()) != null) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        patternType = MOVIENAME;
                        break;
                    case 2:
                        patternType = LOCATION;
                        break;
                    case 3:
                        patternType = CUSTEMAIL;
                        break;
                    case 4:
                        patternType = EXITING;
                        break;
                    default:
                        System.out.println("Please enter a valid choice");
                }
                if (patternType != -1) {
                    if (patternType == EXITING) break;
                    displayChoiceMenu(SEARCHLEVEL);

                    pattern = bufferedReader.readLine();
                    if (patternType == CUSTEMAIL) {
                        bookingSearch(pattern);
                    } else {
                        movieSearch(pattern);
                    }
                    patternType = -1;
                    displayChoiceMenu(TOPLEVEL);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        return null;
    }


    /**
     * A quick linear search O(n)
     * @param pattern The string pattern that will be used to search
     */
    private void movieSearch (String pattern) {
        System.out.println("Searching...");
        for (Movie movie : movieData) {
            String[] movieInfo = movie.getMovieInformation();
            //System.out.println(movieInfo + "  " + pattern);
            if (movieInfo[patternType].equals(pattern)) {
                System.out.println("Movie Name------: " + movieInfo[1] +
                                 "\nTheater---------: " + movieInfo[2] +
                                 "\nTime------------: " + movieInfo[3] +
                                 "\nAvailable Seats-: " + movieInfo[4] + "\n");
            }
        }

    }

    public void bookingSearch (String pattern) {
        for (Booking booking : bookingData) {
            if (booking.getBookingInformation()[0].equals(pattern)) {
                System.out.println("Movie Name -----: " + ((Movie)booking.getBookingInformation()[1]).getMovieInformation()[1] +
                                 "\nCustomer Email -: " + booking.getBookingInformation()[0] +
                                 "\nSuburb ---------: " + booking.getBookingInformation()[2] +
                                 "\nTime -----------: " + ((Movie)booking.getBookingInformation()[1]).getMovieInformation()[3] + "\n");
            }
        }
    }

    /**
     * Displays the search menu
     * @param level How deep into the menu to display
     */
    private void displayChoiceMenu (int level) {
        if (level == TOPLEVEL) {
            System.out.println("How would you like to search?");
            System.out.println("1. Movie name");
            System.out.println("2. Theater location");
            System.out.println("3. Customer email");
            System.out.println("4. Back to menu");
        } else if (level == SEARCHLEVEL) {
            System.out.println("Please enter search pattern: ");
        }
    }


}
