package com.ing.bank.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ing.bank.entity.JwtUser;
import com.ing.bank.repository.JwtUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private JwtUserRepository jwtUserRepository;

	public JwtUserDetailsService(JwtUserRepository applicationUserRepository) {
		this.jwtUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtUser applicationUser = jwtUserRepository.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), new ArrayList<>());
	}
}
