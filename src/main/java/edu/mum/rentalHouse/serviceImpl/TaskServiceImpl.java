package edu.mum.rentalHouse.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.rentalHouse.model.Task;
import edu.mum.rentalHouse.model.User;
import edu.mum.rentalHouse.repository.TaskRepository;
import edu.mum.rentalHouse.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskRepository taskRepository;
	
	public void addTask(Task task, User user) {
	task.setUser(user);	
	taskRepository.save(task);
	}
	
	public List<Task> findUserTask(User user){
		
		return taskRepository.findByUser(user);
	}
	
}
