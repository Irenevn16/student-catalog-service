package com.example.demo.client;

import com.example.demo.model.Grade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient("grades-data-service")
public interface GradesDataService {

    @GetMapping("api/grade")
    List<Grade> getAllGrades(@RequestParam Optional<Long> id);

    @GetMapping("api/grade/{courseCode}")
    ResponseEntity<?> getGradesByCourseCode(@PathVariable String CourseCode);

    @GetMapping("api/course/{courseCode}")
    ResponseEntity<?> getByCourseCode (@PathVariable String CourseCode);
}
