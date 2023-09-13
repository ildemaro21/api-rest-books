package com.example.obrestdatajpa.services;

import com.example.obrestdatajpa.entities.Book;


public class BookPriceCalculator {
    private final double ENVIO = 2.5;
    private final double RECARGO = 5.5;
    
    public double calculatePrice(Book book){
        double price = book.getPrice();
        if (book.getPages()>300)
            price+=RECARGO;
        
        //envio
        price+=ENVIO;
        return price;
    }
    

}
