package com.project.belt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.belt.models.User;
import com.project.belt.repositories.UserRepo;

@Service
public class UserAuthService implements UserDetailsService {
	private UserRepo repo;
	
	public UserAuthService(UserRepo repo) {
		this.repo = repo;
	}
	
	@Override
	// finds user; if found, returns it with correct authorities; else, throw error
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
	}
	
	// returns list of authorities for a user and creates Authority object
	private List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(user.getLevel() > 1) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		// if(user.getLevel() > 2) {
		// 	authorities.add(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
		// }
		return authorities;
	}
	
}