package com.example.fortuner.Controller.API;

import com.example.fortuner.Model.Student;
import com.example.fortuner.Service.StudentService;
import com.example.fortuner.dbo.request.StudentcreateRequest;
import com.example.fortuner.dbo.request.Studentupdaterequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentAPicontroller {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    Student createstudent (@RequestBody StudentcreateRequest request){
        return  studentService.createRequest(request);
    }
    @GetMapping
    List<Student> getstudent(){
        return studentService.getStudent();
    }
    @GetMapping("/{studentid}")
    Student getnhac(@PathVariable("studentid") String studentid){
        return  studentService.getstudent(studentid);

    }
    @PutMapping("/{studentid}")
    Student updateStudent(@PathVariable String studentid, @RequestBody Studentupdaterequest request){
        return  studentService.updatestudent(studentid,request);
    }
    @DeleteMapping("/{studentid}")
    String deleteStudent(@PathVariable String studentid){
        studentService.deletestudent(studentid);
        return "da xoa";
    }
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudentsByName(@RequestParam String name) {
        List<Student> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok().body(students);
    }
}
