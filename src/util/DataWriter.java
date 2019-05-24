package util;

import java.io.*;
import java.util.List;

public class DataWriter {

	/* Basic file writer here */

	private static DataWriter dataWriter = null;

    private static String movieScheduleFilename = "./Timetable_data.csv";
    private static final String userFilename = "./Users.txt";
    private static final String bookingFilename = "./Booking.txt";

    private List<Movie> movieData;
    private List<Booking> bookingData;
    private List<User> userData;



    public static DataWriter getDataWriter() {
        if (dataWriter == null) {
            dataWriter = new DataWriter();
        }
        return dataWriter;
    }

    private DataWriter() { }

    public void setData (List<Movie> movieData, List<Booking> bookingData, List<User> userData) {
        this.movieData = movieData;
        this.bookingData = bookingData;
        this.userData = userData;
    }


    public void writeData(int mode) {

        File file = new File((mode == 0) ? movieScheduleFilename : (mode == 1) ? userFilename : bookingFilename);

        /* Output Streams */
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        PrintWriter printWriter;


        try {
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            printWriter = new PrintWriter(outputStreamWriter, true);


            for (Object o : (mode == 0) ? movieData : (mode == 1) ? userData : bookingData) {

                switch (mode) {
                    case 0:
                        Object[] movieData = ((Movie)o).getMovieInformation();
                        Time movieTime = ((Movie)o).getMovieTime();
                        printWriter.println(movieData[0]+","+movieData[1]+","+movieData[2]+","+(movieTime).session + "," + (movieTime).day + "," + movieData[4]);
                        break;
                    case 1:
                        String[] userData = ((User)o).getDetails();
                        printWriter.println(userData[0] + "," + userData[1]);
                        break;
                    case 2:
                        Object[] bookingData = ((Booking)o).getBookingInformation();
                        printWriter.println(bookingData[0]+","+((Movie)bookingData[1]).getMovieInformation()[0]+","+bookingData[2]);
                        break;
                }

            }

            printWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
