package com.jacobvega.proyectoglobal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jacobvega.proyectoglobal.controlador.UserController;
import com.jacobvega.proyectoglobal.dto.PhoneDTO;
import com.jacobvega.proyectoglobal.dto.UserDTO;
import com.jacobvega.proyectoglobal.servicio.UserService;

@SpringBootTest
public class ProyectoGlobalApplicationTests {

	@Autowired
	private UserController controller;
	
	@Autowired
	private UserService service;
	
	@Test
	public void validarControlador() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void validarServicio() throws Exception {
		assertThat(service).isNotNull();
	}
	
	@Test
	public void validarCrearUsuario() throws Exception {
		UserDTO usuario = new UserDTO();
		usuario.setName("Alfonso Araya");
		usuario.setEmail("alfonso@correo.cl");
		usuario.setPassword("Alfonso12");

		List<PhoneDTO> telefonos = new ArrayList<PhoneDTO>();
		PhoneDTO fono1 = new PhoneDTO();
		fono1.setCitycode("11");
		fono1.setCountrycode("1");
		fono1.setNumber("111111");
		telefonos.add(fono1);

		PhoneDTO fono2 = new PhoneDTO();
		fono2.setCitycode("22");
		fono2.setCountrycode("2");
		fono2.setNumber("222222");
		telefonos.add(fono2);

		usuario.setPhones(telefonos);
		service.crear(usuario);
		assertThat(service.obtener().size()).isEqualTo(1);
	}

}
