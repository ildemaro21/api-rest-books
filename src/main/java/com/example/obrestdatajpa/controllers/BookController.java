package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent())
            return ResponseEntity.ok(book.get());
        else
            return ResponseEntity.notFound().build();
    }
    /*
    OTRA FORMA
    return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()
    */

}
