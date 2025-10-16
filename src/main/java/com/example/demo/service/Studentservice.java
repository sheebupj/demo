package com.example.demo.service;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Studentservice {

    @Autowired
    StudentRepository repository;
    @Autowired
    ModelMapper modelMapper;
    public StudentDTO saveStudent(StudentDTO studentdto){
        Student student=modelMapper.map(studentdto,Student.class);
        Student saved=repository.save(student);
        return modelMapper.map(saved,StudentDTO.class);

    }
    public List<StudentDTO> allStudent(){
        List<Student> students=repository.findAll();
       return students.stream().map(s-> modelMapper.map(s,StudentDTO.class)).toList();

    }
    public Optional<StudentDTO> getStudent(Integer id){
        Optional<Student> student=repository.findById(id);
        return student.map(value-> modelMapper.map(value,StudentDTO.class));

    }
    public StudentDTO modifyStudents(Integer id,StudentDTO studentDTO){
        Student existing=repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"+id));
        modelMapper.map(studentDTO,existing);
        existing.setId(id);
        Student updated=repository.save(existing);
        return modelMapper.map(updated,StudentDTO.class);


    }




}
