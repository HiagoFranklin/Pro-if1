package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Provas extends Controller{
	
	public static void index() {
        render();
    }
}