package controllers;

import java.net.URLEncoder;

import components.TwitterAuthenticator;
import play.Logger;
import play.libs.OAuth;
import play.libs.OAuth.ServiceInfo;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Controller;
import play.mvc.Scope.Params;

import models.User;

public class Application extends Controller {

    public static void index() {
        TwitterAuthenticator twitterAuthenticator = new TwitterAuthenticator();
        User user = TwitterAuthenticator.authenticate();
        Logger.info(user.token, user.username, user.secret);
        String result = TwitterAuthenticator.getUsers(user);
        System.out.println(result);
        render();
    }

}
