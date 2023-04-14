package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.repository.BookRepository;
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
    @Autowired
    BookRepository bookRepository;

    @GetMapping("getAll")
    public ResponseEntity<Iterable<BookImage>> getAll() {
        //
        return ResponseEntity.accepted().body(bookImageRepository.findAll());
    }

    @PostMapping("upload")
    public BookImage saveBookImage( @RequestParam String bookId,
                                    @RequestParam String name,
                                    @RequestParam MultipartFile file) throws IOException {
        BookImage bookImage  = new BookImage();
        //bookImage.setId(Utils.createUUID());
        bookImage.setName(name);
        bookImage.setImage( new Binary(file.getBytes() ));

        /*First, the code checks if the book exists in the book repository by searching for it
        with the provided ID. If the book is not found, it returns null.
        This avoids attempting to add an image to a book that does not exist.
        If the book exists, the code sets the book ID for the book image, as a book
        can have multiple images associated with it. Then, the code saves the book image
        to the book image repository.
        Finally, the code adds the book image ID to the book's list of image IDs and saves
        the updated book to the book repository. This ensures that the book is updated with
        the new image ID.*/

        // Finds a book in the book repository by its ID, if present.
        Optional<Book> book = bookRepository.findById(bookId);
        // Checks if the book exists and sets the book ID for the book image.
        if (book.isPresent()) bookImage.setBookId(book.get().getBookId());
        // If the book doesn't exist, return null.
        else return null;
        // Saves the book image to the book image repository.
        BookImage bookImageSaved = bookImageRepository.save(bookImage);
        // Adds the book image ID to the book's list of image IDs and saves the updated book.
        Book bookUpdated = book.get().addBookImageId(bookImageSaved.getId());
        bookRepository.save(bookUpdated);

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

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteBookImage(@RequestParam(value = "id") String id) {
        Optional<BookImage> optionalBookImage = bookImageRepository.findById(id);
        if (!optionalBookImage.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bookImageRepository.delete(optionalBookImage.get());
        return ResponseEntity.ok().build();
    }
}

