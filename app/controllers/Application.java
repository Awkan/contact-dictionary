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

        ElasticDeserializer elasticDeserializer = new ElasticDeserializer();
        JsonObject json = res.getJson().getAsJsonObject();
        List <Contact> contacts = elasticDeserializer.getContactsFromSearch(json);

        render(contacts);
    }

    public static void filter(String name, String importer) {
        // Get all contacts from ES
        WS.WSRequest req = WS
                .url(URL_GET_CONTACTS)
                .setParameter("size", "1000");

        if (!"".equals(name) || !"".equals(importer)) {
            req = req
                    .setParameter("default_operator", "AND")
                    .setParameter("q", createFilterQuery(name, importer));
        }

        HttpResponse res = req.get();

        play.Logger.info(name);
        play.Logger.info(res.getStatus().toString());
        play.Logger.info(res.getString());

        // Parse receive data
        ElasticDeserializer elasticDeserializer = new ElasticDeserializer();
        JsonObject json = res.getJson().getAsJsonObject();
        List <Contact> contacts = elasticDeserializer.getContactsFromSearch(json);

        // Render filters if their are set in order to show filters in front
        renderArgs.put("nameFilter", name);
        renderArgs.put("importerFilter", importer);

        renderTemplate("Application/index.html", contacts);
    }

    private static String createFilterQuery(String name, String importer) {
        String query = "";

        if (!"".equals(name)) {
            query += "name:'" + name + "'";
        }

        if (!"".equals(query)) {
            query += " ";
        }

        if (!"".equals(importer)) {
            query += "importer:'" + importer + "'";
        }

        return query;
    }
}
