package com.alaa.CRUDdemo.DAO;

import com.alaa.CRUDdemo.entity.Car;
import com.alaa.CRUDdemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    public void save(Student student);

    public void info(Student student);

    public void delete(Student student);

    public Student findById(Integer id);

    public void updateStudent(Student student);

    public void retrievingAllStudents();


    public List<Student> findStudentByLastName(String theData);

    public void delete(Integer id);

    public void deleteAllStudents();
}
