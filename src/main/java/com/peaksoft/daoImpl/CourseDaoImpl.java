package com.peaksoft.daoImpl;

import com.peaksoft.dao.CompanyDao;
import com.peaksoft.dao.CourseDao;
import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final CompanyDao companyDao;

    @Autowired
    public CourseDaoImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course>courses=entityManager.createQuery("from Course",Course.class).getResultList();
        Comparator<Course> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        courses.sort(comparator);
        return courses;
    }

    @Override
    public void addCourse(Course course,Long companyId) {
        Company company=companyDao.getCompanyById(companyId);
        course.setCompany(company);
        entityManager.persist(course);
    }

    @Override
    public Course getCourseById(Long id) {
        Course course;
        course=entityManager.find(Course.class,id);
        return course;
    }

    @Override
    public void updateCourse(Course course,Long id) {
        Course course1=getCourseById(id);
        course1.setCourseName(course.getCourseName());
        course1.setDurationMonth(course.getDurationMonth());
        entityManager.merge(course1);
    }

    @Override
    public void deleteCourse(Course course) {
        entityManager.remove(entityManager.contains(course)?course:entityManager.merge(course));

    }

    @Override
    public List<Group> getGroupsByCourse(Long courseId) {
        List<Group>groups=entityManager.createQuery("select g from Group g join g.courses c where c.id=?1")
                .setParameter(1,courseId).getResultList();
        return groups;
    }
}
