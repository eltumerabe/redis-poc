package com.roomate.service;

import com.roomate.entity.StudentEntity;
import com.roomate.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentEntity findById(String id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public StudentEntity updateStudent(StudentEntity studentEntity) {
        return this.saveStudent(studentEntity);
    }

    @Override
    public String removeStuent(String id) {
        return "student with id : " + id + " has been removed";
    }

    @Override
    public List<StudentEntity> findAllStudent() {
        List<StudentEntity> studentEntities = new ArrayList<>();
        studentRepository.findAll().forEach(studentEntities::add);
        return studentEntities;
    }
}
