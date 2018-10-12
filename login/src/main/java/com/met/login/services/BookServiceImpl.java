package com.met.login.services;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.met.login.entities.Book;
import com.met.login.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;	






	@Override
	public Book getBookById(BigInteger id) {
	return bookRepository.findById(id).get();
	}



	@Override
	public void saveOrUpdate(Book book) {
	bookRepository.save(book);
		
	}



	@Override
	public void deleteBook(BigInteger id) {
	bookRepository.deleteById(id);
		
	}










}
