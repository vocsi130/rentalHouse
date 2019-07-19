package edu.mum.rentalHouse.service;

import java.util.List;

import edu.mum.rentalHouse.model.Role;

public interface RoleService {
public List<Role> getRoles();

public Role get(String name);
}
