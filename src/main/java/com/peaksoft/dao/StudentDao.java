package com.peaksoft.dao;

import com.peaksoft.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    void saveStudent(Student student,Long courseId);
    Student getStudentById(Long id);
    void deleteStudent(Student student);
    void updateStudent(Student student,Long id);
    List<Student> findByName(String name);
    List<Student>getStudentsByCompany(Long companyId);
}
