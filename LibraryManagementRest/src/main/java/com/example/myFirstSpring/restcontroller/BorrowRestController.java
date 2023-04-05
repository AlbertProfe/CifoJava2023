package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Borrow;
import com.example.myFirstSpring.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowRestController {

    @Autowired
    BorrowService borrowService;

    @GetMapping("populate")
    public ResponseEntity<List<Borrow>> populate(@RequestParam("qty") int qty) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "populate");
        headers.add("version", "api 1.0");

        List<Borrow> fakeBorrows = borrowService.populateFakeBorrows(qty);

        if (fakeBorrows.size() > 0 ) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(fakeBorrows);
        } else {
            headers.add("statusOperation", "not populated");
            return ResponseEntity.accepted().body(null);
        }
    }

    @GetMapping("/borrows")
    public  ResponseEntity<Iterable<Borrow>> getAllBorrows(){
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getAllBorrows");
        headers.add("version", "api 1.0");
        headers.add("statusOperation", "success");

        return ResponseEntity.accepted().headers(headers).body(borrowService.getAllBorrows());
    }



    @GetMapping("/createBorrow")
    public ResponseEntity<String> createBorrow (@RequestParam("userId") String userId ,
                                @RequestParam("bookIds") List<String> bookIds){

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "createBorrow");
        headers.add("version", "api 1.0");

        HashMap<String, String> createdBorrows = borrowService.createBorrow(userId, bookIds);

        if ( createdBorrows.get("status").equals("fail") ) {
            headers.add("statusOperation", "fail");
            return ResponseEntity.accepted().headers(headers).body(createdBorrows.get("statusDescription"));
        } else {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(createdBorrows.get("statusDescription"));
        }

    }

    @GetMapping("/returnBorrow")
    public ResponseEntity<String> returnBook (@RequestParam("borrowId") String borrowId){

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "returnBook");
        headers.add("version", "api 1.0");

        boolean returned = borrowService.returnBookByBorrowId(borrowId);

        if ( returned ) {

            return ResponseEntity.accepted().headers(headers).body(borrowId + " > closed borrow");
        } else {
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }
}
