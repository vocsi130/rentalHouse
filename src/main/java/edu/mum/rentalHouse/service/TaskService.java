package edu.mum.rentalHouse.service;

import java.util.List;
import org.springframework.stereotype.Service;
import edu.mum.rentalHouse.model.Task;
import edu.mum.rentalHouse.model.User;

public interface TaskService {

	
	public void addTask(Task task, User user);
	public List<Task> findUserTask(User user);
	
}
