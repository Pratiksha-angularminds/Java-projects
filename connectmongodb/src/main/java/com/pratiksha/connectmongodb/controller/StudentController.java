package com.pratiksha.connectmongodb.controller;



import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pratiksha.connectmongodb.models.Student;
import com.pratiksha.connectmongodb.repo.StudentRepository;

@RestController
// @RequestMapping("/student")
public class StudentController 
{
    @Autowired
    StudentRepository studentRepository;

    // inject via application.properties
    // @Value("${welcome.message}")
    // private String message = "Hello World";

    @GetMapping("/home")
    public String index(Model model) 
    {
        // model.addAttribute("greeting", "hello world");
        return "home";
    }
    
    @GetMapping("/student")
    public ResponseEntity<?> getAllStudents()
    {
        List<Student> student = studentRepository.findAll();
        if(student.size() >0)
        {
            return ResponseEntity.ok(student);
        }
        else
        {
            return new ResponseEntity<>("No student available",HttpStatus.NOT_FOUND);
        }
        
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") String id)
    {
        Optional<Student> student = studentRepository.findById(id);
       
        if(student.isEmpty())
        {
            return new ResponseEntity<>("Student not found with this id",HttpStatus.NOT_FOUND);
        }
        else
        {
            return ResponseEntity.ok(student); 
        }
        
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student st)
    {
        // try
        // {
            // st.setDob(new Date(System.currentTimeMillis())); //-----------for date
            Student save = this.studentRepository.save(st);
            return ResponseEntity.ok(save);
        // }catch(Exception e)
        // {
        //     e.printStackTrace();
        //     return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        // }   
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") String id , @RequestBody Student st)
    {
        Optional<Student> studentDB = studentRepository.findById(id);
       
        if(studentDB.isEmpty())
        {
            return new ResponseEntity<>("please enter id of student you want to update",HttpStatus.BAD_REQUEST);
        }
        else
        {
            Student studentUpdate = studentDB.get();
            if(st.getName() != null)
                studentUpdate.setName(st.getName());

            if(st.getAddress() !=null)
                studentUpdate.setAddress(st.getAddress());
            
            if(st.getCollege() != null)    
                studentUpdate.setCollege(st.getCollege());

            if(st.getFavouriteSub()!= null)
                studentUpdate.setFavouriteSub(st.getFavouriteSub());
                
            studentRepository.save(studentUpdate);
            return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
        }
        
        // return new ResponseEntity<>(studentRepository.save(st), HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") String id)
    {
        try
        {
            // st.setDob(new Date(System.currentTimeMillis())); //-----------for date
            studentRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
            
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }   
    }
}
