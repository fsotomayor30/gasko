package com.usuario.empresa.web.administracion.entidades;


public class Users {
    private String username;
    private String password;
    private boolean enabled;
    private int num_house;
    private int rol;
	
    public Users(String username, String password, boolean enabled, int num_house, int rol) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.num_house = num_house;
		this.rol = rol;
	}

	public Users() { 
		
	}
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getNum_house() {
		return num_house;
	}

	public void setNum_house(int num_house) {
		this.num_house = num_house;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	} 
    
    
    
    
    

    /*public Miembro(int id_miembro, String correo_miembro, String contrasena_miembro) {
        this.id_miembro = id_miembro;
        this.correo_miembro = correo_miembro;
        this.contrasena_miembro = contrasena_miembro;
    }

    public Miembro() {
    }

    public int getId_miembro() {
        return id_miembro;
    }

    public void setId_miembro(int id_miembro) {
        this.id_miembro = id_miembro;
    }

    public String getCorreo_miembro() {
        return correo_miembro;
    }

    public void setCorreo_miembro(String correo_miembro) {
        this.correo_miembro = correo_miembro;
    }

    public String getContrasena_miembro() {
        return contrasena_miembro;
    }

    public void setContrasena_miembro(String contrasena_miembro) {
        this.contrasena_miembro = contrasena_miembro;
    }*/
}
