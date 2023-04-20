package com.pratiksha.authentication.services;

import com.pratiksha.authentication.models.FileModel;
import com.pratiksha.authentication.models.UserModel;
import com.pratiksha.authentication.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class FileService 
{
    @Autowired
    private UserRepository userRepository;  
    
    public final String UPLOAD_DIR = "/home/am-pc-50/Java/SpringBoot/authentication/src/main/resources/static";
    // public final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();
   

    public FileService() throws IOException
    {

    }

    //-------------------------------------------FOR SINGLE FILE-----------------------------
    public UserModel addFile(MultipartFile multipartFile) throws IOException 
    {

        String filename = Calendar.getInstance().get(Calendar.MILLISECOND) + multipartFile.getOriginalFilename().trim();
        
      
        // String filepath = path +"/" +filename;
        // System.out.println("ji==================================");
        //crete folder if not created
        // File f = new File(path);
        // if(!f.exists())
        // {
        //     f.mkdirs();
        // }

        //file copy
        // Files.copy(multipartFile.getInputStream(), Paths.get(filepath));

        Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR + "/" + filename), StandardCopyOption.REPLACE_EXISTING);

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
       
        String filepath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
        userModel.setFile(filepath);
        userRepository.save(userModel);
        return userModel;
    }


    //-----------------------------------FOR MULTIPLE FILE------------------------------------
    public UserModel addMultipleFile(MultipartFile multipartFiles[]) throws IOException
    {

        List<FileModel> fileModel = new ArrayList<>();


        for(int i=0 ; i<multipartFiles.length ;i++)
        {
            String filename = Calendar.getInstance().get(Calendar.MILLISECOND) + multipartFiles[i].getOriginalFilename().trim();
            String filepath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
            
            
            fileModel.add(new FileModel(multipartFiles[i].getOriginalFilename(),filepath));

            Files.copy(multipartFiles[i].getInputStream(), Paths.get(UPLOAD_DIR + "/" + filename), StandardCopyOption.REPLACE_EXISTING);
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        userModel.setMultiplefile(fileModel);

        userRepository.save(userModel);
        return userModel;
    }
}
