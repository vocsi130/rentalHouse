package edu.mum.rentalHouse.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.rentalHouse.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, String>{

	List<User> findByNameLike(String name);

	User findByEmail(String email);


}
