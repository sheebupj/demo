package com.example.demo.controller;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.Studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    Studentservice service;

    @PostMapping("/saveStudents")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO ){
       StudentDTO savedStudent= service.saveStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> allStudents(){
        List<StudentDTO> allStudents= service.allStudent();
        return ResponseEntity.ok(allStudents);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudents(@PathVariable Integer id){

        return ResponseEntity.of(service.getStudent(id));
    }
    @PutMapping("/modifyStudents/{id}")
    public ResponseEntity<StudentDTO> modifyStudent(@PathVariable Integer id,@RequestBody StudentDTO stuDto){

        StudentDTO updated=service.modifyStudents(id,stuDto);
        return ResponseEntity.ok(updated);
    }

}
