package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;


public class Usuarios extends Controller {
	
	public static void form() {
		Usuario usuario = (Usuario)Cache.get("usuario");
		Cache.clear();
		render(usuario);
	}
	
	public static void salvar( Usuario usuario, String senha, File foto) {
		
		
		
		
		if (senha.equals("") == false ||  usuario.id == null) {
			usuario.senha = senha;
		
			if (senha.length() < 6) {
				validation.addError("usuario.senha", "A senha deve conter no mínimo 6 dígitos");
			}
		}
		
		validation.valid(usuario);
		
		
		if (senha.equals("")  && usuario.id != null) {
			
			validation.removeErrors("usuario.senha");
			if (validation.errors().size() == 1) {
				validation.clear();
			}
			
			
		}
		
		if (validation.hasErrors()) {
			validation.keep();
			flash.error("Falha ao salvar o usuário");
			Cache.set("usuario", usuario);
			form();
		}
		
		usuario.nomeFoto = foto.getName();
		usuario.save();
		
		new File("./uploads/" + usuario.id).mkdirs();
		
		File dest = new File("./uploads/" + usuario.id + "/" + foto.getName());
		
		if (dest.exists()) {
			dest.delete();
		}
		
		
		foto.renameTo(dest);
		
		flash.success("Salvo com sucesso!");
		Login.form();
		
	}
	
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		renderTemplate("Editar/editarUsu.html", usuario);
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
