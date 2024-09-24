package com.fa.library.web;

import com.fa.library.model.Book;
import org.springframework.ui.Model;
import com.fa.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LibraryController {
    private final BookRepository bookRepository;

    /**
     * Начальная страница приложения
     */
    @GetMapping
    public String home(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/book")
    public String showBookDetails(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElse(null));
        return "bookdetails";
    }
}
