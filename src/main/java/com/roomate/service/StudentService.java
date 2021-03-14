package com.roomate.service;

import com.roomate.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    StudentEntity saveStudent(StudentEntity studentEntity);

    StudentEntity findById(String id);

    StudentEntity updateStudent(StudentEntity studentEntity);

    String removeStuent(String id);

    List<StudentEntity> findAllStudent();

}
