package edu.mum.rentalHouse.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.mum.rentalHouse.model.Role;
import edu.mum.rentalHouse.model.User;
import edu.mum.rentalHouse.repository.UserRepository;
import edu.mum.rentalHouse.service.UserService;


@Service
public class UserServiceImpl implements UserService{

@Autowired
private UserRepository userRepository;

public void createUser(User user) {
BCryptPasswordEncoder bpeBCryptPasswordEncoder=new BCryptPasswordEncoder();
user.setPassword(bpeBCryptPasswordEncoder.encode(user.getPassword()));
Role userRole=new Role("USER");
List<Role> roles=new ArrayList<Role>();
roles.add(userRole);
user.setRoles(roles);
userRepository.save(user);
}

public void createAdmin(User user) {
BCryptPasswordEncoder bpeBCryptPasswordEncoder=new BCryptPasswordEncoder();
user.setPassword(bpeBCryptPasswordEncoder.encode(user.getPassword()));
Role userRole=new Role("ADMIN");
List<Role> roles=new ArrayList<Role>();
roles.add(userRole);
user.setRoles(roles);
userRepository.save(user);
}

public User findById(String email) {
	
	return userRepository.findById(email).get();
}


public boolean isUserPresent(String email) {
	//User user=userRepository.findById(email).get();
	User user= userRepository.findByEmail(email);

	if(user!=null) return true;
	return false;
}

public List<User> findAll() {
	
 return (List<User>) userRepository.findAll();

}

public List<User> findByName(String name) {
	// TODO Auto-generated method stub
	return  userRepository.findByNameLike("%"+name+"%");
}

public User findByEmail(String email) {
	//return  userRepository.findById(email).get();
	return  userRepository.findByEmail(email);

}

}
