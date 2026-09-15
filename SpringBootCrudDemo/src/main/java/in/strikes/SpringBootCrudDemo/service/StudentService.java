package in.strikes.SpringBootCrudDemo.service;

import in.strikes.SpringBootCrudDemo.entity.Student;
import in.strikes.SpringBootCrudDemo.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service // it tells that it is a service class // business logic is written
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService( StudentRepository studentRepository) {
        this.studentRepository=studentRepository;
    }

    public Student createStudent(Student studentRequest){
        // valadation , businesss logic
      // System.out.println("inside student service");
     studentRequest.setDeleted(false);
    Student studentResp = studentRepository.save(studentRequest);
    return  studentResp;

    }

    public Student getStudent(Long id)
    {
     //Optional<Student> studentResp= studentRepository.findById(id);
        Optional<Student> studentResp= studentRepository.findByIdAndIsDeletedFalse(id);

        if(studentResp.isPresent()){
        return studentResp.get();
    }

    return null;
    }

    public List<Student> getAllStudent(){
        List<Student> studentList=studentRepository.findByIsDeletedFalse();
        return studentList;
    }

    //select * from students where deleted = false;
    public  Student updateStudent(Long id , Student studentReq ){

        Optional<Student> studentResp=studentRepository.findByIdAndIsDeletedFalse(id);
      if(studentResp.isEmpty()){

          return null;
      }
      Student studentToSave=studentResp.get();
      studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
    return studentRepository.save(studentToSave);

    }

    public Boolean deleteStudent(Long id ){
       Boolean isStudent= studentRepository.existsById(id);
       if(!isStudent){
           return false;
       }

       studentRepository.deleteById(id);
       return true;

    }

    public Boolean deleteStudentSoftly(Long id){
        Optional<Student> existingStudent =studentRepository.findByIdAndIsDeletedFalse(id);
        if(existingStudent.isEmpty()){

            return false;

        }
        Student studnetToSave=existingStudent.get();
        studnetToSave.setDeleted(true);
        studentRepository.save(studnetToSave);
        return true;

    }


}
