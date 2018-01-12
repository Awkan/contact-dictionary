package controllers;

import play.mvc.Controller;

public class Errors extends Controller {

    public static void message(String message) {
        // Render error message
        renderArgs.put("message", message);
        render();
    }

}
