package com.fa.library.web;

import com.fa.library.model.Book;
import com.fa.library.repository.BookService;
import org.springframework.data.repository.query.Param;
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
    private final BookService bookService;

    /**
     * Начальная страница приложения
     */
    @GetMapping
    public String home() {
        return "home";
    }

    @ModelAttribute(name = "keyword")
    public String addKeywordToModel(@Param("keyword") String keyword) {
        return keyword;
    }
    @ModelAttribute(name="books")
    public List<Book> addBooksToModel(@Param("keyword")String keyword) {
        if (keyword == null || keyword.equals("")) {
            return bookService.findAll();
        } else return bookService.findByKeyword(keyword);
    }

    //TODO
    @GetMapping("/search")
    public String searchByKeyword(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("books", bookService.findByKeyword(keyword));
        return "redirect:";
    }
}
