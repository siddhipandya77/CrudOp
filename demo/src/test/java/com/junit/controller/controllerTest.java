package com.junit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.model.Books;
import com.junit.services.BooksService;

public class controllerTest {

	@Mock
	BooksService booksService;
	
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext context;

	ObjectMapper objectMapper = new ObjectMapper();
	
	@InjectMocks
	BooksController booksController;
	Books book;
	List<Books> list;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(booksController).build();
		book = getBook();
		list=getBookList();
	
	}
	
	@Test
	public void saveBook_test() {
		when(booksService.saveOrUpdate(Mockito.any(Books.class))).thenReturn(book);
		Books expectedOutput = booksController.saveBook(book);
		assertEquals(book.toString(), expectedOutput.toString());

	}
	
	@Test
	public void saveBookTest() throws Exception {
		String jsonRequest = objectMapper.writeValueAsString(book);
		when(booksService.saveOrUpdate(Mockito.any(Books.class))).thenReturn(book);
		MvcResult mvcResult = mockMvc
				.perform(post("/saveBook").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Books expectedOutputUser = objectMapper.readValue(expectedOutput, Books.class);
		assertEquals(expectedOutputUser.getPrice(), book.getPrice());
	}
	@Test
	public void updateBook_test()
	{
		when(booksService.update(book, book.getBookid())).thenReturn(book);
		Books expectedOutput = booksController.update(book);
		assertEquals(book.toString(),expectedOutput.toString());
		
		
	}
	@Test
	public void updateBookTest() throws Exception {
		String jsonRequest = objectMapper.writeValueAsString(book);
		when(booksService.update(Mockito.any(Books.class),Mockito.anyInt())).thenReturn(book);
		MvcResult mvcResult = mockMvc
				.perform(put("/updateBook").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Books expectedOutputUser = objectMapper.readValue(expectedOutput, Books.class);
		assertEquals(expectedOutputUser.getPrice(), book.getPrice());
	}
	@Test
	public void deleteBook_test()
	{
		when(booksService.getBooksById(Mockito.anyInt())).thenReturn(book);
		int id = booksController.deleteBook(book.getBookid());
		assertEquals(book.getBookid(), id);
		
	}
	
	@Test
	public void deleteBookTest() throws Exception {
		String jsonRequest = String.valueOf(book.getBookid());
		when(booksService.getBooksById(Mockito.anyInt())).thenReturn(book);
		MvcResult mvcResult = mockMvc
			.perform(delete("/deleteBook/{bookid}",jsonRequest))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		int id=Integer.parseInt(expectedOutput);
		//Books expectedOutputUser = objectMapper.readValue(expectedOutput, Books.class);
		assertEquals(id, book.getBookid());
		
		
		
		
	}
	
	@Test
	public void getBookById_test()
	{
		when(booksService.getBooksById(Mockito.anyInt())).thenReturn(book);
		Books actualOutput =booksController.getBooks(book.getBookid());
		assertEquals(actualOutput.toString(), book.toString());
		
	}
	
	@Test
	public void getBookByIdTest() throws Exception {
		String jsonRequest = String.valueOf(book.getBookid());
		when(booksService.getBooksById(Mockito.anyInt())).thenReturn(book);
		MvcResult mvcResult = mockMvc
			.perform(get("/getBookById/{bookid}",jsonRequest))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Books expectedOutputUser = objectMapper.readValue(expectedOutput, Books.class);
		assertEquals(expectedOutputUser.getPrice(), book.getPrice());
	}
	@Test
	public void getAllBooks_Test()
	{
		when(booksService.getAllBooks()).thenReturn(list);
		List<Books> actualUserList = booksController.getAllBooks();
		assertEquals(actualUserList.size(), list.size());
		
	}
	
	@Test
	public void getAllBooksTest() throws Exception {
		when(booksService.update(Mockito.any(Books.class),Mockito.anyInt())).thenReturn(book);
		MvcResult mvcResult = mockMvc
				.perform(get("/getAllBooks"))
				.andExpect(status().isOk()).andReturn();
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		//String expectedOutput = mvcResult.getResponse().getContentAsString();
		//Books[] bookList = super.mapFromJson(expectedOutput, Books[].class);
		//assertEquals(bookList.length()>0);
	
	
	}
	public Books getBook()
	{
		Books book=new Books();
		book.setBookid(55);
		book.setBookname("hhh");
		book.setAuthor("jjj");
		book.setPrice(90);
		return book;
	}
	public List<Books> getBookList()
	{
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
	
}
