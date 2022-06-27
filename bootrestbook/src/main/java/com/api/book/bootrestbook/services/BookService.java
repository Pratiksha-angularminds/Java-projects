package com.api.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.models.Book;

@Component
public class BookService 
{
    private static List<Book> list = new ArrayList<>();

    static
    {
        list.add(new Book(2,"Think java","ABC"));
        list.add(new Book(3,"Mann main hai vishwas","Vishwas patil"));
        list.add(new Book(4,"Wings of fire","DR.APJ Kalam"));
    }

    //----------------------get All Books----------------------------------
    public List<Book> getAllBooks()
    {
        return list;
    }
    
    //------------------------get single book by id--------------------------
    public Book getBookById(int id)
    {
        Book book = null;
        book = list.stream().filter(e->e.getId()==id).findFirst().get();
        return book;
    }

    //--------------------------Add new Book--------------------------------
    public Book addBook(Book book)
    {
        list.add(book);
        return book;
    }

    //-------------------------Delete book by id----------------------------
    public List<Book> deleteBook(int bid) 
    {
        list = list.stream().filter(book -> book.getId() != bid).collect(Collectors.toList());
        return list;
    }

    public List<Book> updateBook(Book book,int bid)
    {
        list = list.stream().map( b ->
            {
                if(b.getId() == bid)
                {
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthor());
                }
                return b;
            }).collect(Collectors.toList());

        return list;
    }
}
