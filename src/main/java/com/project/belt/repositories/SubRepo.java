package com.project.belt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.belt.models.Subscription;

@Repository 												
public interface SubRepo extends CrudRepository<Subscription, Long>{
	
	List<Subscription> findAll();
	List<Subscription> findAllByActive(boolean b);
	Subscription findById(Long id);
	
}
