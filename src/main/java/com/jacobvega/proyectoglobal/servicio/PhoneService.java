package com.jacobvega.proyectoglobal.servicio;

import java.util.List;

import com.jacobvega.proyectoglobal.dto.PhoneDTO;
import com.jacobvega.proyectoglobal.modelo.Phone;

public interface PhoneService {

	List<Phone> listado();
	void agregarLista(List<PhoneDTO> phones, Long userid);
}
