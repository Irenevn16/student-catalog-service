package com.example.demo.client;


import com.example.demo.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient("student-info-service")
public interface StudentInfoService {

    @GetMapping("/api/students/{id}")
    ResponseEntity<?> getStudentById (@PathVariable Long studentId);


    }


