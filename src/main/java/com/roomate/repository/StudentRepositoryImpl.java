package com.roomate.repository;

import com.roomate.entity.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentRepositoryImpl {
    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    public StudentEntity findById(String id) {
        return studentRepository.findById(id).get();
    }

    public StudentEntity updateStudent(StudentEntity studentEntity) {
        return saveStudent(studentEntity);
    }

    public String removeStuent(String id) {
        studentRepository.deleteById(id);
        return "user with id : " + id + " has been deleted";
    }

    public List<StudentEntity> findAllStudent() {
        List<StudentEntity> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }
}