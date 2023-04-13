package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.utils.Utils;
import org.springframework.web.bind.annotation.*;
import com.example.myFirstSpring.model.BookImage;
import com.example.myFirstSpring.repository.BookImageRepository;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64.Encoder;
import java.util.Base64;
import java.util.Optional;


@RestController
@RequestMapping("api/image")
public class BookImageRestController {

    @Autowired
    BookImageRepository bookImageRepository;

    @PostMapping("upload")
    public BookImage saveBookImage( @RequestParam String name, @RequestParam MultipartFile file) throws IOException {
        BookImage bookImage  = new BookImage();
        //bookImage.setId(Utils.createUUID());
        bookImage.setName(name);
        bookImage.setImage( new Binary(file.getBytes() ));

        bookImageRepository.save(bookImage);

        return bookImage;

    }

    @GetMapping("/getData")
    public String getDataBookImage(@RequestParam  String id){

        Optional<BookImage> bookImage = bookImageRepository.findById(id);
        Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString( bookImage.get().getImage().getData() );

    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getBookImage(@RequestParam String id){

        Optional<BookImage> bookImage = bookImageRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>( bookImage.get().getImage().getData(), headers, HttpStatus.OK );

    }

    @PutMapping("update/{id}")
    public ResponseEntity<BookImage> updateBookImage(@PathVariable(value = "id") String id,
                                                     @RequestParam String name,
                                                     @RequestParam MultipartFile file) throws IOException {
        Optional<BookImage> optionalBookImage = bookImageRepository.findById(id);
        if (!optionalBookImage.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        BookImage bookImage = optionalBookImage.get();
        bookImage.setName(name);
        bookImage.setImage(new Binary(file.getBytes()));
        BookImage updatedBookImage = bookImageRepository.save(bookImage);

        return ResponseEntity.ok(updatedBookImage);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBookImage(@PathVariable(value = "id") String id) {
        Optional<BookImage> optionalBookImage = bookImageRepository.findById(id);
        if (!optionalBookImage.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bookImageRepository.delete(optionalBookImage.get());
        return ResponseEntity.ok().build();
    }
}

