package com.jacobvega.proyectoglobal.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.jacobvega.proyectoglobal.dto.TokenDTO;
import com.jacobvega.proyectoglobal.dto.UserDTO;
import com.jacobvega.proyectoglobal.modelo.User;

public interface UserService {

	List<User> obtener();
	
	TokenDTO crear(UserDTO usuario);
	
	UserDetails loadUserByEmail(String email);
	
	//String signin(String email, String password);
	
}
