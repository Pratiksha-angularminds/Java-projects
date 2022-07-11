package com.pratiksha.authentication.models;


import java.security.Provider;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class UserModel 
{
    @Id
    private String id;
    private String email;
    private String password;
    private String file;
    private List<FileModel> multiplefile;
    private Provider provider;


    public UserModel() {
    }

    

    public UserModel(String id, String email, String password, String file, List<FileModel> multiplefile) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.file = file;
        this.multiplefile = multiplefile;
    }



    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String upload) {
        this.file = upload;
    }

    public List<FileModel> getMultiplefile() {
        return multiplefile;
    }

    public void setMultiplefile(List<FileModel> multiplefile) {
        this.multiplefile = multiplefile;
    }



    public Provider getProvider() {
        return provider;
    }



    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    
}
