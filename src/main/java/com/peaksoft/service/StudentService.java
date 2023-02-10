package com.peaksoft.service;

import com.peaksoft.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student,Long id);
    Student getStudentById(Long id);
    void deleteStudent(Student student);
    void updateStudent(Student student,Long id);
    List<Student>findByStudentName(String name);

    List<Student>getStudentsByCompany(Long companyId);
}
