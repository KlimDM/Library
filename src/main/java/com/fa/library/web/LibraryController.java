package com.fa.library.web;

import com.fa.library.model.Book;
import org.springframework.ui.Model;
import com.fa.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
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
    public String home() {
        return "home";
    }

    @ModelAttribute(name="books")
    public List<Book> addBooksToModel() {
        return bookRepository.findAll();
    }

    //TODO
    @GetMapping("/search")
    public String searchByKeyword(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("books", bookRepository.findByKeyword(keyword));
        return "redirect:";
    }
}
