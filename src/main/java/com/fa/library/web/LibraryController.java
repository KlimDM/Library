package com.fa.library.web;

import com.fa.library.model.Book;
import org.springframework.ui.Model;
import com.fa.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model) {
        return "home";
    }

    @ModelAttribute(name="books")
    public List<Book> addBooksToModel(Model model) {
        return bookRepository.findAll();
    }

    @GetMapping("/book")
    public String showBookDetails(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElse(null));
        return "bookdetails";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") long id, @ModelAttribute("books") List<Book> books) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/issueBook")
    public String issueBook(@RequestParam("id") long id, @RequestParam("studentName") String studentName,
                            Model model) {
        Book currentBook = bookRepository.findById(id).orElse(null);
        if (currentBook != null) {
            currentBook.setStudentName(studentName);
            currentBook.setIssueDate(LocalDate.now());
            bookRepository.save(currentBook);
        }
        return showBookDetails(id, model);
    }
}
