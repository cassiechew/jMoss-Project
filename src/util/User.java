package util;


/**
 * A user class that holds the details of the user
 */
public class User {

    /* Account details */
    private String username;
    private String password;

    /* Sets the details, used when reading information from file */
    public static User setDetails (String username, String password) {
        User newUser = new User();
        newUser.username = username;
        newUser.password = password;
        return newUser;
    }


    /* A verify function for login */
    public boolean verifyDetails(String username, String password) {
        return (username.equals(this.username) && password.equals(this.password));
    }

    public String[] getDetails () {
        String[] data = new String[2];
        data[0] = this.username;
        data[1] = this.password;
        return data;
    }

}
