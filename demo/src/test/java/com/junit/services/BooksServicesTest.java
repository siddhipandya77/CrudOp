package com.junit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.junit.model.Books;
import com.junit.repository.BooksRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BooksServicesTest {

	Books book1;
	Books book2;
	List<Books> bookList;
	
	@Mock
	BooksRepository booksRepository;
	
	@InjectMocks
	BooksService booksService;
	
	
	@BeforeEach
	void setup()
	{
		bookList=getBookList();
		book1=getBook();
		book2=getBook();
	}
	@Test
	public void saveTest()
	{
		
		
		when(booksRepository.save(Mockito.any(Books.class))).thenReturn(book1);
		Books actualoutput = booksService.saveOrUpdate(book1);
		assertEquals(book1.toString(), actualoutput.toString());
		
	}
	@Test
	public void getAllBooksTest()
	{
		when(booksRepository.findAll()).thenReturn(bookList);
		List<Books> actualUserList = booksService.getAllBooks();
		assertEquals(actualUserList.size(), bookList.size());
		
		
	}
	
	 @Test
	 public void deleteTest(){
		 
			when(booksRepository.findById(Mockito.anyInt())).thenReturn(book1);
			int id = booksService.delete(book1.getBookid());
			assertEquals(book1.getBookid(), id);
		 
	
	 }
	 @Test
	 public void getByIdTest()
	 {
		 when(booksRepository.findById(Mockito.anyInt())).thenReturn(book1);
			Books actualOutput =booksService.getBooksById(book1.getBookid());
			assertEquals(actualOutput.toString(), book1.toString());
		
	 }
	 
	 @Test
	 public void updateTest()
	 {
		 when(booksRepository.findById(Mockito.anyInt())).thenReturn(book2);
			when(booksRepository.save(Mockito.any(Books.class))).thenReturn(book1);
			Books actualOutput = booksService.update(book1, book1.getBookid());
			assertEquals(book1.toString(), actualOutput.toString()); 
	    	
	 }
	 
	 
	 private List<Books> getBookList() {
			List<Books> bookList = new ArrayList<>();
			
			Books book1=new Books();
			book1.setBookid(11);
			book1.setBookname("cccc");
			book1.setAuthor("ddd");
			book1.setPrice(100);
			
			Books book2=new Books();
			book2.setBookid(12);
			book2.setBookname("fff");
			book2.setAuthor("eeee");
			book2.setPrice(100);
			

			bookList.add(book1);
			bookList.add(book2);
			return bookList;
	 }
	 
	 private Books getBook() {
			Books book=new Books();
			book.setBookid(11);
			book.setBookname("aaabbb");
			book.setAuthor("bbb");
			book.setPrice(100);
			return book;
		}
}
