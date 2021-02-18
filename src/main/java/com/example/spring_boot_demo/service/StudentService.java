package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.dao.StudentDAO;
import com.example.spring_boot_demo.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentDAO studentDAO;

    public List<Student> getStudents(){
        return studentDAO.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentDAO.
                findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentDAO.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentDAO.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("student with Id: " + studentId + " does not exists");
        }
        studentDAO.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(long studentId, String name, String email){
        Student student = studentDAO.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does nor exists"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentDAO
                    .findStudentByEmail(email);

            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
