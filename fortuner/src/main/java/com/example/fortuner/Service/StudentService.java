package com.example.fortuner.Service;

import com.example.fortuner.Model.Student;
import com.example.fortuner.Repository.StudenRepository;
import com.example.fortuner.dbo.request.StudentcreateRequest;
import com.example.fortuner.dbo.request.Studentupdaterequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudenRepository studenRepository;

    public Student createRequest(StudentcreateRequest request) {
        Student student = new Student();
        student.setTen(request.getTen());
        student.setDiem(request.getDiem());
        return studenRepository.save(student); // Use the instance variable instead of the class name
    }
    public  List<Student> getStudent (){
        return studenRepository.findAll();
    }
    public Student getstudent(String id){
        return studenRepository.findById(id).orElseThrow(() -> new RuntimeException("Student khong ton tai"));
    }
    public Student updatestudent(String studentid, Studentupdaterequest request){
       Student student =  getstudent(studentid);
       student.setTen(request.getTen());
       student.setDiem(request.getDiem());

       return studenRepository.save(student);
    }
    public void deletestudent(String studentid){
        studenRepository.deleteById(studentid);
    }
    public List<Student> searchStudentsByName(String name) {
        return studenRepository.findByTenContainingIgnoreCase(name);
    }
}