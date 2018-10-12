package com.met.login.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.met.login.entities.Book;
@Repository
public interface BookRepository  extends JpaRepository<Book, BigInteger>{


}
