package edu.mum.rentalHouse.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.rentalHouse.model.Role;
import edu.mum.rentalHouse.repository.RoleRepository;
import edu.mum.rentalHouse.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Role get(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}

}
