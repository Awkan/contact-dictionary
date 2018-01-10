package controllers;

import models.Contact;
import play.Logger;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    private static final String URL_GET_CONTACTS = "http://localhost:9200/mashup/_search";

    public static void index() {
        List<Contact> contactList = Contact.findAll();

        // Get all contacts from ES
        HttpResponse res = WS.url(URL_GET_CONTACTS)
                .setParameter("size", "1000")
                .get();

        renderArgs.put("res", res.getString());
        render(contactList);
    }

}
