package com.junit.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.junit.model.Books;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class BooksRepositoryTest {

	@Autowired  
	BooksRepository booksRepository; 

	   
	    @Test
	   @Rollback(value = false)
	    public void saveBookTest(){

	        Books book = new Books(11,"DSA","XYZ",200);

	        booksRepository.save(book);

	        Assertions.assertThat(book.getBookid()).isGreaterThan(0);
	    }
	    
	    @Test
	    public void getBookTest(){

	        Books book = booksRepository.findById(11);

	        Assertions.assertThat(book.getBookid()).isEqualTo(11);

	    }
	    @Test
	    public void getAllBooksTest(){

	        List<Books> employees = (List<Books>) booksRepository.findAll();

	        Assertions.assertThat(employees.size()).isGreaterThan(0);

	    }
	    
	    @Test
	    public void updateBookTest()
	    {
	    	Books book=booksRepository.findById(11);
	    	book.setPrice(400);
	    	Books bookUpdate= booksRepository.save(book);
	    	assertThat(bookUpdate.getPrice()).isEqualTo(400);
	    }

	    @Test
	    @Rollback(value = false)
	    public void deleteBookTest(){

	        Books book = booksRepository.findById(11);
	        booksRepository.delete(book);
	        //booksRepository.deleteById(11);

	        Books book1 = null;

	        Optional<Books> optionalbooks = Optional.ofNullable(booksRepository.findById(11));

	      

	        Assertions.assertThat(book1).isNull();
	    }

}
