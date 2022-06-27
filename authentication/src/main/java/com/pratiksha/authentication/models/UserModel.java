package com.pratiksha.authentication.models;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
public class UserModel 
{
    @Id
    private String id;
    private String username;
    private String password;
    private String file;
    private List<FileModel> multiplefile;
    
    public UserModel() {
    }

    

    public UserModel(String id, String username, String password, String file, List<FileModel> multiplefile) {
        this.id = id;
        this.username = username;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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

}
