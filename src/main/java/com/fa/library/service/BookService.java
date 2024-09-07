package com.fa.library.service;

import com.fa.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    void addBook() {}
    void deleteBook() {}
    void editBook() {}
}
