package in.strikes.SpringBootCrudDemo.repository;

import in.strikes.SpringBootCrudDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository  extends JpaRepository<Student , Long > {
    Optional<Student> findByIdAndIsDeletedFalse(Long id);

    List<Student> findByIsDeletedFalse();
//findBy +fieldName +consdition














    /* public Student saveStudent(Student studentReq){
        //save to DB
       System.out.println("inside StudentRepository");
        Student s1=new Student();
        s1.setAge(2);
        s1.setEmail("xyz@gmail.com");
        s1.setId(1L);
        s1.setName("xyz");
        s1.setRollNo(12);
        s1.setSubject("SpringBoot");
        return  s1;*/



    }
