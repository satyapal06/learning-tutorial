package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.first.*;

public class FirstController extends Controller {

    public Result welcome(String firstName, String lastName) {
        return ok(first.render(firstName, lastName));
    }
}
