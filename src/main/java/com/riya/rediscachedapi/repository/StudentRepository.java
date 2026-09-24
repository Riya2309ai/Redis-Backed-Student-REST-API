package com.riya.rediscachedapi.repository;

import com.riya.rediscachedapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //these two methods are already there in the JpaRepository by-default so no need to write it again
    //public Optional<Student> findById(int id);
    //public void save(Student student);
}
