package components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ElasticSerializer {

    public static List<Contact> getContactsFromSearch(JsonElement json) {
        List<Contact> contacts = new ArrayList<Contact>();

        JsonObject jsonObj = json.getAsJsonObject();
        JsonObject parentHitObj = jsonObj.getAsJsonObject("hits");
        JsonArray hits = parentHitObj.getAsJsonArray("hits");

        for (JsonElement hit :
                hits) {
            JsonObject hitObj = hit.getAsJsonObject();
            JsonObject sourceObj = hitObj.getAsJsonObject("_source");

            Contact contact = new Contact();

            if (null != sourceObj.get("importer")) {
                contact.setImporter(sourceObj.get("importer").toString());
            }
            if (null != sourceObj.get("name")) {
                contact.setName(sourceObj.get("name").toString());
            }
            if (null != sourceObj.get("location")) {
                contact.setLocation(sourceObj.get("location").toString());
            }
            if (null != sourceObj.get("logoUrl")) {
                contact.setLogoUrl(sourceObj.get("logoUrl").toString().replaceAll("\"", ""));
            }
            if (null != sourceObj.get("bannerUrl")) {
                contact.setBannerUrl(sourceObj.get("bannerUrl").toString());
            }
            contacts.add(contact);

        }

        return contacts;
    }
}
