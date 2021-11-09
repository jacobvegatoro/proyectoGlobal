package com.jacobvega.proyectoglobal.controlador;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jacobvega.proyectoglobal.dto.GenericDTO;
import com.jacobvega.proyectoglobal.dto.TokenDTO;
import com.jacobvega.proyectoglobal.dto.UserDTO;
import com.jacobvega.proyectoglobal.modelo.User;
import com.jacobvega.proyectoglobal.servicio.PhoneService;
import com.jacobvega.proyectoglobal.servicio.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private UserService userService;
	private PhoneService phoneService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserController(UserService userService, PhoneService phoneService) {
		this.userService = userService;
		this.phoneService = phoneService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<User> listado(){
		return userService.obtener();
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<GenericDTO> crear(@RequestBody UserDTO usuario) {
		
		logger.debug("Inicia creación de usuario");
		
		Pattern patcorreo = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
		Matcher matcorreo = patcorreo.matcher(usuario.getEmail());
		String tokenobtenido = "";
				
		if (matcorreo.matches()) {
			
			Pattern patclave = Pattern.compile("[A-Z]{1}[a-z]+[0-9]{2}");
			Matcher matclave = patclave.matcher(usuario.getPassword());
			
			if (matclave.matches()) {
				TokenDTO usertoken = userService.crear(usuario);
				if (usertoken.getId() > 0) {
					tokenobtenido = usertoken.getToken();
					logger.info("El usuario ha sido creado. Token de usuario: " + usertoken.getToken());
					phoneService.agregarLista(usuario.getPhones(), usertoken.getId());
					logger.info("Se han agregado los teléfonos del usuario");
				}
				else if (usertoken.getId() == -1) {
					logger.error("El correo ya está registrado");
					GenericDTO resp = new GenericDTO();
					resp.setCodigo("500");
					resp.setMensaje("El correo ya está registrado");
					return new ResponseEntity<GenericDTO>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				logger.error("La clave debe tener una letra mayúscula, dos números y el resto solo letras minúsculas");
				GenericDTO resp = new GenericDTO();
				resp.setCodigo("500");
				resp.setMensaje("La clave debe tener una letra mayúscula, dos números y el resto solo letras minúsculas");
				return new ResponseEntity<GenericDTO>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		} else {
			logger.error("El correo no tiene un formato correcto");
			GenericDTO resp = new GenericDTO();
			resp.setCodigo("500");
			resp.setMensaje("El correo no tiene un formato correcto");
			return new ResponseEntity<GenericDTO>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		GenericDTO resp = new GenericDTO();
		resp.setCodigo("201");
		resp.setMensaje("Usuario creado. Token obtenido: " + tokenobtenido);
		logger.debug("Fin creación de usuario");
		return new ResponseEntity<GenericDTO>(resp,HttpStatus.CREATED);

	}

	/*@PostMapping("/obtener")
	public ResponseEntity<GenericDTO> login(@RequestParam String email, @RequestParam String password) {
		String tokengenerado = userService.signin(email, password);
		GenericDTO resp = new GenericDTO();
		resp.setCodigo("200");
		resp.setMensaje("Se ha reestablecido el token del usuario " + email + ". Nuevo token: " + tokengenerado);
		return new ResponseEntity<GenericDTO>(resp,HttpStatus.OK);		
	}*/

}
