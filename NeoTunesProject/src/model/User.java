package model;

import java.time.LocalDate;

public class User {

    private String nickname;
    private String id;
    private LocalDate linkUpDate;

    public User(String nickname, String id, LocalDate linkUpDate) {
        this.nickname = nickname;
        this.id = id;
        this.linkUpDate = linkUpDate;
    }

    
    /** 
     * @return String
     */
    public String getNickname() {
        return this.nickname;
    }

}
