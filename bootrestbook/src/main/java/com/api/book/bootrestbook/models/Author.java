package com.api.book.bootrestbook.models;

public class Author 
{
    private int authorId;
    private String name;
    private String language;

    public Author() 
    {
    }

    public Author(int authorId, String name, String language) 
    {
        this.authorId = authorId;
        this.name = name;
        this.language = language;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    

    
}
