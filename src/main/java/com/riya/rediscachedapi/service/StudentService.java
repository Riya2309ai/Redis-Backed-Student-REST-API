package com.riya.rediscachedapi.service;

import com.riya.rediscachedapi.entity.Student;
import com.riya.rediscachedapi.repository.StudentRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CacheManager cacheManager;

    public StudentService(StudentRepository studentRepository,  CacheManager cacheManager) {
        this.studentRepository = studentRepository;
        this.cacheManager = cacheManager;
    }

    @Cacheable(value = "students", key = "#id")
    public Student getStudent(int id){
       return studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found: " + id));
    }

    @CachePut(value="students", key="#result.id")
    public void addStudent(Student student){
         studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }



    public void deleteStudent(int id){
        studentRepository.deleteById(id);

        //use of CacheManager instead of @CacheEvict to manually control over cache in program
        Cache cache = cacheManager.getCache("students");
        if(cache != null) {
            cache.evict(id);
        }
    }
}
