package com.ing.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.bank.entity.JwtUser;

@Repository
public interface JwtUserRepository extends JpaRepository<JwtUser, Long> {

	public JwtUser findByUsername(String username);
}
