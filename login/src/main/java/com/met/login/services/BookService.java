package com.met.login.services;


import java.math.BigInteger;

import org.springframework.stereotype.Service;


import com.met.login.entities.Book;
@Service
public interface BookService {
	
	public Book getBookById(BigInteger Id);

	public void saveOrUpdate(Book book);

	public void deleteBook(BigInteger Id);

}
