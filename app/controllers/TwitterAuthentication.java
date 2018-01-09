package controllers;

import models.User;
import oauth.twitter.TwitterUsers;
import play.Logger;
import play.libs.OAuth;
import play.libs.OAuth.ServiceInfo;
import play.libs.WS;
import play.libs.WS.*;
import play.mvc.Controller;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class TwitterAuthentication extends Controller{

    private static String URL_GET_FRIENDS = "https://api.twitter.com/1.1/friends/list.json";

    private static final ServiceInfo TWITTER = new ServiceInfo(
            "https://twitter.com/oauth/request_token",
            "https://twitter.com/oauth/access_token",
            "https://twitter.com/oauth/authorize",
            "KnRMZZuLKgIK3MLVsSulsg4Oh",
            "zKVPKEwsRBwFPbjjtODvXaqFBNKGUg3vlDmYwBC0UWpEAzzKF3"
    );

    public static void index() {
        try {
            renderArgs.put("users", getUsers());
        } catch (Exception e) {
            e.printStackTrace();
            renderArgs.put("error", e.getMessage());
            render();
        }
        render();
    }

    public static TwitterUsers getNextUsers(String nextCursor) throws Exception{
        Gson gson = new Gson();
        String params = "";
        if(!"".equals(nextCursor)){
            params = "?cursor=" + nextCursor;
        }
        HttpResponse res = WS.url(URL_GET_FRIENDS + params).oauth(TWITTER, getUser().token, getUser().secret).get();
        Logger.info("Result request : " + res.getStatus());
        if(res.getStatus() != 200){
            throw new Exception("Get Users Exception : " + res.getString());
        }
        return gson.fromJson(res.getString(), TwitterUsers.class);
    }
    
    public static List<User> getUsers() throws Exception {
        String nextCursor = "";
        List<User> users = new ArrayList<>(0);
        while(!"0".equals(nextCursor)){
            TwitterUsers twitterUsers = getNextUsers(nextCursor);
            Logger.info("twitterUsers: " + twitterUsers);
            nextCursor = twitterUsers.getNext_cursor();
            Logger.info("Nextcursor: " + nextCursor);
            users.addAll(twitterUsers.getUsers());
        }
        return users;
    }

    // Twitter authentication
    public static void authenticate() {
        User user = getUser();
        Logger.info(user.token, user.username, user.secret);
        if (OAuth.isVerifierResponse()) {
            // We got the verifier; now get the access token, store it and back to index
            OAuth.Response oauthResponse = OAuth.service(TWITTER).retrieveAccessToken(user.token, user.secret);
            if (oauthResponse.error == null) {
                user.token = oauthResponse.token;
                user.secret = oauthResponse.secret;
                user.save();
                Logger.info("save User retrieveAccessToken" + user.secret);
            } else {
                Logger.error("Error connecting to twitter: " + oauthResponse.error);
            }
            index();
        }
        OAuth twitt = OAuth.service(TWITTER);
        OAuth.Response oauthResponse = twitt.retrieveRequestToken();
        if (oauthResponse.error == null) {
            // We received the unauthorized tokens in the OAuth object - store it before we proceed
            user.token = oauthResponse.token;
            user.secret = oauthResponse.secret;
            user.save();
            Logger.info("save User retrieveRequestToken");
            Logger.info("Redirect URL_GET_FRIENDS " + twitt.redirectUrl(oauthResponse.token));
            redirect(twitt.redirectUrl(oauthResponse.token));
        } else {
            Logger.error("Error connecting to twitter: " + oauthResponse.error);
            index();
        }
    }

    private static User getUser() {
        return User.findOrCreate("guest");
    }

}