package com.viniciusassis.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniciusassis.workshopmongo.domain.User;
import com.viniciusassis.workshopmongo.dto.UserDTO;
import com.viniciusassis.workshopmongo.repository.UserRepository;
import com.viniciusassis.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository rep;
	
	public List<User> findAll(){
		return rep.findAll();
	}
	
	public User findById(String id) {
		Optional <User> user = rep.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return rep.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
