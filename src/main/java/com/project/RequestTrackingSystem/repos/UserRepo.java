package com.project.RequestTrackingSystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.RequestTrackingSystem.models.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByUserEmail(String email);
	public User findByUserName(String userName);
}
