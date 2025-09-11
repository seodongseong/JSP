package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //함수명명법
    List<Book> findByBookName(String bookName);

    List<Book> findByPublisher(String publisher);

    List<Book> findByIsbn(String isbn);

    List<Book> findByBookNameAndPublisher(String bookName, String publisher);

    List<Book> findByBookNameContains(String bookName);

}
