package com.jacobvega.proyectoglobal.servicio;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jacobvega.proyectoglobal.dto.TokenDTO;
import com.jacobvega.proyectoglobal.dto.UserDTO;
import com.jacobvega.proyectoglobal.modelo.Role;
import com.jacobvega.proyectoglobal.modelo.User;
import com.jacobvega.proyectoglobal.repositorio.UserRepository;
import com.jacobvega.proyectoglobal.seguridad.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public List<User> obtener() {
		return userRepository.findAll();
	}

	@Override
	public TokenDTO crear(UserDTO usuario) {
		
		boolean existe = userRepository.existsByEmail(usuario.getEmail());
		Long usuarioexiste = -1L;
		if (!existe) {
			User us = new User();
			us.setEmail(usuario.getEmail());
			us.setName(usuario.getName());
			us.setPassword(passwordEncoder.encode(usuario.getPassword()));

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			us.setCreated(dtf.format(LocalDateTime.now()));
			us.setLastLogin(dtf.format(LocalDateTime.now()));
			us.setModified(dtf.format(LocalDateTime.now()));
			us.setIsactive(1);
			
			User newusuario = userRepository.saveAndFlush(us);
			TokenDTO resp = new TokenDTO();
			resp.setId(newusuario.getId());
			List<Role> roles = new ArrayList<Role>();
			roles.add(Role.ROLE_ADMIN);
			resp.setToken(jwtTokenProvider.createToken(usuario.getEmail(), roles));
			return resp;
		}
		else {
			TokenDTO resp = new TokenDTO();
			resp.setId(usuarioexiste);
			resp.setToken("");
			return resp;
		}

	}

	@Override
	public UserDetails loadUserByEmail(String email) {
		final User user = userRepository.findByEmail(email);
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.ROLE_ADMIN);
		
		if (user == null) {
			throw new UsernameNotFoundException("Usuario '" + email + "' no encontrado");
		}
		return org.springframework.security.core.userdetails.User
				.withUsername(email)
				.password(user.getPassword())
				.authorities(roles)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}

}
