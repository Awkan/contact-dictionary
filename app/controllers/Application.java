package controllers;

import models.Contact;
import play.Logger;
import play.libs.OAuth;
import play.libs.OAuth.ServiceInfo;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Controller;
import play.mvc.Scope.Params;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static void index() {
        List<Contact> contactList = Contact.findAll();
        render(contactList);
    }

}
