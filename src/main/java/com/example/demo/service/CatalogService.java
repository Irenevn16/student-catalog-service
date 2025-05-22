package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.repository.CatalogRepository;
import com.example.demo.repository.StudentGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    public ResponseEntity<?> getCatalogByCourseCode(String courseCode) {
        ResponseEntity<GradeDTO[]> gradeResponse = restTemplate.getForEntity(
                "http://grades-data-service/api/grade/" + courseCode,
                GradeDTO[].class
        );
        GradeDTO[] gradeArray = gradeResponse.getBody();
        if (gradeArray == null || gradeArray.length == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<StudentGradeDTO> studentGrades = new ArrayList<>();
        for (GradeDTO grade : gradeArray) {
            ResponseEntity<StudentDTO> studentResponse = restTemplate.getForEntity(
                    "http://student-info-service/api/students/" + grade.getStudentId(),
                    StudentDTO.class
            );
            StudentDTO student = studentResponse.getBody();
            if (student != null) {
                StudentGradeDTO dto = new StudentGradeDTO();
                dto.setStudentName(student.getStudentName());
                dto.setStudentAge(student.getStudentAge());
                dto.setGrade(grade.getGrade());
                studentGrades.add(dto);
            }
        }
            ResponseEntity<CourseDTO> courseResponse = restTemplate.getForEntity(
                    "http://grades-data-service/api/grades" + courseCode,
                    CourseDTO.class
            );

            CourseDTO courseDTO = courseResponse.getBody();
            if (courseDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


            CatalogDTO catalog = new CatalogDTO();
            catalog.setCourseName(courseDTO.getCourseName());
            catalog.setStudentGrades(studentGrades);

            return new ResponseEntity<>(catalog, HttpStatus.OK);
        }
    }

