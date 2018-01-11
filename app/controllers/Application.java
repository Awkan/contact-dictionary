package controllers;

import com.google.gson.*;
import components.ElasticSerializer;
import models.Contact;
import play.Logger;
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

        ElasticSerializer elasticSerializer = new ElasticSerializer();
        JsonObject json = res.getJson().getAsJsonObject();
        List <Contact> contacts = elasticSerializer.getContactsFromSearch(json);

        render(contacts);
    }

}
