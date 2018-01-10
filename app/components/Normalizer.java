package components;

import models.Contact;
import oauth.twitter.Users;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

public class Normalizer {
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
            contacts.add(contact);
        }

        return contacts;
    }
}
