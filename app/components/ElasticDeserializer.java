package components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Contact;
import models.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class ElasticDeserializer {

    public static List<Contact> getContactsFromSearch(JsonElement json) {
        List<Contact> contacts = new ArrayList<Contact>();

        JsonObject jsonObj = json.getAsJsonObject();
        JsonObject parentHitObj = jsonObj.getAsJsonObject("hits");

        if (null != parentHitObj) {
            JsonArray hits = parentHitObj.getAsJsonArray("hits");

            for (JsonElement hit :
                    hits) {
                JsonObject hitObj = hit.getAsJsonObject();
                JsonObject sourceObj = hitObj.getAsJsonObject("_source");

                Contact contact = new Contact();

                if (null != sourceObj.get("importer")) {
                    contact.setImporter(sourceObj.get("importer").toString().replaceAll("\"", ""));
                }
                if (null != sourceObj.get("name")) {
                    contact.setName(sourceObj.get("name").toString().replaceAll("\"", ""));
                }
                if (null != sourceObj.get("location")) {
                    contact.setLocation(sourceObj.get("location").toString().replaceAll("\"", ""));
                }
                if (null != sourceObj.get("logoUrl")) {
                    contact.setLogoUrl(sourceObj.get("logoUrl").toString().replaceAll("\"", "").replaceAll("_normal", ""));
                }
                if (null != sourceObj.get("bannerUrl")) {
                    contact.setBannerUrl(sourceObj.get("bannerUrl").toString().replaceAll("\"", ""));
                }
                if (null != sourceObj.get("locationGeoNames")) {
                    GeoPoint geoPoint = new GeoPoint();
                    if (null != sourceObj.getAsJsonObject("locationGeoNames").get("lat")) {
                        geoPoint.resetLat(Double.valueOf(sourceObj.getAsJsonObject("locationGeoNames").get("lat").toString()));
                    }
                    if (null != sourceObj.getAsJsonObject("locationGeoNames").get("lon")) {
                        geoPoint.resetLon(Double.valueOf(sourceObj.getAsJsonObject("locationGeoNames").get("lon").toString()));
                    }
                    contact.setLocationGeoNames(geoPoint);
                }
                contacts.add(contact);
            }
        }

        return contacts;
    }
}
