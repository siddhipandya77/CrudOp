package com.junit.services;

import java.util.ArrayList;  
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
import com.junit.model.Books;  
import com.junit.repository.BooksRepository;  
 
@Service  
public class BooksService 
{  
@Autowired  
BooksRepository booksRepository;  
  
public List<Books> getAllBooks()   
{  
List<Books> books = new ArrayList<Books>();  
booksRepository.findAll().forEach(books1 -> books.add(books1));  
return books;  
}  
 
public Books getBooksById(int id)   
{  
return booksRepository.findById(id);  
}  
 
public Books saveOrUpdate(Books books)   
{  
return booksRepository.save(books);  
}  
 
public int delete(int id)   
{  
booksRepository.deleteById(id); 
return id;
}  

public Books update(Books books, int bookid)   
{  
return booksRepository.save(books);  
}  
}  

