package com.example.demo.service;

import com.example.demo.client.GradesDataService;
import com.example.demo.client.StudentInfoService;
import com.example.demo.model.Catalog;
import com.example.demo.model.Grade;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NewCatalogService implements CatalogServiceInterface {

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private GradesDataService gradesDataService;

    public Catalog getCatalog(Long StudentId){
        ResponseEntity<?> student = studentInfoService.getStudentById(StudentId);
        //falta codigo
    }
}
