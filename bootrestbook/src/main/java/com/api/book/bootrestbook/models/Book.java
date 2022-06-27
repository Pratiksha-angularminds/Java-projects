package com.api.book.bootrestbook.models;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;

// @Entity
// @Table(name="Books")
public class Book 
{
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO) //to increment id automatically
    // @Column(name="book_id") // to change the column name
    private int id;
    private String title,author;

    public Book(int id, String title, String author) 
    {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Book() 
    {

    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getAuthor() 
    {
        return author;
    }

    @Override
    public String toString() 
    {
        return "Book [author=" + author + ", id=" + id + ", title=" + title + "]";
    }

    public void setAuthor(String author) 
    {
        this.author = author;
    }    



}
