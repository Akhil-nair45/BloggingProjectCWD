package com.api.blog.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.api.blog.Entity.User;
import com.api.blog.Payload.UserDTO;

public interface UserService {

	public UserDTO AddUser(UserDTO userdto);
	public List<UserDTO> findAll();
	public UserDTO findById(Integer id);
	public void deleteUser(Integer id);
	public UserDTO updateUser(UserDTO userdto, Integer id);
}
