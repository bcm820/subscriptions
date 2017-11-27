package com.project.belt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.project.belt.models.User;
import com.project.belt.repositories.UserRepo;

@Component
public class UserValidator implements Validator {
	
	private UserRepo repo;
	
	public UserValidator(UserRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public boolean supports(Class<?> c) {
		return User.class.equals(c);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		
		if (repo.existsByUsername(user.getUsername())) {
			errors.rejectValue("username", "Exists");
		}
		
		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match");
		}
	}
}
