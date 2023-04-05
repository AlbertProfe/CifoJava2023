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
    BookService bookService;

    @GetMapping("populate")
    public ResponseEntity<HashMap<String, Book> > populate(@RequestParam("qty") int qty) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "populate");
        headers.add("version", "api 1.0");

        HashMap<String, Book> fakeBooks = bookService.populateFakeBooks(qty);

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

        return ResponseEntity.accepted().headers(headers).body(bookService.getAllBooks());
    }

    //CRUD: read, find one book by id
    @GetMapping("getBook")
    public ResponseEntity<Book> findBookById(@RequestParam("id") String id) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "findBookById");
        headers.add("version", "api 1.0");

        Optional<Book> bookFound = bookService.findBookById(id);
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

        Book bookCreated = bookService.createBook(book);

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

        Optional<Book> bookFound = bookService.findBookById(id);
        boolean isBook = bookFound.isPresent();
        if (isBook) {
            //Optional<Book> deletedBook = bookservice.deleteBookById(id);
            bookService.deleteBookById(id);
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

        Optional<Book> bookFound = bookService.findBookById(id);


        if (bookFound.isPresent()){
            bookService.updateBook(bookFound.get(), dataBook);
            headers.add("operationStatus","updated");
            return  ResponseEntity.accepted().headers(headers).body(bookFound.get());
        } else {
            headers.add("operationStatus","not found");
            return ResponseEntity.accepted().headers(headers).body(null);}


    }

    //CRUD: delete book by title
    @DeleteMapping("deleteBookByTitle")
    public ResponseEntity<Book> deleteBookByTitle(@RequestParam String title) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteBookByTitle");
        headers.add("version", "api 1.0");

        //findBookByTitle(String title)
        //deleteBookByTitle(S   tring title)

        return null;

    }
}
