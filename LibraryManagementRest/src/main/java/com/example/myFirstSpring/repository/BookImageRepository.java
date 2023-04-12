package com.example.myFirstSpring.repository;


import com.example.myFirstSpring.model.BookImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepository extends MongoRepository<BookImage, String> {}