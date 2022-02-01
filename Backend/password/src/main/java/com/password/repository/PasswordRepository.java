package com.password.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.password.entity.Password;

public interface PasswordRepository extends JpaRepository<Password, Long>{
	
	@Query("FROM Password as u where u.user.id =:id")
	List<Password> findPasswordByUser(@Param("id") long id);

}
