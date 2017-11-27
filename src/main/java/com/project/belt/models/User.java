package com.project.belt.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {

	@Id @GeneratedValue	private Long id;
	@Column(unique=true)
	@Email
	@Size(min=1)			private String username;
	@Size(min=8)			private String password;
	@Transient				private String passwordConfirmation;
	@Size(min=1)			private String first;
	@Size(min=1)			private String last;
	private int level;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subscription")
	private Subscription subscription;
	@Min(1) @Max(31)
	private int dueDay;
	private Date dueDate;
	private int amtDue;
	private boolean subscribed;
	
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

	public User(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
		this.dueDay = 1;
		this.subscribed = false;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public String getFirst() {
		return first;
	}
	public String getLast() {
		return last;
	}
	public int getLevel() {
		return level;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public int getDueday() {
		return dueDay;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public int getAmtDue() {
		return amtDue;
	}
	public boolean isSubscribed() {
		return subscribed;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public void setDueday(int dueday) {
		this.dueDay = dueday;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public void setAmtDue(int amtDue) {
		this.amtDue = amtDue;
	}
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
	public int getDueDay() {
		return dueDay;
	}
	public void setDueDay(int dueDay) {
		this.dueDay = dueDay;
	}
	
}