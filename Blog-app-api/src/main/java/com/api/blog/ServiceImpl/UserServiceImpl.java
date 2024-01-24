package com.api.blog.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.blog.Entity.User;
import com.api.blog.Exceptions.ResourceNotFoundException;
import com.api.blog.Payload.UserDTO;
import com.api.blog.Repository.UserRepo;
import com.api.blog.Services.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepo ur;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public UserDTO AddUser(UserDTO userdto) {
		User user = this.dtotoUser(userdto);
		User save = this.ur.save(user);
		return this.usertoDto(save);
	}

	@Override
	public List<UserDTO> findAll() {
		List<User> users = this.ur.findAll();
		List<UserDTO> userDtos = users.stream().map(user->this.usertoDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDTO findById(Integer id) {
		User user = this.ur.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		return this.usertoDto(user);
	}

	@Override
	public void deleteUser(Integer id) {
		User user = this.ur.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		this.ur.delete(user);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer id) {
		User user = this.ur.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User"," id ",id));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		User userupdated = this.ur.save(user);
		UserDTO usertoDto1 = this.usertoDto(userupdated);
		return usertoDto1;
	}
	
	
	private User dtotoUser(UserDTO userdto) {
		User user = this.modelMapper.map(userdto, User.class);
		
		
//		as we implemented model mapper we only have to write the above line rest spring will help in conversion
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		return user;
	}
	
	public UserDTO usertoDto(User user) {
		UserDTO userdto= this.modelMapper.map(user, UserDTO.class);
		
		
		
//		same as above as we are implementing model mapper we are not doing the conversion manually so we are using model mapper as spring will take care of the rest 
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
		return userdto;
	}

	

}
