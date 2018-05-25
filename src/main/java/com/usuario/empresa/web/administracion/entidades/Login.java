package com.usuario.empresa.web.administracion.entidades;


public class Login {
    private String correo;
    private String contrasena;
	
    public Login(String correo, String contrasena) {
		super();
		this.correo = correo;
		this.contrasena = contrasena;
	}
    
    public Login() { 
    	
    }

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
    
    
}
