package com.junit.controller;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;  
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RestController;  
import com.junit.model.Books;  
import com.junit.services.BooksService;  
  
@RestController  
public class BooksController   
{  
 
@Autowired  
BooksService booksService;  
  
@GetMapping("/getAllBooks")  
public List<Books> getAllBooks()   
{  
return booksService.getAllBooks();  
}  

@GetMapping("/getBookById/{bookid}")  
public Books getBooks(@PathVariable("bookid") int bookid)   
{  
return booksService.getBooksById(bookid);  
}  

@DeleteMapping("/deleteBook/{bookid}")  
public int deleteBook(@PathVariable("bookid") int bookid)   
{  
booksService.delete(bookid);  
return bookid;
}  
 
@PostMapping("/saveBook")  
public Books saveBook(@RequestBody Books books)   
{  
booksService.saveOrUpdate(books);  
return books;  
}  
  
@PutMapping("/updateBook")  
public Books update(@RequestBody Books books)   
{  
booksService.saveOrUpdate(books);  
return books;  
}  
}  
