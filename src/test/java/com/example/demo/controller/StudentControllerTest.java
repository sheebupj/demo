package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.Studentservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Studentservice studentservice;

    public static String asJsonString(final Object obj) {
        try {
            // Uses Jackson's ObjectMapper to serialize the object to a String
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            // Throw a runtime exception if serialization fails
            throw new RuntimeException(e);
        }
    }
    @Test
    void getStudentById_shouldReturn200()throws Exception{
        StudentDTO mockStudentDTO= new StudentDTO(1,"sheebu",11,"paremal");
        Optional<StudentDTO> optDto=Optional.of(mockStudentDTO);
        when(studentservice.getStudent(1)).thenReturn(optDto);
        mockMvc.perform(get("/students/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("sheebu"))
                .andExpect(jsonPath("$.age").value(11))
                .andExpect(jsonPath("$.address").value("paremal"));;

    }
    @Test
    void saveStudentReturn201()throws Exception{
        StudentDTO inputStudentDTO= new StudentDTO(null,"sheebu",11,"paremal");
        StudentDTO createdStudentDTO= new StudentDTO(1,"sheebu",11,"paremal");
        Optional<StudentDTO> optDto=Optional.of(createdStudentDTO);
        when(studentservice.saveStudent(any(StudentDTO.class))).thenReturn(createdStudentDTO);
        mockMvc.perform(post("/saveStudents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputStudentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("sheebu"))
                .andExpect(jsonPath("$.age").value(11))
                .andExpect(jsonPath("$.address").value("paremal"));

    }
}
