package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("api/book")
public class BookRestController {
    //here we are creating our end-point rest API
    @Autowired
    BookService bookservice;

    @GetMapping("populate")
    public ResponseEntity<HashMap<String, Book> > findBookById(@RequestParam("qty") int qty) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "populate");
        headers.add("version", "api 1.0");

        HashMap<String, Book> fakeBooks = bookservice.populateFakeBooks(qty);

        if (fakeBooks.size() > 0 ) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(fakeBooks);
        } else {
            headers.add("statusOperation", "not populated");
            return ResponseEntity.accepted().body(null);
        }
    }
    //CRUD: read
    @GetMapping("books")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getAllBooks");
        headers.add("version", "api 1.0");
        headers.add("statusOperation", "success");

        return ResponseEntity.accepted().headers(headers).body(bookservice.getAllBooks());
    }

    //CRUD: read, find one book by id
    @GetMapping("getBook")
    public ResponseEntity<Book> findBookById(@RequestParam("id") String id) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "findBookById");
        headers.add("version", "api 1.0");

        Optional<Book> bookFound = bookservice.findBookById(id);
        if (bookFound.isPresent()) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(bookFound.get());
        } else {
            headers.add("statusOperation", "not found");
            return ResponseEntity.accepted().body(null);
        }
    }

    //CRUD: create
    @PostMapping(path = "createBook", consumes = "application/JSON")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "createBook");
        headers.add("version", "api 1.0");
        headers.add("statusOperation", "success");

        Book bookCreated = bookservice.createBook(book);

        if (bookCreated != null) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(bookCreated);
        } else {
            headers.add("statusOperation", "not created");
            return ResponseEntity.accepted().body(null);
        }
    }

    //CRUD: delete
    @DeleteMapping("deleteBook")
    public ResponseEntity<Book> deleteBook(@RequestParam String id) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteBook");
        headers.add("version", "api 1.0");

        Optional<Book> bookFound = bookservice.findBookById(id);
        boolean isBook = bookFound.isPresent();
        if (isBook) {
            //Optional<Book> deletedBook = bookservice.deleteBookById(id);
            bookservice.deleteBookById(id);
            headers.add("operationStatus", "deleted");
            return ResponseEntity.accepted().headers(headers).body(bookFound.get());
        } else {
            headers.add("operationStatus", "not found");
            return ResponseEntity.accepted().body(null);
        }
    }


    //CRUD: update
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book dataBook) {

    HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateBook");
        headers.add("version", "api 1.0");
        Optional<Book> bookFound = bookservice.findBookById(id);

        if (bookFound.isPresent()) {
            Book bookToUpdate = bookFound.get();
            //
            if (dataBook.getTitle() != null) {
                bookToUpdate.setTitle(dataBook.getTitle());
            }

            Book bookUpdated = bookservice.updateBook(bookToUpdate);
            return ResponseEntity.accepted().headers(headers).body(bookUpdated);
        } else
            return ResponseEntity.accepted().body(null);

    }

    //CRUD: delete book by title
    @DeleteMapping("deleteBookByTitle")
    public ResponseEntity<Book> deleteBookByTitle(@RequestParam String title) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteBookByTitle");
        headers.add("version", "api 1.0");

        //findBookByTitle(String title)
        //deleteBookByTitle(String title)

        return null;

    }
}
