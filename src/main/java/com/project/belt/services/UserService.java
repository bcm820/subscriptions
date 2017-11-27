package com.project.belt.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.belt.models.Subscription;
import com.project.belt.models.User;
import com.project.belt.repositories.UserRepo;

@Service
public class UserService {
	
	private UserRepo ur; private BCryptPasswordEncoder bc;
	public UserService(UserRepo ur, BCryptPasswordEncoder bc) {
		this.ur = ur; this.bc = bc;
	}

	// Create
	public void createAdmin(User user) {
		user.setPassword(bc.encode(user.getPassword()));
		user.setLevel(2); ur.save(user);
	}
	public void createUser(User user) {
		user.setPassword(bc.encode(user.getPassword()));
		user.setLevel(1); ur.save(user);
	}

	// to list customers
	public List<User> getByLevel(int level) {return ur.findByLevel(level);}

	// For authentication
	public User getByUsername(String username) {return ur.findByUsername(username);}

	// For user id
	public User get(Long id) {return ur.findById(id);}
	
	// Get subscribers
	public List<User> getBySub(Subscription sub) {return ur.findBySubscription(sub);} // 1tm

	// Subscribe and set date and date offset
	public void subscribe(User u, Subscription s, int d) {
		u.setDueDay(d);
		u.setSubscription(s);
		u.setSubscribed(true);
		u.setAmtDue(u.getSubscription().getCost());

		Date date = new Date();
		LocalDate newdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = newdate.getYear();
		int month = newdate.getMonthValue();
		int day = u.getDueday();
		if (day < LocalDate.now().getDayOfMonth()) {
			LocalDate realdate = LocalDate.of(year, month, day).plusMonths(1);
			Date duedate = Date.from(realdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			u.setDueDate(duedate);
		} else {
			LocalDate realdate = LocalDate.of(year, month, day);
			Date duedate = Date.from(realdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			u.setDueDate(duedate);
		}
		ur.save(u);
	}

	// Update due date
	public void updateDueDate(User u) {
		Date date = new Date();
		LocalDate newdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate udate = u.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = newdate.plusMonths(1).getMonthValue();
		if (month == udate.getMonthValue()) {
			System.out.println("No change");
		} else {
			int year = newdate.getYear();
			int day = u.getDueday();
			LocalDate dueDate = LocalDate.of(year, month, day); // create new date
			Date duedate = Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			u.setDueDate(duedate);
			ur.save(u);
		}
	}
}
