package com.jacobvega.proyectoglobal.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacobvega.proyectoglobal.dto.PhoneDTO;
import com.jacobvega.proyectoglobal.modelo.Phone;
import com.jacobvega.proyectoglobal.repositorio.PhoneRepository;

@Service
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private PhoneRepository phoneRepository;
	
	@Override
	public List<Phone> listado() {
		return phoneRepository.findAll();
	}

	@Override
	public void agregarLista(List<PhoneDTO> phones, Long userid) {
		for (PhoneDTO phdto:phones) {
			Phone ph = new Phone();
			ph.setNumber(phdto.getNumber());
			ph.setCitycode(phdto.getCitycode());
			ph.setCountrycode(phdto.getCountrycode());
			ph.setUserid(userid);
			phoneRepository.save(ph);
		}
	}
	
}
