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

    @GetMapping("/book")
    public String showBookDetails(@RequestParam("id") Long id, Model model, @ModelAttribute("books")List<Book> books) {
        model.addAttribute("book", bookRepository.findById(id).orElse(null));
        return "bookdetails";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") long id, @ModelAttribute("books") List<Book> books) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/issueBook")
    public String issueBook(@RequestParam("id") long id, @RequestParam("studentName") String studentName) {
        Book currentBook = bookRepository.findById(id).orElse(null);
        if (currentBook != null) {
            currentBook.setStudentName(studentName);
            currentBook.setIssueDate(LocalDate.now());
            bookRepository.save(currentBook);
        }
        bookRepository.findByKeyword("java");
        return "redirect:/book?id=" + id;
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("id") long id) {
        Book currentBook = bookRepository.findById(id).orElse(null);
        if (currentBook != null) {
            currentBook.setStudentName(null);
            currentBook.setIssueDate(null);
            currentBook.setReturnDate(LocalDate.now());
            bookRepository.save(currentBook);
        }
        return "redirect:/book?id=" + id;
    }

    //TODO
    @GetMapping("/search")
    public String searchByKeyword(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("books", bookRepository.findByKeyword(keyword));
        return "redirect:";
    }
}
