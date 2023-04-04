package com.example.myFirstSpring.repository;

import com.example.myFirstSpring.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, String> {

}