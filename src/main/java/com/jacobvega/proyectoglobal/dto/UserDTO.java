package com.jacobvega.proyectoglobal.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends GenericDTO {

	private String name;
	
	private String email;
	
	private String password;

	private List<PhoneDTO> phones;
	
}
