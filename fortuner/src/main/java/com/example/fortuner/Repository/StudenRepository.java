package com.example.fortuner.Repository;

import com.example.fortuner.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudenRepository  extends JpaRepository<Student,String> {
    List<Student> findByTenContaining(String name);

    List<Student> findByTenContainingIgnoreCase(String name);
}
