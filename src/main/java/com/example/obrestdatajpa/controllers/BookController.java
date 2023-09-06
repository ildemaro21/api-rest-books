package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    
    
    //CRUD
    //Crear un Libro
    
    
    //Actualizar un Libro
    
    
    //Borrar un Libro
    
    
    //Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    
    //Buscar un libro segun ID
    
    

}
