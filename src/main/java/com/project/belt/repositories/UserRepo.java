package com.project.belt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.belt.models.Subscription;
import com.project.belt.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	List<User> findAll();
	User findByUsername(String username);
	boolean existsByUsername(String username);
	User findById(Long id);
	List<User> findByLevel(int level);
	List<User> findBySubscription(Subscription sub);
}
