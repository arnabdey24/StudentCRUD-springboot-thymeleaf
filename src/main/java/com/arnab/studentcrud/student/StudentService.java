package com.arnab.studentcrud.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void removeStudentById(Long id){
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student student){
        if(studentRepository.findById(student.getId()).isPresent()){
            studentRepository.save(student);
        }
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }


    public Student getStudentById(Long id) {
        Optional<Student> data=studentRepository.findById(id);
        if(data.isPresent()){
            return data.get();
        }else {
            throw new RuntimeException("Student with id = "+id+" not found");
        }
    }
}
