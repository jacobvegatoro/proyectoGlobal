package com.jacobvega.proyectoglobal.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jacobvega.proyectoglobal.modelo.Phone;
import com.jacobvega.proyectoglobal.servicio.PhoneService;

@RestController
@RequestMapping("/api/v1/phones")
public class PhoneController {

	private PhoneService phoneService;
	
	@Autowired
	public PhoneController(PhoneService phoneService) {
		this.phoneService = phoneService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Phone> listado(){
		return phoneService.listado();
	}
	
}
