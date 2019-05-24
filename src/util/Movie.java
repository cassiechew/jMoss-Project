package util;

import java.util.Date;

public class Movie {

	/***
	 * 
	 * Name					Place					Time
	 * Movie A				StKilda					13:30
	 * Movie A				Fitzroy					16:00
	 * Movie A				Lilydale				8:00
	 * etc
	 * 
	 */
	
	
	//private long movieId;
    private static final int DATANUMBER = 5;

    private int movieId;
	private String movieName;
	private Time movieTime;
	//private Place moviePlace;
	private String moviePlace;
	private int availableSeats;

	private Movie () {};


	public static Movie initializeMovie(int movieId, String movieName, Time movieTime, String moviePlace, int availableSeats) {

	    Movie newMovie = new Movie();

	    newMovie.movieId = movieId;
	    newMovie.movieName = movieName;
	    newMovie.moviePlace = moviePlace;
	    newMovie.movieTime = movieTime;
	    newMovie.availableSeats = availableSeats;

	    return newMovie;

    }

    public void modifySeats (int increment) {
	    this.availableSeats += increment;
    }

    public Time getMovieTime () {
	    return this.movieTime;
    }

    public String[] getMovieInformation () {
        String[] returnArray = new String[DATANUMBER];

        returnArray[0] = Integer.toString(movieId);
        returnArray[1] = movieName;
        returnArray[2] = moviePlace;
        returnArray[3] = movieTime.toString();
        returnArray[4] = Integer.toString(availableSeats);



	    return returnArray;

    }
	
}
