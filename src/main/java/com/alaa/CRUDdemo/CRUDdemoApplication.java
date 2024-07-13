package com.alaa.CRUDdemo;

import com.alaa.CRUDdemo.DAO.StudentDAO;
import com.alaa.CRUDdemo.DAO.StudentDAOImpl;
import com.alaa.CRUDdemo.anonymous.Animal;
import com.alaa.CRUDdemo.anonymous.Cat;
import com.alaa.CRUDdemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.HikariCheckpointRestoreLifecycle;
import org.springframework.context.annotation.Bean;

import java.util.List;

// اسهل العمليات علي البيانات من خلال ال spring


@SpringBootApplication
public class CRUDdemoApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to CRUD operation using JPA and Hibernate ");
        SpringApplication.run(CRUDdemoApplication.class, args);

    }


    @Bean
    CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        // The method returns the CommandLineRunner Interface And you need to over the single method in this interface by this way
        return (runner) -> {
//            readStudent(studentDAO);
            createMultipleStudents(studentDAO);
//            createNewStudent(studentDAO);
//            deleteStudent(studentDAO);
//            getAllStudents(studentDAO);
//            getStudentByName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudentById(studentDAO);
//            deleteAllStudent(studentDAO);
        };
    }


    private void deleteAllStudent(StudentDAO studentDAO) {
        studentDAO.deleteAllStudents();
    }

    // we need to delete Object by id
    private void deleteStudentById(StudentDAO studentDAO) {
        // You get the student Or retrieve The Student
        Student retrievedStudent = studentDAO.findById(101);
        // You would delete This student
        studentDAO.delete(retrievedStudent);
    }

    private void updateStudent(StudentDAO studentDAO) {
        // we need to read a student from databse based on id
        int studentId = 1;
        System.out.println("Getting the student by Id " + studentId);

        // we need to get the student by Id
        Student myStudent = studentDAO.findById(studentId);
        // modify the student Name
        myStudent.setLast_name("PottterUpdatedUAmer");
        // we need to update the student in database
        studentDAO.updateStudent(myStudent);
        // you could display this student
        List<Student> students = studentDAO.findStudentByLastName(myStudent.getLast_name());
        for (Student ele : students) {
            String result = ele.toString();
            System.out.println(result);
        }
        System.out.println("The Student updated");

    }

    private void getStudentByName(StudentDAO studentDAO) {
        String lastName = "amer";
        List<Student> retrievedData = studentDAO.findStudentByLastName(lastName);
        for (Student ele : retrievedData) {
            String result = ele.toString();
            System.out.println(result);
        }
    }

    private void getAllStudents(StudentDAO studentDAO) {
        studentDAO.retrievingAllStudents();
    }


    //reading Student From database
    private void readStudent(StudentDAO studentDAO) {
        // you need to create object
        Student student = new Student("Yara", "Amer ", "emailOne@23.com");
        //you need to save this student
        studentDAO.save(student);
        System.out.println("This student is saved to our database ");
        // you need to read the student by id
        Student retrievedStudent = studentDAO.findById(student.getId());
        System.out.println("we found this student in your database ");
        String result = retrievedStudent.toString();
        System.out.println(result);

    }

    // This is the Insertion function First CRUD operation
    private void createNewStudent(StudentDAO studentDAO) {
        // You need to create Student Object and then save it
        //creating student Object
        Student student = new Student("Mohamed Alaa amer ", "Amer is the last name", "email One ");
        // PROCESSING
        studentDAO.save(student);
        studentDAO.info(student);
        //OUTPUT
        System.out.println("Saved Student to our Database : id is + " + student.getId());
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        Student student = new Student("What  ", "Amer is the last name", "email One ");
        Student student1 = new Student("When ", "Amer is the last name", "email One ");
        Student student2 = new Student("Where ", "Amer is the last name", "email One ");
        Student student3 = new Student("How ", "Amer is the last name", "email One ");
        Student student5= new Student("Where ", "Amer is the last name", "email One ");
        Student student4 = new Student("How ", "Amer is the last name", "email One ");
        studentDAO.save(student);
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
    }

    // This is the deletion function Second crud operation
    private void deleteStudent(StudentDAO studentDAO) {
        Student student = new Student("Potter", "last name potter ", "emailTwo ");
        studentDAO.delete(student);
    }


    @Bean
    CommandLineRunner commandLineRunner2() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("This could be used with single or multiple functions implementation");
            }
        };
    }

//
//    @Bean
//    public CommandLineRunner commandLineRunner1() {
//
////        // we have concept of anonymous class uaAmer
////        Animal animal= new Animal() {
////            @Override
////            public void canRun() {
////                System.out.println("This is the anonymous class ");
////            }
////        } ;
////  =======================================
////        Cat cat = new Cat(){
////            @Override
////            public void canRun(){
////                System.out.println("This cal is implemented in the inner clas suaaemr That is Great mission");
////            }
////        } ;
//        //========================================
////        Animal cat = new Cat() {
////            @Override
////            public void canRun() {
////                System.out.println("This method can Run very fast uaa'mer ");
////            }
////
////            public void carFly() {
////                System.out.println("This cat can not flyu Thanks very much uaaemr ");
////            }
////        };
//        new Animal() {
//            @Override
//            public void canRun() {
//                System.out.println("This anonymous Class implements the interface And calling the function");
//            }
//        }.canRun();
//
//        // Animal is an interface
//        Animal animal = () -> {
//            System.out.println("lamda expression used to implement the single method in the interface You are tryinng to take object from ");
//            System.out.println("If you have single line of code you could remove the carely Brackets");
//        };
//        // You could pass Anonymous inner Class to a function as a parameter
//        // هو دا الاستخدام الاهم لل Anonymous Inner Class ودا هيظر معاك في الFlutter
//
//
//        // This is just Inner Anonymous Class uaAmer ==> Just You took Object From this class uaAlaa
//        // انت عندك دالة واحدة بس
//        // انت هتستخدم ال lamada لما يكون interface وعاوز تعمل منه object والا interface دا مش عنده الا دالة زاحدة بس
//        //   ف انت ممكن تستخدم ال lamada علشان تعمل Implementation لهذه الدالة
//
//        // CommandLineRunner is an interface This is so Good mission
//        // Simple Hint You have here multiple parameter List
//        CommandLineRunner runner = (String... args) -> {
//            System.out.println("This is the run method ");
//        };
//        // commandLinerRunnerFunction will return the runner
//        return runner;
//    }
//

}
















