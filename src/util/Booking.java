package util;

public class Booking {

    private static final int datanumber = 3;

    private String customerEmail;
    private Movie bookedMovie;
    private String suburb;

    public static Booking createBooking (String customerEmail, Movie bookedMovie, String suburb) {
        Booking booking = new Booking();
        booking.customerEmail = customerEmail;
        booking.bookedMovie = bookedMovie;
        booking.suburb = suburb;
        return booking;
    }

    public Object[] getBookingInformation () {

        Object[] returnArray = new Object[datanumber];

        returnArray[0] = customerEmail;
        returnArray[1] = bookedMovie;
        returnArray[2] = suburb;

        return returnArray;


    }

}
