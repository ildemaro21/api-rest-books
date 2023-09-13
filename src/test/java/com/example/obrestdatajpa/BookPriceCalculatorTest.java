package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.services.BookPriceCalculator;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookPriceCalculatorTest {

    @Test
    void calculatePriceTest(){
        //Configuracion de la prueba
        Book book = new Book(1L, "El Sr de los anillos", "Autor", 1000, 49.99, LocalDate.now(), true); 
        BookPriceCalculator calculator = new BookPriceCalculator();
        
        //Ejecutar el metodo a testear
        double price = calculator.calculatePrice(book);
        
        //Assertions
        assertTrue(price>0);
    }
}
