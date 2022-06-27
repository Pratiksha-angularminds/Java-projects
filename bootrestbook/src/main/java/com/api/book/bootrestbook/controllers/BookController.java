package com.api.book.bootrestbook.controllers;


import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.models.Book;
import com.api.book.bootrestbook.services.BookService;

@RestController
public class BookController 
{
    @Autowired
    private BookService bookService;

    // @RequestMapping(value="/books", method = RequestMethod.GET)
    // @GetMapping("/books")
    // public Book getBooks()
    // {
    //     Book book = new Book();
    //     book.setId(1);
    //     book.setTitle("Java");
    //     book.setAuthor("Pratiksha Sorg.springframework.web.bind.annotation.RequestParamhelke");
    //     return book;
    // }   
    
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> list = bookService.getAllBooks();
        if(list.size() <=0 )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") int id)
    {
        return this.bookService.getBookById(id);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book)
    {
        Book b = this.bookService.addBook(book);
        return b;
    }

    @DeleteMapping("/books/{id}")
    public List<Book> deleteBook(@PathVariable("id") int id)
    {   
        return this.bookService.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<List<Book>> updateBook(@RequestBody Book book,@PathVariable("id") int id) 
    {
        try
        {
            List<Book> list = this.bookService.updateBook(book,id);
            return ResponseEntity.ok().body(list);

        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
            
    }
}
