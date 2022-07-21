package com.pratiksha.socialfeed.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pratiksha.socialfeed.models.FileModel;

@Service
public class FileService 
{
    // public final String UPLOAD_DIR = "/home/am-pc-50/Java/SpringBoot/authentication/src/main/resources/static";
    public final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();
   

    public FileService() throws IOException
    {

    }

    //-------------------------------------------FOR SINGLE FILE-----------------------------
    public String addFile(MultipartFile multipartFile) throws IOException 
    {

        String filename = Calendar.getInstance().get(Calendar.MILLISECOND) + multipartFile.getOriginalFilename().trim();
        
        Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR + "/" + filename), StandardCopyOption.REPLACE_EXISTING);

        String filepath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
        
        return filepath;
    }


    //-----------------------------------FOR MULTIPLE FILE------------------------------------
    public List<FileModel> addMultipleFile(MultipartFile multipartFiles[]) throws IOException
    {

        List<FileModel> fileModel = new ArrayList<>();


        for(int i=0 ; i<multipartFiles.length ;i++)
        {
            String filename = Calendar.getInstance().get(Calendar.MILLISECOND) + multipartFiles[i].getOriginalFilename().trim();
            String filepath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
            
            
            fileModel.add(new FileModel(multipartFiles[i].getOriginalFilename(),filepath));

            Files.copy(multipartFiles[i].getInputStream(), Paths.get(UPLOAD_DIR + "/" + filename), StandardCopyOption.REPLACE_EXISTING);
        }

        
        return fileModel;
    }
}
