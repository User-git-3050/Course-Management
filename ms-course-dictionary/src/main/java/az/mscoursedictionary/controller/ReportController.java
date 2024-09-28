package az.mscoursedictionary.controller;

import az.mscoursedictionary.dao.CourseReport;
import az.mscoursedictionary.dao.InstructorReport;
import az.mscoursedictionary.dao.InstructorResponse;
import az.mscoursedictionary.dao.StudentReport;
import az.mscoursedictionary.service.ReportDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/reports/dictionary")
public class ReportController {

    private final ReportDictionaryService reportDictionaryService;

    @Autowired
    public ReportController(ReportDictionaryService reportDictionaryService) {
        this.reportDictionaryService = reportDictionaryService;
    }

    @GetMapping("courses")
    public ResponseEntity<String> getCourseReport(@RequestParam Long courseId,@RequestParam String username){
        return ResponseEntity.ok(reportDictionaryService.getCourseReport(courseId,username));
    }

    @GetMapping("students")
    public ResponseEntity<String> getStudentReport(@RequestParam String studentName,@RequestParam String username){
        return ResponseEntity.ok(reportDictionaryService.getStudentReport(studentName,username));
    }

    @GetMapping("instructors")
    public ResponseEntity<String> getInstructorReport(@RequestParam String instructorName){
        return ResponseEntity.ok(reportDictionaryService.getInstructorReport(instructorName));
    }

    @GetMapping("courses/all")
    public ResponseEntity<List<String>> getAllCourseReport(){
        return ResponseEntity.ok(reportDictionaryService.getAllCourseReports());
    }

    @GetMapping("students/all")
    public ResponseEntity<List<String>> getAllStudentReport(){
        return ResponseEntity.ok(reportDictionaryService.getAllStudentReports());
    }

    @GetMapping("instructors/all")
    public ResponseEntity<List<String>> getAllInstructorReport(){
        return ResponseEntity.ok(reportDictionaryService.getAllInstructorReports());
    }

}
