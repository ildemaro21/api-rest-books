package com.example.obrestdatajpa;

import com.example.obrestdatajpa.repositories.BookRepository;
import com.example.obrestdatajpa.entities.Book;
import java.time.LocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObRestDatajpaApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
        context.getBean(BookRepository.class);
        BookRepository repository = context.getBean(BookRepository.class);
            
        //CRUD
        //crear libro
        Book book = new Book(null, "Habitos Atomicos", "James Clear", 336, 19.99, LocalDate.of(2018, 10, 16), true);  
        Book book2 = new Book(null, "Metodo Silva", "José Silva", 236, 9.99, LocalDate.of(1977, 10, 16), true);
        System.out.println("Numero de libros en BD " + repository.count());
        
        //almacenar libro
        repository.save(book);
        repository.save(book2);
        
        //recuperar los libros
        System.out.println("Numero de libros en BD " + repository.count());
        
        //borrar libro
        //repository.deleteById(1L);
        // System.out.println("Numero de libros en BD " + repository.count());
            
	}

}
