package com.pratiksha.socialfeed.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@Data
@AllArgsConstructor
public class FileModel 
{
    private String name;
    private String url;
}
