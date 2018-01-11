package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import play.Logger;

@Entity
public class User extends Model {

    public String username;
    public String token;
    public String secret;

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "username="+ username + " token="+token + " secret="+secret;
    }

    public static User findOrCreate(String username) {
        User user = User.find("username", username).first();
        if (user == null) {
            Logger.info("Create new User");
            user = new User(username);
        }else{
            Logger.info("Find User " + user.toString());
        }
        return user;
    }

}
