package com.project.belt.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Subscription {

	@Id @GeneratedValue
	private Long id;
	private String type;
	private int cost;
	private boolean active;

	@OneToMany(mappedBy="subscription", fetch=FetchType.LAZY)
	private List<User> users;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="MM-dd-yyyy HH:mm:ss") private Date createdAt;
	@DateTimeFormat(pattern="MM-dd-yyyy HH:mm:ss") private Date updatedAt;
	@PrePersist public void onCreate(){this.createdAt = new Date();}
	@PreUpdate public void onUpdate(){this.updatedAt = new Date();}
	public Long getId() {return id;}
	public Date getCreatedAt() {return createdAt;}
	public Date getUpdatedAt() {return updatedAt;}
	public void setId(Long id) {this.id = id;}
	public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}
	public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

	public Subscription(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
		this.active = true;
	}
	
	public String getType() {
		return type;
	}
	public int getCost() {
		return cost;
	}
	public boolean isActive() {
		return active;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
