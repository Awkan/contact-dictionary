package components;

import models.GeoPoint;
import components.geoname.Geoname;
import models.Contact;
import oauth.google.Connections;
import oauth.twitter.Users;
import play.libs.WS;
import play.libs.WS.*;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class Normalizer {

    private static final Gson GSON = new Gson();

    private static final String URL_POST_TWITTER_CONTACT = "http://localhost:9200/mashup/contact";
    private static final String URL_GET_GEOPOSITION = "http://api.geonames.org/searchJSON?maxRows=1&username=gonzalt03&q=";

    private GeoPoint getGeoPosition(String position){
        Gson gson = new Gson();
        GeoPoint geoPoint = new GeoPoint();
        HttpResponse res = WS.url(URL_GET_GEOPOSITION + position).get();
        if(res.getStatus() != 200){
            return geoPoint;
        }
        Geoname geoname = gson.fromJson(res.getString(), Geoname.class);

        if(!geoname.getGeonames().isEmpty()){
            geoPoint.resetLat(Double.parseDouble(geoname.getGeonames().get(0).getLat()));
            geoPoint.resetLon(Double.parseDouble(geoname.getGeonames().get(0).getLng()));
        }

        return geoPoint;
    }

    public List<Contact> normalizeTwitterUsers(List<Users> usersList) {
        List<Contact> contacts = new ArrayList<Contact>();

        for (Users user :
                usersList) {
            Contact contact = new Contact();
            contact.setImporter(Contact.IMPORTER_TWITTER);
            contact.setName(user.getName());
            contact.setLocation(user.getLocation());
            contact.setLocationGeoNames(getGeoPosition(user.getLocation()));
            contact.setLogoUrl(user.getProfile_image_url());
            contact.setBannerUrl(user.getProfile_banner_url());
            // Save contact & add it to out contact list
            contact.save();
            saveContactToElasticsearch(contact);
            contacts.add(contact);
        }

        return contacts;
    }

    public List<Contact> normalizeGoogleUsers(List<Connections> usersList) {
        List<Contact> contacts = new ArrayList<Contact>();
        for (Connections connections : usersList) {
            Contact contact = new Contact();
            contact.setImporter(Contact.IMPORTER_GOOGLE);
            contact.setName(connections.getNames().get(0).getDisplayName());

            if(connections.getAddresses() != null && !connections.getAddresses().isEmpty()) {
                contact.setLocation(connections.getAddresses().get(0).toString().replaceAll("\n", " "));
                contact.setLocationGeoNames(getGeoPosition(contact.getLocation()));
            }

            if(!connections.getPhotos().isEmpty()){
                contact.setLogoUrl(connections.getPhotos().get(0).getUrl());
            }

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
    }
}
