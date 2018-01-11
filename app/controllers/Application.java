package controllers;

import com.google.gson.*;
import components.ElasticDeserializer;
import models.Contact;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Controller;

import java.util.List;

public class Application extends Controller {

    private static final String URL_GET_CONTACTS = "http://localhost:9200/mashup/_search";

    public static void index() {
        // Get all contacts from ES
        HttpResponse res = WS.url(URL_GET_CONTACTS)
                .setParameter("size", "1000")
                .get();

        ElasticDeserializer elasticSerializer = new ElasticDeserializer();
        JsonObject json = res.getJson().getAsJsonObject();
        List <Contact> contacts = elasticSerializer.getContactsFromSearch(json);

        render(contacts);
    }

    public static void filter(String name) {
        // Get all contacts from ES
        WS.WSRequest req = WS.url(URL_GET_CONTACTS).setParameter("size", "1000");

        if (!"".equals(name)) {
            req = req.setParameter("q", createFilterQuery(name));
        }

        HttpResponse res = req.get();

        play.Logger.info(name);
        play.Logger.info(res.getStatus().toString());
        play.Logger.info(res.getString());

        // Parse receive data
        ElasticDeserializer elasticSerializer = new ElasticDeserializer();
        JsonObject json = res.getJson().getAsJsonObject();
        List <Contact> contacts = elasticSerializer.getContactsFromSearch(json);

        // Render filters if their are set in order to show filters in front
        renderArgs.put("nameFilter", name);

        renderTemplate("Application/index.html", contacts);
    }

    private static String createFilterQuery(String name) {
        String query = "";

        if (!"".equals(name)) {
            query += "name:'" + name + "'";
        }

        return query;
    }
}
