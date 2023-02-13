package org.example.storage;

import org.example.model.Book;
import org.example.model.Borrow;
import org.example.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class LibraryStorage {
    private Map<String, User> users;
    private Map<String, Book> books;
    private Map<String, Borrow> borrows;

    public LibraryStorage() {
        this.users = new HashMap<>();
        this.books = new HashMap<>();
        this.borrows = new HashMap<>();
    }

    public void addUser(String userId, User user) {
        this.users.put(userId, user);
    }

    public User getUser(String userId) {
        return this.users.get(userId);
    }

    public void addBook(String bookId, Book book) {
        this.books.put(bookId, book);
    }

    public Book getBook(String bookId) {
        return this.books.get(bookId);
    }

    public void addBorrow(String borrowId, Borrow borrow) {
        this.borrows.put(borrowId, borrow);
    }

    public Borrow getBorrow(String borrowId) {
        return this.borrows.get(borrowId);
    }

    public void deleteUser(String userId) {
        this.users.remove(userId);
    }

    public void deleteBook(String bookId) {
        this.books.remove(bookId);
    }

    public void deleteBorrow(String borrowId) {
        this.borrows.remove(borrowId);
    }

    public User findOneUser(String userId) {
        return this.users.get(userId);
    }

    public Book findOneBook(String bookId) {
        return this.books.get(bookId);
    }

    public Borrow findOneBorrow(String borrowId) {
        return this.borrows.get(borrowId);
    }

    public List<User> listAllUsers() {
        return new ArrayList<>(this.users.values());
    }

    public List<Book> listAllBooks() {
        return new ArrayList<>(this.books.values());
    }

    public List<Borrow> listAllBorrows() {
        return new ArrayList<>(this.borrows.values());
    }
}
