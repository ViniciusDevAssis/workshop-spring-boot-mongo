package com.viniciusassis.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniciusassis.workshopmongo.domain.Post;
import com.viniciusassis.workshopmongo.repository.PostRepository;
import com.viniciusassis.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository rep;
	
	public List<Post> findAll(){
		return rep.findAll();
	}
	
	public Post findById(String id) {
		Optional <Post> user = rep.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	/*public List<Post> findByTitle(String text){
		return rep.findByTitleContainingIgnoreCase(text);
	}*/
	
	public List<Post> findByTitle(String text){
		return rep.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return rep.fullSearch(text, minDate, maxDate);
	}
}
