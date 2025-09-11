package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import com.example.demo.Domain.Common.Entity.Lend;
import com.example.demo.Domain.Common.Entity.User;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LendRepositoryTest {

    @Autowired
    private  LendRepository lendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void t1(){
//        List<Lend> list =  lendRepository.findAll();
//        list.forEach(System.out::println);

        //INSERT
//        User user = userRepository.findById("user1").get(); //원래 NULL쳌해야함
//        Book book = bookRepository.findById(1L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);
//
//
    }

    @Test
    public void t2() {
        //user1 도서id 1L대여
        //UserDto에 있는 user1과 BookDto에 있는 1L책을 가져와서 lendDto에 넣는 작업을 하라는건가
        //INSERT
//        User user = userRepository.findById("user1").get(); //원래 NULL쳌해야함
//        Book book = bookRepository.findById(1L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);

        //user1 도서id 2L대여
//        User user = userRepository.findById("user1").get(); //원래 NULL쳌해야함
//        Book book = bookRepository.findById(2L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);

        //user2 도서id 3L대여
//        User user = userRepository.findById("user2").get(); //원래 NULL쳌해야함
//        Book book = bookRepository.findById(3L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);

        //user3 도서id 4L대여
//        User user = userRepository.findById("user3").get(); //원래 NULL쳌해야함
//        Book book = bookRepository.findById(4L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);


    }

    @Test
    public void t3(){
//        User user = userRepository.findById("user1").get();
//        Book book = bookRepository.findById(2L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);

    }
    @Test
    public void t4(){
//        User user = userRepository.findById("user2").get();
//        Book book = bookRepository.findById(3L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);

    }
    @Test
    public void t5(){
//        User user = userRepository.findById("user3").get();
//        Book book = bookRepository.findById(4L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);

    }
//    @Test
//    public void t6(){
//        User user1 = userRepository.findById("user1").get();
//        Book book1 = bookRepository.findById(1L).get();
//        Lend lend1 = new Lend();
//        lend1.setBook(book1);
//        lend1.setUser(user1);
//        lendRepository.save(lend1);
//    }

    @Test
    public void t7(){
        //
//        List<Lend> list = lendRepository.findAllLendsbyUser("user1");
//        list.forEach(System.out::println);
        //
//        List<Lend> list = lendRepository.findAllLendsByBook("가고일");
//        list.forEach(System.out::println);
    }

    @Test
    @Transactional(rollbackFor=Exception.class)
    public void t8(){
        System.out.println("---------------------FETCH TEST START");
        Optional<Lend> lendOptional = lendRepository.findById(11L);
        System.out.println("---------------------lendRepository.findById(1L) invoke! ---------");
        Lend lend = lendOptional.get();
        System.out.println("---------------------lendOptional.get() invoke! ---------");
        User user = lend.getUser();     //point!
        System.out.println(user);
        System.out.println("---------------------lend.getUser() invoke! ---------");
        Book book = lend.getBook();
        System.out.println("---------------------lend.getBook() invoke! ---------");
        System.out.println("---------------------FETCH TEST END");
    }


}