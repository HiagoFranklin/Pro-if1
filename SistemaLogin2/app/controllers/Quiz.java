package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Quiz extends Controller{
	
	public static void quiz1() {
        render();
    }
}