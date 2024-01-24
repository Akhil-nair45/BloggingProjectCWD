package com.api.blog.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.Payload.UserDTO;
import com.api.blog.Payloads.ApiResponse;
import com.api.blog.ServiceImpl.UserServiceImpl;
import com.api.blog.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService us;
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO ud){
		return new ResponseEntity<>(us.AddUser(ud),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO udo , @PathVariable Integer id){
		return new ResponseEntity<>(us.updateUser(udo,id),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
		 this.us.deleteUser(id);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully!!",true), HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return new ResponseEntity<>(us.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
		return new ResponseEntity<>(us.findById(id), HttpStatus.OK);
	}
	
	
	
}
