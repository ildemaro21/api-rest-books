package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookCOntrollerTest {
    
    private TestRestTemplate testRestTemplate;
    
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    
    @LocalServerPort
    private int port;
    
    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    
    @Test
    void findAllTest(){
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        
        List<Book> books = Arrays.asList(response.getBody());
    
    }
    
    @Test
    void findByIdTest(){
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/books/1", Book.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    
    }
    
    @Test
    void createTest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        String json = """
                      {
                          "title": "Libro Nuevo Spring Test",
                          "author": "Leonardo Da Vinci",
                          "pages": 650,
                          "price": 19.99,
                          "releaseDate": "1977-10-16",
                          "online": true
                      }
                      """;
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);
        Book book = response.getBody();
        
        assertEquals(1L, book.getId());
        assertEquals("Libro Nuevo Spring Test", book.getTitle());
        
    }

}
