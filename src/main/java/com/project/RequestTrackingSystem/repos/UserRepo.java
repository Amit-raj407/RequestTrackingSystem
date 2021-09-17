package com.project.RequestTrackingSystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.RequestTrackingSystem.models.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByUserEmail(String email);
	public User findByUserName(String userName);
	
	
	
//	===================================================================================
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="Update rts.user set user_password = :password where user_email = :email", nativeQuery = true)
	public int updatePassword(@Param("email") String email, @Param("password") String password);
	
//	@Modifying
//	@Query("Update User u set u.userPassword = :password where u.email = :email")
//	public void updatePassword(String email, String password);
	
	
}
