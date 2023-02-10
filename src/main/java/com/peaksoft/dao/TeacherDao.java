package com.peaksoft.dao;

import com.peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> getAllTeachers();
    void saveTeacher(Teacher teacher,Long courseId);
    Teacher getTeacherById(Long id);
    void deleteTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher,Long id);
}