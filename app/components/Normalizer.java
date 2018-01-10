package components;

import models.Contact;
import oauth.twitter.Users;
import play.libs.WS;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class Normalizer {

    private static final Gson GSON = new Gson();

    private static final String URL_POST_TWITTER_CONTACT = "http://localhost:9200/mashup/contact";

    public List<Contact> normalizeTwitterUsers(List<Users> usersList) {
        List<Contact> contacts = new ArrayList<Contact>();

        for (Users user :
                usersList) {
            Contact contact = new Contact();
            contact.setImporter(Contact.IMPORTER_TWITTER);
            contact.setName(user.getName());
            contact.setLocation(user.getLocation());
            contact.setLogoUrl(user.getProfile_image_url());
            contact.setBannerUrl(user.getProfile_banner_url());
            // Save contact & add it to out contact list
            contact.save();
            saveContactToElasticsearch(contact);
            contacts.add(contact);
        }

        return contacts;
    }

    private void saveContactToElasticsearch(Contact contact) {
        String serialized = GSON.toJson(contact, Contact.class);
        WS.HttpResponse res = WS.url(URL_POST_TWITTER_CONTACT)
                .setHeader("Content-Type", "application/json")
                .body(serialized)
                .post();
        play.Logger.info(res.getString());
    }
}
