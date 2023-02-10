package com.peaksoft.daoImpl;

import com.peaksoft.dao.GroupDao;
import com.peaksoft.dao.StudentDao;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {
    @PersistenceContext
    EntityManager entityManager;

    private final GroupDao groupDao;

    @Autowired
    public StudentDaoImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }


    @Override
    public List<Student> getAllStudents() {
        List<Student> students =entityManager.createQuery("from Student",Student.class).getResultList();
        Comparator<Student> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        students.sort(comparator);
        return students;
    }

    @Override
    public void saveStudent(Student student,Long id) {
        Group group=groupDao.getGroupById(id);
        List<Student>students=new ArrayList<>();
        students.add(student);
        student.setGroup(group);
        group.setStudents(students);
        entityManager.persist(student);

    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(entityManager.contains(student)?student:entityManager.merge(student));
    }

    @Override
    public void updateStudent(Student student,Long id) {
        Student student1=getStudentById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);

    }

    @Override
    public List<Student> findByName(String name) {
        List<Student>students = entityManager.createQuery("select s from Student s where s.firstName=?1",
                        Student.class).setParameter(1,name).getResultList();
        return students;
    }

    @Override
    public List<Student> getStudentsByCompany(Long companyId) {
        List<Student>students=entityManager.createQuery(
                        "select s from Student s join Group g on g.id=s.group.id  join g.courses c   join Company com on com.id=c.company.id where com.id=?1",Student.class)
                .setParameter(1,companyId).getResultList();
        return students;
    }
}
