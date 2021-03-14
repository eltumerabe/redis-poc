package com.roomate.controller;

import com.roomate.entity.StudentEntity;
import com.roomate.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class RedisController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private HashOperations hashOperations;


    @PostMapping
    public StudentEntity saveStudent(@RequestBody StudentEntity studentEntity) {
        studentEntity = studentService.saveStudent(studentEntity);
        hashOperations.put("STUDENT", studentEntity.getId(), studentEntity);
        return studentEntity;
    }

    @GetMapping("/{id}")
    public StudentEntity findById(@PathVariable(value = "id") String id) {
        if (null != hashOperations.get("STUDENT", id)) {
            log.info("=========== from Cache ================");
            return (StudentEntity) hashOperations.get("STUDENT", id);
        } else {
            log.info("================= from DB ===================");
            return studentService.findById(id);
        }
    }

    @PutMapping
    public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity) {
        return studentService.updateStudent(studentEntity);
    }

    @DeleteMapping("/{id}")
    public String removeStuent(@PathVariable(value = "id") String id) {
        return studentService.removeStuent(id);
    }

    @GetMapping
    public List<StudentEntity> findAllStudent() {
        return studentService.findAllStudent();
    }
}
