package components;

import models.User;
import play.Logger;
import play.libs.OAuth;
import play.libs.OAuth.ServiceInfo;
import play.libs.WS;
import play.mvc.Controller;

public class TwitterAuthenticator extends Controller{

    private static final ServiceInfo TWITTER = new ServiceInfo(
            "https://twitter.com/oauth/request_token",
            "https://twitter.com/oauth/access_token",
            "https://twitter.com/oauth/authorize",
            "hVEgGJjzJ04qm27bBj3YmmrFW",
            "Cbz1WJ99aKbmwl42s8YE95DkeSNor6Y7pTi8aamf4xjwLciX8Z"
    );

    public static String getUsers(User user) {
        String url = "https://api.twitter.com/1.1/friends/list.json";
        String mentions = "";
        mentions = WS.url(url).oauth(TWITTER, user.token, user.secret).get().getString();
        return mentions;
    }

    // Twitter authentication
    public static User authenticate() {
        User user = getUser();
        Logger.info(user.token, user.username, user.secret);
        if (OAuth.isVerifierResponse()) {
            // We got the verifier; now get the access token, store it and back to index
            OAuth.Response oauthResponse = OAuth.service(TWITTER).retrieveAccessToken(user.token, user.secret);
            if (oauthResponse.error == null) {
                user.token = oauthResponse.token;
                user.secret = oauthResponse.secret;
                user.save();
            } else {
                Logger.error("Error connecting to twitter retrieveAccessToken: " + oauthResponse.error);
            }
        }
        OAuth twitt = OAuth.service(TWITTER);
        OAuth.Response oauthResponse = twitt.retrieveRequestToken();
        if (oauthResponse.error == null) {
            // We received the unauthorized tokens in the OAuth object - store it before we proceed
            user.token = oauthResponse.token;
            user.secret = oauthResponse.secret;
            user.save();
            redirect(twitt.redirectUrl(oauthResponse.token));
        } else {
            Logger.error("Error connecting to twitter retrieveRequestToken: " + oauthResponse.error);
        }
        return getUser();
    }

    private static User getUser() {
        return User.findOrCreate("guest");
    }

}
