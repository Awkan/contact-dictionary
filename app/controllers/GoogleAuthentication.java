package controllers;

import com.google.gson.*;
import models.User;
import oauth.twitter.TwitterUsers;
import oauth.twitter.Users;
import play.Logger;
import play.libs.OAuth;
import play.libs.OAuth.ServiceInfo;
import play.libs.WS;
import play.libs.WS.*;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;
import models.User;
import play.Logger;
import play.libs.OAuth2;
import play.libs.WS;
import play.mvc.Before;
import play.mvc.Controller;

import com.google.gson.JsonObject;

public class GoogleAuthentication extends Controller{

    public static OAuth2 FACEBOOK = new OAuth2(
            "https://accounts.google.com/o/oauth2/auth",
            "https://accounts.google.com/o/oauth2/token",
            "292609518544-em0pi9k905jiqou359gj2v2gng3hhkqo.apps.googleusercontent.com",
            "3NE89YfQXkX14BjPLQkdYAQg"
    );

    public static void index() {
        User u = connected();
        JsonObject me = null;
        if (u != null && u.access_token != null) {
            me = WS.url("https://graph.facebook.com/me?access_token=%s", WS.encode(u.access_token)).get().getJson().getAsJsonObject();
        }
        render(me);
    }

    public static void auth() {
        if (OAuth2.isCodeResponse()) {
            User u = connected();
            OAuth2.Response response = FACEBOOK.retrieveAccessToken(authURL());
            u.access_token = response.accessToken;
            u.save();
            index();
        }
        FACEBOOK.retrieveVerificationCode(authURL());
    }

    @Before
    static void setuser() {
        User user = null;
        if (session.contains("uid")) {
            Logger.info("existing user: " + session.get("uid"));
            user = User.get(Long.parseLong(session.get("uid")));
        }
        if (user == null) {
            user = User.createNew();
            session.put("uid", user.uid);
        }
        renderArgs.put("user", user);
    }

    static String authURL() {
        return play.mvc.Router.getFullUrl("Application.auth");
    }

    static User connected() {
        return (User)renderArgs.get("user");
    }


}
