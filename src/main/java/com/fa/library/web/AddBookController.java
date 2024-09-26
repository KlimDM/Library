package com.fa.library.web;

import com.fa.library.model.Book;
import com.fa.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addBook")
@RequiredArgsConstructor
public class AddBookController {
    private final BookRepository bookRepository;

    @GetMapping
    public String showAddBookForm(@ModelAttribute("newBook")Book newBook) {
        return "addbook";
    }

    @PostMapping
    public String processNewBook(@ModelAttribute Book newBook) {
        if (newBook.getPublisher().equals("")) {
            newBook.setPublisher(null);
        }
        if (newBook.getThumbnailUrl().equals("")) {
            newBook.setThumbnailUrl(null);
        }
        bookRepository.save(newBook);
        return "redirect:/";
    }
}
