package com.jacobvega.proyectoglobal.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacobvega.proyectoglobal.modelo.User;

public interface UserRepository extends JpaRepository<User,Long> {

	boolean existsByEmail(String email);
	User findByEmail(String email);
	
}
