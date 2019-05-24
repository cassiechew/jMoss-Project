package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A singleton to ensure single access to file writing
 */
public class DataReader {

	/* Basic data reader instance here */
	private static DataReader dataReaderInstance = null;

	/* Data filenames */
	private static final String movieScheduleFilename = "./Timetable_data.csv";
	private static final String userFilename = "./Users.txt";
	private static final String bookingFilename = "./Booking.txt";

	private List<Movie> movieData;

	private DataReader () {};

	/* Singleton pattern */
	public static DataReader getDataReader () {
	    if (dataReaderInstance == null) {
	        dataReaderInstance = new DataReader();
        }
        return dataReaderInstance;
    }

    public void setMovieData(List<Movie> movieData) {
        this.movieData = movieData;
    }

    private Movie getMovieFromId (int id) {
	    for (Movie m : movieData) {
	        if (Integer.parseInt(m.getMovieInformation()[0]) == id) return m;
        }
        return null;
    }

    /**
     * This function reads movie data from the csv file into a list
     * @param mode movies of users
     * @return
     */
	public ArrayList<Object> getData (int mode) {

	    File file = new File((mode == 0) ? movieScheduleFilename : (mode == 1) ? userFilename : bookingFilename);


        List<Object> data;

        /* Streams for reading data */
	    FileInputStream fileInputStream;
	    InputStreamReader inputStreamReader;
	    BufferedReader bufferedReader;

	    /* Buffers */
	    String nextLine;
        String[] bufferStringArray;

	    data = new ArrayList<>();

	    try {



	        /* Opens data streams to read data */
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);


            while ((nextLine = bufferedReader.readLine()) != null) {
                bufferStringArray = nextLine.split(",");
                switch (mode) {

                    /* Checks whether the mode is of movie type or user type to read */
                    case 0:
                        int[] session = Time.getSession(Integer.parseInt(bufferStringArray[3]));

                        data.add(Movie.initializeMovie(Integer.parseInt(bufferStringArray[0]), bufferStringArray[1],
                                new Time(Integer.parseInt(bufferStringArray[4]), session[0], session[1],
                                        Integer.parseInt(bufferStringArray[3])),
                                bufferStringArray[2], Integer.parseInt(bufferStringArray[5])));
                        break;
                    case 1:
                        data.add(User.setDetails(bufferStringArray[0], bufferStringArray[1]));
                        break;
                    case 2:
                        data.add(Booking.createBooking(bufferStringArray[0],
                                getMovieFromId(Integer.parseInt(bufferStringArray[1])), bufferStringArray[2]));

                }

            }

            /* Closes the streams */
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();


        }
        catch ( IOException ioe ) {
	        ioe.printStackTrace();
        }

        return (ArrayList<Object>) data;

    }
	
}
