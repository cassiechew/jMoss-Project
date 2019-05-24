package pages;

import Interfaces.Page;
import util.Booking;
import util.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieBooking implements Page {

    private static final String emailValidatorString = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;


    private List<Movie> movieData;
    private List<Booking> bookingData;
    private BufferedReader bufferedReader;

    public MovieBooking (BufferedReader bufferedReader, List<Movie> movieData, List<Booking> bookingData) {
        this.bufferedReader = bufferedReader;
        this.movieData = movieData;
        this.bookingData = bookingData;

        pattern = Pattern.compile(emailValidatorString);
    }

    private boolean validateEmail (final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }


    private Object[] readNewBookingInformation () throws IOException{
        boolean passed = false;
        int currentPos = 0;
        Object[] newBookingHolder = new Object[3];
        String input;
        System.out.println("Please enter your email");
        while (((input = bufferedReader.readLine()) != null ) && !passed) {
            switch (currentPos) {
                //email
                case 0:

                    if (validateEmail(input)) {
                        newBookingHolder[0] = input;
                        currentPos++;
                    }
                    else {
                        continue;
                    }
                    displayInformation();
                    System.out.println("Please enter the movie ID");
                    break;
                //movie
                case 1:
                    for (Movie m : movieData) {
                        if (Integer.parseInt(m.getMovieInformation()[0]) == Integer.parseInt(input)) {
                            newBookingHolder[1] = m;
                            m.modifySeats(-1);
                            currentPos++;

                        }
                    }
                    if (currentPos == 1) {
                        System.out.println("That was an invalid movie ID, try again");
                        continue;
                    }
                    System.out.println("Please enter your suburb");
                    break;
                //suburb
                case 2:
                    newBookingHolder[2] = input;
                    passed = true;
                    break;
            }

            if (passed) {
                System.out.println("Are you sure everything is correct?[yes/no]");
                System.out.println(newBookingHolder[0]);
                System.out.println(((Movie)newBookingHolder[1]).getMovieInformation()[1] + " at " +
                        ((Movie)newBookingHolder[1]).getMovieInformation()[3]);
                System.out.println(newBookingHolder[2]);

                String confirmation;
                boolean confirmed = false;
                while ((confirmation = bufferedReader.readLine()) != null) {
                    confirmation = confirmation.toLowerCase();
                    if (confirmation.equals("yes")) {
                        confirmed = true;
                        break;
                    }
                    else if (confirmation.equals("no")) {
                        confirmed = false;
                        break;
                    }
                }


                if (confirmed) {
                    break;
                }
                else {
                    System.out.println("Please start over");
                }
            }
        }
        return newBookingHolder;
    }


    private void deleteBooking () throws IOException{

        String email;
        boolean passed = false;

        System.out.println("Please enter your email");

        while (((email = bufferedReader.readLine()) != null)) {
            if (!validateEmail(email)) {
                System.out.println("Please enter a valid email");
                continue;
            }
            if (passed) break;
            for (Booking booking : bookingData) {
                if (booking.getBookingInformation()[0].equals(email)) {
                    for (Movie m : movieData) {
                        if (m.getMovieInformation()[0].equals(((Movie)booking.getBookingInformation()[1]).getMovieInformation()[0])) {
                            m.modifySeats(1);
                            break;
                        }
                    }
                    bookingData.remove(booking);

                    passed = true;
                    break;
                }
            }
            if(passed) break;

        }

    }

    private void createBooking () throws IOException {

        String email;
        String movie;
        System.out.println("Please enter your email");

        while (((email = bufferedReader.readLine()) != null)) {
            if (!validateEmail(email)) {
                System.out.println("Please enter a valid email");
                break;
            }
        }

        displayInformation();
        System.out.println("Please select a movie (input movie ID");

        while ((movie = bufferedReader.readLine()) != null) {

        }


    }


    private void displayInformation () {
        for (Movie m : movieData) {
            System.out.print(m.getMovieInformation()[0] + " / ");
            System.out.print(m.getMovieInformation()[1] + " / ");
            System.out.print(m.getMovieInformation()[2] + " / ");
            System.out.print(m.getMovieInformation()[3] + " / ");
            System.out.println(m.getMovieInformation()[4]);
        }
    }

    private void showAllBoookings () {
        for (Booking b : bookingData) {
            System.out.println("Email-----: " + b.getBookingInformation()[0]);
            System.out.println("Movie Name: "+((Movie)b.getBookingInformation()[1]).getMovieInformation()[1]);
            System.out.println("Movie Time: "+((Movie)b.getBookingInformation()[1]).getMovieInformation()[3]);
            System.out.println("Suburb----: "+ b.getBookingInformation()[2]);
            System.out.println("\n");
        }
    }

    @Override
    public Object call() {

        String choice;

        displayMenu();

        try {
            while ((choice = bufferedReader.readLine()) != null) {

                switch (Integer.parseInt(choice)) {
                    case 1:

                        Object[] newMovieBookingData = readNewBookingInformation();

                        bookingData.add(Booking.createBooking((String)newMovieBookingData[0],
                                (Movie)newMovieBookingData[1], (String)newMovieBookingData[2]));

                        break;
                    case 2:

                        deleteBooking();

                        break;
                    case 3:
                        showAllBoookings();
                        break;
                    case 4:
                        return null;
                    default:
                        System.out.println("Please enter a valid choice!");
                }

                displayMenu();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private void displayMenu () {

        System.out.println("What would you like to do?");
        System.out.println("1. Book a movie");
        System.out.println("2. Cancel a booking");
        System.out.println("3. Show all bookings");
        System.out.println("4. Exit");

    }

}
