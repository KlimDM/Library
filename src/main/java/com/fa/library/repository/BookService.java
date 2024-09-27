package com.fa.library.repository;

import com.fa.library.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findByKeyword(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseOrPublisherContainingIgnoreCaseOrStudentNameContainingIgnoreCase(keyword, keyword, keyword);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void add(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
