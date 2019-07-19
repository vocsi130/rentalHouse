package edu.mum.rentalHouse.service;
import java.util.List;


import edu.mum.rentalHouse.model.User;
public interface UserService {

public void createUser(User user) ;

public void createAdmin(User user) ;

public User findById(String email);


public boolean isUserPresent(String email);
public List<User> findAll();

public List<User> findByName(String name);

public User findByEmail(String email) ;
}
