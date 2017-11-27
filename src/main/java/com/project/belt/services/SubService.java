package com.project.belt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.belt.models.Subscription;
import com.project.belt.repositories.SubRepo;

@Service
public class SubService {
	
	private SubRepo repo;
	public SubService(SubRepo repo){
		this.repo = repo;
	}
	
	public Subscription create(Subscription sub) {return repo.save(sub);}

	public Subscription activate(Subscription sub) {
		sub.setActive(true);
		return repo.save(sub);
	}
	public Subscription deactivate(Subscription sub) {
		sub.setActive(false);
		return repo.save(sub);
	}

	public void delete(Subscription sub) {
		repo.delete(sub);
	}

	// Basic retrieval
	public List<Subscription> getAll() {return repo.findAll();}
	public List<Subscription> getActive(boolean b) {return repo.findAllByActive(b);}

	public Subscription get(Long id) {
		return repo.findById(id);
	}

}
