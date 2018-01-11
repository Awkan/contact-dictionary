package controllers;

import com.google.gson.*;
import components.Normalizer;
import models.Contact;
import models.User;
import oauth.twitter.TwitterUsers;
import oauth.twitter.Users;
import play.Logger;
import play.Play;
import play.libs.OAuth;
import play.libs.OAuth.ServiceInfo;
import play.libs.WS;
import play.libs.WS.*;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

public class GoogleAuthentication extends Controller{

    private static String URL_GET_AUTHORIZATION = "https://api.twitter.com/1.1/friends/list.json?count=200";

    public static void authenticate() {

        redirect("https://accounts.google.com/o/oauth2/v2/auth?" +
                "scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive.metadata.readonly&" +
                "access_type=offline&" +
                "include_granted_scopes=true&" +
                "state=state_parameter_passthrough_value&" +
                "redirect_uri=http://localhost:9000/google/import&" +
                "response_type=code&" +
                "client_id=292609518544-em0pi9k905jiqou359gj2v2gng3hhkqo.apps.googleusercontent.com");

   /*     HttpResponse res = WS.url("https://accounts.google.com/o/oauth2/v2/auth?" +
                "scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive.metadata.readonly&" +
                "access_type=offline&" +
                "include_granted_scopes=true&" +
                "state=state_parameter_passthrough_value&" +
                "redirect_uri=http://localhost:9000/google/import&" +
                "response_type=code&" +
                "client_id=292609518544-em0pi9k905jiqou359gj2v2gng3hhkqo.apps.googleusercontent.com").get();
        Logger.info("Result request : " + res.getStatus());
        if(res.getStatus() != 200){
            Logger.error("Exception : " + res.getString());
        }
        Logger.info(res.getString());

        render();

        */

    }

    public static void index(String code) {
        Logger.info("index() + code="+code);
        render();
    }

}
