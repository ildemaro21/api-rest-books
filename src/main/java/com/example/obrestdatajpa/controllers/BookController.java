package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    
    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    
    
    //CRUD
    //Crear un Libro
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        if(book.getId() != null ){
            log.warn("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
        
    }
    
    
    //Actualizar un Libro
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null ){
            log.warn("trying to update a book that doesn't exist");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())){
            log.warn("trying to update a book that doesn't exist");
            return ResponseEntity.notFound().build();        
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }
    
    //Borrar un Libro
    @DeleteMapping("api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            log.warn("trying to delete a non existent book");
            return ResponseEntity.notFound().build(); 
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    //Borrar todos los libros
    @DeleteMapping("api/books")
    public ResponseEntity<Book> deleteAll(){
        if(bookRepository.count() == 0){
            log.warn("There are no books to delete");
            return ResponseEntity.notFound().build(); 
        }
        log.info("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
    
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
