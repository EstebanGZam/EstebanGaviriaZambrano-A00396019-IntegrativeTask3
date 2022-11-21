package model;

import java.time.LocalDate;

/**
 * <b>Name:</b> User <br>
 * <b>Description:</b> User objects class. <br>
 */
public class User {

    private String nickname;
    private String id;
    private LocalDate linkUpDate;

    /**
     * <b>Name:</b> User <br>
     * <b>Description:</b> Constructor method of User class. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param nickname   User nickname
     * @param id         Identity document of the user.
     * @param linkUpDate Date of user's link to the platform.
     */
    public User(String nickname, String id, LocalDate linkUpDate) {
        this.nickname = nickname;
        this.id = id;
        this.linkUpDate = linkUpDate;
    }

    /**
     * <b>Name:</b> getNickname <br>
     * <b>Description:</b> nickname getter method. <br>
     * 
     * @return String Nickname attribute.
     */
    public String getNickname() {
        return this.nickname;
    }

}
