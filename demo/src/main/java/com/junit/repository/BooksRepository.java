package com.junit.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;  
import com.junit.model.Books;  
//repository that extends CrudRepository  
public interface BooksRepository extends CrudRepository<Books, Integer>  
{

	//Optional<Books> findByPrice(int i);  
	Books findById(int i);  
} 
