package util;

import java.util.Date;

public class Time {

    private static final int SESSIONONE = 1;
    private static final int SESSIONTWO = 2;
    private static final int SESSIONTHR = 3;
    private static final int SESSIONFUR = 4;
    private static final int SESSIONFVE = 5;
    //private static final int SESSIONSIX = 6;
    //private static final int SESSIONSVN = 7;


	//public static final int currentYear = 2018;
	//public static final int currentMonth = 4;

	//public int year;
	//public int month;
	public int day;
	
	public int hour;
	public int minute;
	public int second;
	public int session;


	public Time (int day, int hour, int minute, int session) {

		//Date date = new Date(System.currentTimeMillis());



		//this.year = year;
		//this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.session = session;
		//this.second = second;
	}

	public static int[] getSession (int sessionNumber) {
	    int[] hourMinute;

	    hourMinute = new int[2];

	    switch (sessionNumber) {
            case SESSIONONE:
                hourMinute[0] = 9;
                hourMinute[1] = 0;
                return hourMinute;
			case SESSIONTWO:
				hourMinute[0] = 11;
				hourMinute[1] = 0;
				return hourMinute;
			case SESSIONTHR:
				hourMinute[0] = 13;
				hourMinute[1] = 0;
				return hourMinute;
			case SESSIONFUR:
				hourMinute[0] = 15;
				hourMinute[1] = 0;
				return hourMinute;
			case SESSIONFVE:
				hourMinute[0] = 17;
				hourMinute[1] = 0;
				return hourMinute;
			/*case SESSIONSIX:
				hourMinute[0] = 19;
				hourMinute[1] = 0;
				return hourMinute;
			case SESSIONSVN:
				hourMinute[0] = 21;
				hourMinute[1] = 0;
				return hourMinute;*/

            default:
                return null;


        }
    }

    public int[] getWritingInformation () {
	    int[] data = new int[2];
	    data[0] = day;
	    data[1] = session;
	    return data;
    }

    @Override
	public String toString() {

	    String sday = "";



		switch (day) {
            case 1:
                sday = "Monday";
                break;
            case 2:
                sday = "Tuesday";
                break;
            case 3:
                sday = "Wednesday";
                break;
            case 4:
                sday = "Thursday";
                break;
            case 5:
                sday = "Friday";
                break;
            case 6:
                sday = "Saturday";
                break;
            case 7:
                sday = "Sunday";
                break;

        }

		return (sday + " " + hour + ":" + minute);
	}
	
}
