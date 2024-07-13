package com.alaa.CRUDdemo.DAO;

import com.alaa.CRUDdemo.entity.Car;
import com.alaa.CRUDdemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// Support Scanning and support handling for checked exceptions
@Repository
public class StudentDAOImpl implements StudentDAO {

    //define entity manager
    private EntityManager entityManager;

    // you need to inject the entity manager to the StudentDAO using constructor
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement the save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    @Transactional
    public void delete(Student student) {
        entityManager.remove(
                entityManager.contains(student) ? student : entityManager.merge(student)
        );
    }


    @Override
    @Transactional
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        // You could just merge method for making update
        entityManager.merge(student);
        // we will prepare the query
//        int numberRowsUpdated = entityManager.createQuery("update Student set last_name=:theData").executeUpdate();

    }

    @Override
    public void retrievingAllStudents() {
        TypedQuery<Student> theQueryFromJPQL = entityManager.createQuery("From Student", Student.class);
        System.out.println(theQueryFromJPQL);
        List<Student> allStudent = theQueryFromJPQL.getResultList();
        for (Student ele : allStudent) {
            String singleRow = ele.toString();
            System.out.println(singleRow);
        }
    }


    @Override
    @Transactional
    public List<Student> findStudentByLastName(String theData) {
        // preparing the query
        // All Strings That you type here is based on java Code Not tables in the database
        TypedQuery<Student> query = entityManager.createQuery("From Student where last_name=:theData", Student.class);

        // last_name:=theData ==> That's mean This is a variable uaAlaa
        //set the query parameters
        query.setParameter("theData", theData);
        // return query result
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student retrievedStudent = entityManager.find(Student.class, id);
        entityManager.remove(retrievedStudent);
    }

    @Override
    @Transactional
    public void deleteAllStudents() {
        // You could delete via JPQL statement
//        int numRowsDeleted = entityManager.createQuery("Delete From Student", Student.class).executeUpdate();

        //--------------------------------
        // you need to prepare the statement
        TypedQuery<Student> theQueryFromJPQL = entityManager.createQuery("From Student", Student.class);
        //make list to receive the output
        List<Student> allStudent = theQueryFromJPQL.getResultList();
        // You could remove every row from  this list
        for (Student ele : allStudent) {
            String singleRow = ele.toString();
            System.out.println("This row is deleted + " + singleRow);
            entityManager.remove(ele);
        }
    }


    @Override
    @Transactional
    public void info(Student student) {
        System.out.println("This is just the Student Information uaAmer ");
        System.out.println("The student Id is : " + student.getId());
        System.out.println("The student name is  :" + student.getFirst_name());
        System.out.println("The studetn name is  :" + student.getLast_name());
        System.out.println("The student email is : " + student.getEmail());
    }


}
