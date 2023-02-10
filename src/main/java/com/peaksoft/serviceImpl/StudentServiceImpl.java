package com.peaksoft.serviceImpl;

import com.peaksoft.dao.StudentDao;
import com.peaksoft.entity.Student;
import com.peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public void saveStudent(Student student,Long id) {
        studentDao.saveStudent(student,id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.deleteStudent(student);
    }

    @Override
    public void updateStudent(Student student,Long id) {
        studentDao.updateStudent(student,id);
    }

    @Override
    public List<Student> findByStudentName(String name) {
        return studentDao.findByName(name);
    }

    @Override
    public List<Student> getStudentsByCompany(Long companyId) {
        return studentDao.getStudentsByCompany(companyId);
    }
}
