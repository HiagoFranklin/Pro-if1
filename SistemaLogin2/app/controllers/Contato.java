package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Contato extends Controller{
	
	public static void index() {
        render();
    }
}