package in.strikes.SpringBootCrudDemo.controller;

import in.strikes.SpringBootCrudDemo.entity.Student;
import in.strikes.SpringBootCrudDemo.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.management.loading.PrivateClassLoader;
import java.util.List;

@RestController   // contain internally @component
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;
     StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

       Student createdStudent= studentService.createStudent(student);
       return ResponseEntity.
               status(HttpStatus.CREATED).
                  body(createdStudent);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id ){
            Student studentResp=studentService.getStudent(id);
            if(studentResp==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(studentResp);
    }


    // Get all record
    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> studentList =studentService.getAllStudent();
        if(studentList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }
    // update by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student ){
         Student  studentResponse=studentService.updateStudent(id, student);
        if(studentResponse==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
     }

    //@Transactional it is good practice to use transactional when you are not using JPa internal query method
    //and when you dont use transaactional it works fine beacuasse internally jparepository contain transactional wrapper so it wont through error.+

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
       Boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record deleted successfully ");
    }
    // soft delete
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
        Boolean isDeleted=studentService.deleteStudentSoftly(id);
        if(isDeleted){
            return ResponseEntity.ok("softly Deleted ");
        }
        return ResponseEntity.notFound().build();
    }
}
