package edu.mum.rentalHouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.rentalHouse.model.Task;
import edu.mum.rentalHouse.model.User;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long>{

	List<Task> findByUser(User user);

}
