package com.example.fortuner.Controller;

import com.example.fortuner.Model.Student;
import com.example.fortuner.dbo.request.StudentcreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/student-ui")
public class StudentController {
    private static final String API_URL = "http://localhost:8080/student";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/list")
    public String getNhacList(Model model) {
        ResponseEntity<Student[]> responseEntity = restTemplate.getForEntity(API_URL, Student[].class);
        List<Student> Students = Arrays.asList(responseEntity.getBody());
        model.addAttribute("Students", Students);
        return "student-list"; // Tên của tệp HTML trong thư mục templates
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("Student", new Student());
        return "add-student"; // Tên của tệp HTML trong thư mục templates
    }

    @PostMapping("/add")
    public String createstudent(@ModelAttribute StudentcreateRequest request, RedirectAttributes redirectAttributes) {
        HttpEntity<StudentcreateRequest> requestEntity = new HttpEntity<>(request);
        restTemplate.postForObject(API_URL + "/add", requestEntity, Student.class);
        redirectAttributes.addFlashAttribute("message", "Thêm sinh vien thành công");
        return "redirect:/student-ui/list"; // Chuyển hướng về trang danh sách nhạc
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id, RedirectAttributes redirectAttributes) {
        // Gửi yêu cầu DELETE để xóa sinh viên với ID cụ thể
        restTemplate.delete(API_URL + "/{id}", id);
        redirectAttributes.addFlashAttribute("message", "Xóa sinh viên thành công");
        return "redirect:/student-ui/list"; // Chuyển hướng về trang danh sách sinh viên
    }
    @PostMapping("/update/{id}")
    public String updateStudent(@ModelAttribute Student student, String id, RedirectAttributes redirectAttributes) {
        // Gửi yêu cầu PUT để cập nhật thông tin của sinh viên
        HttpEntity<Student> requestEntity = new HttpEntity<>(student);
        restTemplate.put(API_URL + "/{id}", requestEntity,id);
        redirectAttributes.addFlashAttribute("message", "Cập nhật sinh viên thành công");
        return "redirect:/student-ui/list"; // Chuyển hướng về trang danh sách sinh viên
    }
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable String id, Model model) {
        // Gọi API để lấy thông tin của sinh viên dựa trên ID
        Student student = restTemplate.getForObject(API_URL + "/{id}", Student.class, id);
        model.addAttribute("student", student);
        return "editStudent"; // Trả về template để chỉnh sửa sinh viên
    }
    @GetMapping("/search")
    public String searchStudents(@RequestParam String name, Model model) {
        ResponseEntity<Student[]> responseEntity = restTemplate.getForEntity(API_URL + "/search?name={name}", Student[].class, name);
        List<Student> students = Arrays.asList(responseEntity.getBody());
        model.addAttribute("students", students);
        return "student-list"; // Corresponds to the HTML file name in templates folder
    }
}
