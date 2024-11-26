package com.klef.jfsd.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.Repository.StudentRepository;
import com.klef.jfsd.springboot.exception.ResourceNotFoundException;
import com.klef.jfsd.springboot.model.Student;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
            .map(student -> {
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                student.setCollege(updatedStudent.getCollege());
                student.setCourse(updatedStudent.getCourse());
                return studentRepository.save(student);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }


    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

