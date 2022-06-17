package controllers;

import java.util.List;

import models.Usuario;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class pagina1 extends Controller{
	
	public static void pg1() {
        render();
    }

public static void salvar(Usuario usuario, String senha) {
		
		if (senha.equals("") == false) {
			usuario.senha = senha;
		}
		
		usuario.save();
		flash.success("Salvo com sucesso!");
		Login.form();
		
	}
	
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		renderTemplate("Usuarios/form.html", usuario);
	}
	
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		flash.success("Removido com sucesso!");
		listar();
	}
	
	
	public static void listar() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
}



